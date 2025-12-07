package br.com.easy.aalife.modules.usuario.service;

import br.com.easy.aalife.config.exceptions.NotFoundException;
import br.com.easy.aalife.config.exceptions.ValidationException;
import br.com.easy.aalife.modules.comum.enums.ESituacao;
import br.com.easy.aalife.modules.usuario.dto.UsuarioBaseAtualizacaoRequest;
import br.com.easy.aalife.modules.usuario.dto.UsuarioBaseRequest;
import br.com.easy.aalife.modules.usuario.dto.UsuarioBaseResponse;
import br.com.easy.aalife.modules.usuario.model.UsuarioBase;
import br.com.easy.aalife.modules.usuario.repository.UsuarioBaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static br.com.easy.aalife.modules.comum.util.ConstantsUtils.EX_USUARIO_NAO_ENCONTRADO;
import static br.com.easy.aalife.modules.comum.util.ConstantsUtils.MSG_USUARIO_JA_CADASTRADO;

@Service
@RequiredArgsConstructor
public class UsuarioBaseService {

    private final UsuarioBaseRepository repository;
    private final PasswordEncoder passwordEncoder;

    public void salvar(UsuarioBaseRequest request) {
        validarEmailExistente(request.email());
        var usuario = UsuarioBase.of(request, passwordEncoder);
        repository.save(usuario);
    }

    public void editar(Integer id, UsuarioBaseAtualizacaoRequest request) {
        var usuario = findById(id);
        validarUsuarioAtivo(usuario.getSituacao());
        validarEmailParaAtualizar(request.email(), id);

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

    public void validarUsuarioAtivo(ESituacao situacao) {
        if (situacao != ESituacao.A) {
            throw new ValidationException("Nao e possivel fazer login com um usuario inativo.");
        }
    }

    public UsuarioBase validarUsuario(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException(EX_USUARIO_NAO_ENCONTRADO));
    }

    private void validarEmailParaAtualizar(String email, Integer id) {
        if (repository.existsByEmailAndIdNot(email, id)) {
            throw new ValidationException(MSG_USUARIO_JA_CADASTRADO);
        }
    }

    private UsuarioBase findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ValidationException("Usuario nao encontrado"));
    }

    private void validarEmailExistente(String email) {
        if (repository.existsByEmail(email)) {
            throw new ValidationException(MSG_USUARIO_JA_CADASTRADO);
        }
    }
}
