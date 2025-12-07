package br.com.easy.aalife.config.auth;

import br.com.easy.aalife.config.auth.dto.LoginRequest;
import br.com.easy.aalife.config.auth.dto.LoginResponse;
import br.com.easy.aalife.config.exceptions.ValidationException;
import br.com.easy.aalife.modules.usuario.model.UsuarioBase;
import br.com.easy.aalife.modules.usuario.service.UsuarioBaseService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioBaseService usuarioBaseService;
    private final PasswordEncoder passwordEncoder;

    @Value("${jwt.secret}")
    private String secret;

    public LoginResponse login(LoginRequest request) {
        var usuario = usuarioBaseService.validarUsuario(request.email());
        usuarioBaseService.validarUsuarioAtivo(usuario.getSituacao());
        validarSenha(request.senha(), usuario);

        var token = gerarToken(usuario);
        return new LoginResponse(token);
    }

    private String gerarToken(UsuarioBase usuario) {
        var now = Instant.now();
        var expiration = now.plus(24, ChronoUnit.HOURS);
        var key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .setIssuer("api")
                .setSubject(usuario.getEmail())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(expiration))
                .claim("role", usuario.getRole().name())
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    private void validarSenha(String password, UsuarioBase usuario) {
        if (!usuario.isLoginCorreto(password, passwordEncoder)) {
            throw new ValidationException("Senha inválida");
        }
    }
}
