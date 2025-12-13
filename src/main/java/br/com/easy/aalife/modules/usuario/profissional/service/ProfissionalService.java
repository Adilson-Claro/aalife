package br.com.easy.aalife.modules.usuario.profissional.service;

import br.com.easy.aalife.config.exceptions.ValidationException;
import br.com.easy.aalife.modules.comum.enums.ETipoConselho;
import br.com.easy.aalife.modules.usuario.comum.model.Usuario;
import br.com.easy.aalife.modules.usuario.comum.repository.UsuarioRepository;
import br.com.easy.aalife.modules.usuario.comum.service.UsuarioService;
import br.com.easy.aalife.modules.usuario.profissional.dto.ProfissionalAtualizacaoRequest;
import br.com.easy.aalife.modules.usuario.profissional.dto.ProfissionalRequest;
import br.com.easy.aalife.modules.usuario.profissional.dto.ProfissionalResponse;
import br.com.easy.aalife.modules.usuario.profissional.model.Profissional;
import br.com.easy.aalife.modules.usuario.profissional.repository.ProfissionalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static br.com.easy.aalife.modules.comum.util.ConstantsUtils.EX_USUARIO_NAO_ENCONTRADO;
import static br.com.easy.aalife.modules.comum.util.ConstantsUtils.MSG_USUARIO_JA_CADASTRADO;
import static br.com.easy.aalife.modules.comum.util.CpfCnpjUtils.validarCpfOuCnpj;
import static br.com.easy.aalife.modules.comum.util.TelefoneUtils.validarTelefone;

@Service
@RequiredArgsConstructor
public class ProfissionalService {

    private final ProfissionalRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioService service;

    public void salvar(ProfissionalRequest request) {
        validarEmailExistente(request.email());
        validarTelefone(request.telefone());
        validarCpfOuCnpj(request.cpf(), request.cnpj());
        validarCpfExistente(request.cpf());
        validarTelefoneExistente(request.telefone());
        validarNumeroConselhoExistente(request.numeroConselho());

        var usuario = Usuario.ofProfissional(request, passwordEncoder);
        usuarioRepository.save(usuario);

        var profissional = Profissional.of(request, usuario);

        repository.save(profissional);
    }

    public void editar(Integer id, ProfissionalAtualizacaoRequest request) {
        var profissional = findById(id);
        var usuario = profissional.getUsuario();

        service.validarUsuarioAtivo(usuario.getSituacao());

        validarTelefoneParaAtualizar(request.telefone(), id);
        service.validarEmailParaAtualizar(request.email(), usuario.getId());

        profissional.editar(request);
        usuario.editarProfissional(request, passwordEncoder);

        repository.save(profissional);
        usuarioRepository.save(usuario);
    }

    public Page<ProfissionalResponse> findProfissionais(ETipoConselho tipoConselho, Pageable pageable) {
        return repository.findByTipoConselho(tipoConselho, pageable)
                .map(ProfissionalResponse::of);
    }

    private void validarEmailExistente(String email) {
        if (usuarioRepository.existsByEmail(email)) {
            throw new ValidationException(MSG_USUARIO_JA_CADASTRADO);
        }
    }

    private void validarTelefoneExistente(String telefone) {
        if (repository.existsByTelefone(telefone)) {
            throw new ValidationException(MSG_USUARIO_JA_CADASTRADO);
        }
    }

    private void validarCpfExistente(String cpf) {
        if (cpf != null && repository.existsByCpf(cpf)) {
            throw new ValidationException(MSG_USUARIO_JA_CADASTRADO);
        }
    }

    public Profissional findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ValidationException(EX_USUARIO_NAO_ENCONTRADO));
    }

    private void validarNumeroConselhoExistente(String numeroConselho) {
        if (repository.existsByNumeroConselho(numeroConselho)) {
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
