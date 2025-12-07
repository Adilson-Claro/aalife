package br.com.easy.aalife.config.auth;

import br.com.easy.aalife.config.auth.dto.LoginRequest;
import br.com.easy.aalife.config.auth.dto.LoginResponse;
import br.com.easy.aalife.config.exceptions.ValidationException;
import br.com.easy.aalife.modules.comum.usuario.model.UsuarioBase;
import br.com.easy.aalife.modules.comum.usuario.service.UsuarioBaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioBaseService usuarioBaseService;
    private final PasswordEncoder passwordEncoder;
    private final JwtEncoder jwtEncoder;

    public LoginResponse login(LoginRequest request) {
        var usuario = usuarioBaseService.validarUsuario(request.email());
        usuarioBaseService.validarUsuarioAtivo(usuario.getSituacao());
        validarSenha(request.senha(), usuario);
        var claims = montarClaim(usuario);
        var token = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        return new LoginResponse(token);
    }

    private JwtClaimsSet montarClaim(UsuarioBase usuario) {
        var now = Instant.now();

        return JwtClaimsSet.builder()
                .issuer("api")
                .subject(usuario.getEmail())
                .issuedAt(now)
                .expiresAt(now.plus(24, ChronoUnit.HOURS))
                .claim("role", usuario.getRole().name())
                .build();
    }

    private void validarSenha(String password, UsuarioBase usuario) {
        if (!usuario.isLoginCorreto(password, passwordEncoder)) {
            throw new ValidationException("Senha inválida");
        }
    }
}
