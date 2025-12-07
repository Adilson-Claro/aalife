package br.com.easy.aalife.modules.administrador.service;

import br.com.easy.aalife.config.exceptions.ValidationException;
import br.com.easy.aalife.modules.administrador.dto.UsuarioAdministradorAtualizacaoRequest;
import br.com.easy.aalife.modules.administrador.dto.UsuarioAdministradorRequest;
import br.com.easy.aalife.modules.administrador.dto.UsuarioAdministradorResponse;
import br.com.easy.aalife.modules.administrador.model.UsuarioAdministrador;
import br.com.easy.aalife.modules.administrador.repository.UsuarioAdministradorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static br.com.easy.aalife.modules.comum.util.ConstantsUtils.MSG_USUARIO_JA_CADASTRADO;

@Service
@RequiredArgsConstructor
public class UsuarioAdministradorService {

    private final UsuarioAdministradorRepository repository;
    private final PasswordEncoder passwordEncoder;

    public void salvar(UsuarioAdministradorRequest request) {
        validarUsuarioExistente(request);
        var usuario = UsuarioAdministrador.of(request, passwordEncoder);
        repository.save(usuario);
    }

    public void editar(Integer id, UsuarioAdministradorAtualizacaoRequest request) {
        var usuario = findById(id);
        validarEmailExistente(request.email());

        usuario.editar(request, passwordEncoder);

        repository.save(usuario);
    }

    public void alterarSituacao(Integer id) {
        var usuario = findById(id);
        usuario.alterarSituacao();
        repository.save(usuario);
    }

    public UsuarioAdministradorResponse buscarPorId(Integer id) {
        var usuario = findById(id);

        return UsuarioAdministradorResponse.of(usuario);
    }

    public Page<UsuarioAdministradorResponse> buscarUsuarios(Pageable pageable) {
        return repository.findAll(pageable)
                .map(UsuarioAdministradorResponse::of);
    }

    private void validarUsuarioExistente(UsuarioAdministradorRequest request) {
        validarEmailExistente(request.email());
    }

    private UsuarioAdministrador findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ValidationException("Usuario nao encontrado"));
    }

    private void validarEmailExistente(String email) {
        if (repository.existsByEmail(email)) {
            throw new ValidationException(MSG_USUARIO_JA_CADASTRADO);
        }
    }
}
