package br.com.easy.aalife.modules.comum.usuario.service;

import br.com.easy.aalife.config.exceptions.ValidationException;
import br.com.easy.aalife.modules.comum.usuario.dto.UsuarioBaseAtualizacaoRequest;
import br.com.easy.aalife.modules.comum.usuario.dto.UsuarioBaseRequest;
import br.com.easy.aalife.modules.comum.usuario.dto.UsuarioBaseResponse;
import br.com.easy.aalife.modules.comum.usuario.model.UsuarioBase;
import br.com.easy.aalife.modules.comum.usuario.repository.UsuarioBaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioBaseService {

    private final UsuarioBaseRepository repository;
    private final PasswordEncoder passwordEncoder;

    public void salvar(UsuarioBaseRequest request) {
        validarEmailExistente(request.email());
        var usuario = UsuarioBase.of(request);
        usuario.setSenha(passwordEncoder.encode(request.senha()));
        repository.save(usuario);
    }

    public void editar(Integer id, UsuarioBaseAtualizacaoRequest request) {
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

    public UsuarioBaseResponse buscarPorId(Integer id) {
        var usuario = findById(id);

        return UsuarioBaseResponse.of(usuario);
    }

    public Page<UsuarioBaseResponse> buscarUsuarios(Pageable pageable) {
        return repository.findAll(pageable)
                .map(UsuarioBaseResponse::of);
    }

    private UsuarioBase findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ValidationException("Usuario nao encontrado"));
    }

    private void validarEmailExistente(String email) {
        if (repository.existsByEmail(email)) {
            throw new ValidationException("Este usuario ja esta cadastrado.");
        }
    }
}
