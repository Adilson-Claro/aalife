package br.com.easy.aalife.config.auth.service;

import br.com.easy.aalife.config.auth.dto.LoginRequest;
import br.com.easy.aalife.config.auth.dto.LoginResponse;
import br.com.easy.aalife.config.exceptions.ValidationException;
import br.com.easy.aalife.config.auth.jwt.JwtTokenService;
import br.com.easy.aalife.modules.usuario.comum.model.Usuario;
import br.com.easy.aalife.modules.usuario.comum.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenService tokenService;

    public LoginResponse login(LoginRequest request) {
        var usuario = usuarioService.validarUsuario(request.email());
        usuarioService.validarUsuarioAtivo(usuario.getSituacao());
        validarSenha(usuario, request.senha());

        return new LoginResponse(tokenService.gerarToken(usuario));
    }

    private void validarSenha(Usuario usuario, String senhaDigitada) {
        if (!usuario.isLoginCorreto(senhaDigitada, passwordEncoder)) {
            throw new ValidationException("Senha inválida");
        }
    }
}
