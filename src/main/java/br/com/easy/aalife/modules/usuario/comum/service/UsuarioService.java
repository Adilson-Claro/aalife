package br.com.easy.aalife.modules.usuario.comum.service;

import br.com.easy.aalife.config.exceptions.NotFoundException;
import br.com.easy.aalife.config.exceptions.ValidationException;
import br.com.easy.aalife.modules.comum.enums.ESituacao;
import br.com.easy.aalife.modules.usuario.comum.dto.UsuarioRequest;
import br.com.easy.aalife.modules.usuario.comum.dto.UsuarioResponse;
import br.com.easy.aalife.modules.usuario.comum.model.Usuario;
import br.com.easy.aalife.modules.usuario.comum.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static br.com.easy.aalife.modules.comum.util.ConstantsUtils.EX_USUARIO_NAO_ENCONTRADO;
import static br.com.easy.aalife.modules.comum.util.ConstantsUtils.MSG_USUARIO_JA_CADASTRADO;
import static br.com.easy.aalife.modules.comum.util.EmailUtils.validarEmail;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;

    public void salvar(UsuarioRequest request) {
        validarEmail(request.email());
        validarEmailExistente(request.email());
        var usuario = Usuario.ofComum(request, passwordEncoder);

        repository.save(usuario);
    }

    public void editar(Integer id, UsuarioRequest request) {
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

    public UsuarioResponse buscarPorId(Integer id) {
        var usuario = findById(id);

        return UsuarioResponse.of(usuario);
    }

    public Page<UsuarioResponse> buscarUsuarios(Pageable pageable) {
        return repository.findAll(pageable)
                .map(UsuarioResponse::of);
    }

    public void validarUsuarioAtivo(ESituacao situacao) {
        if (situacao != ESituacao.A) {
            throw new ValidationException("Não é possível fazer login com um usuário inativo.");
        }
    }

    public Usuario validarUsuario(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException(EX_USUARIO_NAO_ENCONTRADO));
    }

    public void validarEmailParaAtualizar(String email, Integer id) {
        if (repository.existsByEmailAndIdNot(email, id)) {
            throw new ValidationException(MSG_USUARIO_JA_CADASTRADO);
        }
    }

    public Usuario findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ValidationException(EX_USUARIO_NAO_ENCONTRADO));
    }

    private void validarEmailExistente(String email) {
        if (repository.existsByEmail(email)) {
            throw new ValidationException(MSG_USUARIO_JA_CADASTRADO);
        }
    }
}
