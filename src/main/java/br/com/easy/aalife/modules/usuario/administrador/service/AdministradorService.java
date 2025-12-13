package br.com.easy.aalife.modules.usuario.administrador.service;

import br.com.easy.aalife.config.exceptions.ValidationException;
import br.com.easy.aalife.modules.usuario.administrador.dto.AdministradorAtualizacaoRequest;
import br.com.easy.aalife.modules.usuario.administrador.dto.AdministradorRequest;
import br.com.easy.aalife.modules.usuario.administrador.model.Administrador;
import br.com.easy.aalife.modules.usuario.administrador.repository.AdministradorRepository;
import br.com.easy.aalife.modules.usuario.comum.model.Usuario;
import br.com.easy.aalife.modules.usuario.comum.repository.UsuarioRepository;
import br.com.easy.aalife.modules.usuario.comum.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static br.com.easy.aalife.modules.comum.util.ConstantsUtils.EX_USUARIO_NAO_ENCONTRADO;
import static br.com.easy.aalife.modules.comum.util.ConstantsUtils.MSG_USUARIO_JA_CADASTRADO;
import static br.com.easy.aalife.modules.comum.util.TelefoneUtils.validarTelefone;

@Service
@RequiredArgsConstructor
public class AdministradorService {

    private final AdministradorRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioService service;

    public void salvar(AdministradorRequest request) {
        validarEmailExistente(request.email());
        validarTelefone(request.telefone());
        validarCpfExistente(request.cpf());
        validarTelefoneExistente(request.telefone());

        var usuario = Usuario.ofAdministrador(request, passwordEncoder);
        usuarioRepository.save(usuario);

        var administrador = Administrador.of(request, usuario);

        repository.save(administrador);
    }

    public void editar(Integer id, AdministradorAtualizacaoRequest request) {
        var administrador = findById(id);
        var usuario = administrador.getUsuario();

        service.validarUsuarioAtivo(usuario.getSituacao());

        validarTelefoneParaAtualizar(request.telefone(), id);
        service.validarEmailParaAtualizar(request.email(), usuario.getId());

        administrador.editar(request);
        usuario.editarAdministrador(request, passwordEncoder);

        repository.save(administrador);
        usuarioRepository.save(usuario);
    }

    private void validarEmailExistente(String email) {
        if (usuarioRepository.existsByEmail(email)) {
            throw new ValidationException(MSG_USUARIO_JA_CADASTRADO);
        }
    }

    public Administrador findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ValidationException(EX_USUARIO_NAO_ENCONTRADO));
    }

    private void validarCpfExistente(String cpf) {
        if (cpf != null && repository.existsByCpf(cpf)) {
            throw new ValidationException(MSG_USUARIO_JA_CADASTRADO);
        }
    }

    private void validarTelefoneExistente(String telefone) {
        if (repository.existsByTelefone(telefone)) {
            throw new ValidationException(MSG_USUARIO_JA_CADASTRADO);
        }
    }

    private void validarTelefoneParaAtualizar(String telefone, Integer id) {
        if (repository.existsByTelefoneAndIdNot(telefone, id)) {
            throw new ValidationException(MSG_USUARIO_JA_CADASTRADO);
        }
        validarTelefone(telefone);
    }
}
