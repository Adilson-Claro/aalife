package br.com.easy.aalife.modules.usuario.service;

import br.com.easy.aalife.config.exceptions.NotFoundException;
import br.com.easy.aalife.config.exceptions.ValidationException;
import br.com.easy.aalife.modules.comum.enums.ERole;
import br.com.easy.aalife.modules.comum.enums.ESituacao;
import br.com.easy.aalife.modules.comum.enums.ETipoUsuario;
import br.com.easy.aalife.modules.usuario.dto.*;
import br.com.easy.aalife.modules.usuario.model.Usuario;
import br.com.easy.aalife.modules.usuario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static br.com.easy.aalife.modules.comum.enums.ETipoUsuario.COMUM;
import static br.com.easy.aalife.modules.comum.enums.ETipoUsuario.PROFISSIONAL;
import static br.com.easy.aalife.modules.comum.util.ConstantsUtils.EX_USUARIO_NAO_ENCONTRADO;
import static br.com.easy.aalife.modules.comum.util.ConstantsUtils.MSG_USUARIO_JA_CADASTRADO;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;

    public void salvar(UsuarioRequest request) {
        validarCamposPorTipoUsuario(request);
        validarEmailExistente(request.email());
        validarNumeroConselhoExistente(request);
        var usuario = criarUsuario(request);

        repository.save(usuario);
    }

    public void editar(Integer id, UsuarioAtualizacaoRequest request) {
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

    public Page<AdministradorResponse> buscarAdministrador(Integer id, Pageable pageable) {
        return repository.findById(id, pageable)
                .map(AdministradorResponse::of);
    }

    public Page<ProfissionalResponse> buscarProfissional(Integer id, Pageable pageable) {
        return repository.findById(id, pageable)
                .map(ProfissionalResponse::of);
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

    private Usuario criarUsuario(UsuarioRequest request) {
        var role = verificarTipoUsuario(request.tipoUsuario());
        var usuario = Usuario.of(request, passwordEncoder);
        usuario.setRole(role);
        return usuario;
    }

    private void validarCamposPorTipoUsuario(UsuarioRequest request) {
        validarNumeroConselho(request);
        validarTipoConselho(request);
        validarCpfParaAdministradorOuProfissional(request);
    }

    private void validarTipoConselho(UsuarioRequest request) {
        if (request.tipoUsuario() == PROFISSIONAL && request.tipoConselho() == null) {
            throw new ValidationException("Tipo de conselho é obrigatório.");
        }
    }

    private void validarNumeroConselho(UsuarioRequest request) {
        if (request.tipoUsuario() == PROFISSIONAL && request.numeroConselho() == null) {
            throw new ValidationException("Número do conselho é obrigatório.");
        }
    }

    private void validarCpfParaAdministradorOuProfissional(UsuarioRequest request) {
        if (!request.tipoUsuario().equals(COMUM) &&
                (request.cpf() == null || request.cpf().isBlank())) {
            throw new ValidationException("CPF é obrigatório.");
        }
    }

    private ERole verificarTipoUsuario(ETipoUsuario tipoUsuario) {
        return switch (tipoUsuario) {
            case PROFISSIONAL -> ERole.PROFISSIONAL;
            case ADMINISTRADOR -> ERole.ADMINISTRADOR;
            default -> ERole.COMUM;
        };
    }

    private void validarEmailParaAtualizar(String email, Integer id) {
        if (repository.existsByEmailAndIdNot(email, id)) {
            throw new ValidationException(MSG_USUARIO_JA_CADASTRADO);
        }
    }

    private void validarNumeroConselhoExistente(UsuarioRequest request) {
        if (request.tipoUsuario() == PROFISSIONAL
                && repository.existsByNumeroConselho(request.numeroConselho())) {

            throw new ValidationException(MSG_USUARIO_JA_CADASTRADO);
        }
    }

    private Usuario findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ValidationException("Usuario nao encontrado"));
    }

    private void validarEmailExistente(String email) {
        if (repository.existsByEmail(email)) {
            throw new ValidationException(MSG_USUARIO_JA_CADASTRADO);
        }
    }
}
