package br.com.easy.aalife.modules.profissional.service;

import br.com.easy.aalife.config.exceptions.ValidationException;
import br.com.easy.aalife.modules.profissional.dto.UsuarioProfissionalAtualizacaoRequest;
import br.com.easy.aalife.modules.profissional.dto.UsuarioProfissionalRequest;
import br.com.easy.aalife.modules.profissional.dto.UsuarioProfissionalResponse;
import br.com.easy.aalife.modules.profissional.model.UsuarioProfissional;
import br.com.easy.aalife.modules.profissional.repository.UsuarioProfissionalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioProfissionalService {

    private final UsuarioProfissionalRepository repository;
    private final PasswordEncoder passwordEncoder;

    public void salvar(UsuarioProfissionalRequest request) {
        validarUsuarioExistente(request);
        var usuario = UsuarioProfissional.of(request, passwordEncoder);
        repository.save(usuario);
    }

    public void editar(Integer id, UsuarioProfissionalAtualizacaoRequest request) {
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

    public UsuarioProfissionalResponse buscarPorId(Integer id) {
        var usuario = findById(id);

        return UsuarioProfissionalResponse.of(usuario);
    }

    public Page<UsuarioProfissionalResponse> buscarUsuarios(Pageable pageable) {
        return repository.findAll(pageable)
                .map(UsuarioProfissionalResponse::of);
    }

    private void validarUsuarioExistente(UsuarioProfissionalRequest request) {
        validarEmailExistente(request.email());
        valdiarNumeroConselhoExistente(request.numeroConselho());
    }

    private UsuarioProfissional findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ValidationException("Usuario nao encontrado"));
    }

    private void valdiarNumeroConselhoExistente(String numeroConselho) {
        if (repository.existsByNumeroConselho(numeroConselho)) {
            throw new ValidationException("Este usuario ja esta cadastrado");
        }
    }

    private void validarEmailExistente(String email) {
        if (repository.existsByEmail(email)) {
            throw new ValidationException("Este usuario ja esta cadastrado.");
        }
    }
}
