package br.com.easy.aalife.config.auth;

import br.com.easy.aalife.config.auth.dto.LoginRequest;
import br.com.easy.aalife.config.auth.dto.LoginResponse;
import br.com.easy.aalife.config.auth.dto.UsuarioLogado;
import br.com.easy.aalife.config.exceptions.ValidationException;
import br.com.easy.aalife.modules.comum.enums.ERole;
import br.com.easy.aalife.modules.usuario.model.Usuario;
import br.com.easy.aalife.modules.usuario.service.UsuarioService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;

    @Value("${jwt.secret}")
    private String secret;

    public LoginResponse login(LoginRequest request) {
        var usuario = usuarioService.validarUsuario(request.email());
        usuarioService.validarUsuarioAtivo(usuario.getSituacao());
        validarSenha(request.senha(), usuario);

        var token = gerarToken(usuario);
        return new LoginResponse(token);
    }

    public static UsuarioLogado getUsuarioLogado() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var jwt = (Jwt) authentication.getPrincipal();

        return new UsuarioLogado(
                ((Number) jwt.getClaim("id")).intValue(),
                jwt.getClaimAsString("nome"),
                jwt.getSubject(),
                ERole.valueOf(jwt.getClaimAsString("role"))
        );
    }

    private String gerarToken(Usuario usuario) {
        var now = Instant.now();
        var expiration = now.plus(24, ChronoUnit.HOURS);
        var key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .setIssuer("api")
                .setSubject(usuario.getEmail())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(expiration))
                .claim("role", usuario.getRole().name())
                .claim("nome", usuario.getNome())
                .claim("id", usuario.getId())
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    private void validarSenha(String password, Usuario usuario) {
        if (!usuario.isLoginCorreto(password, passwordEncoder)) {
            throw new ValidationException("Senha inválida");
        }
    }

    public static void validarAdministrador(UsuarioLogado usuario) {
        if (!ERole.ADMINISTRADOR.equals(usuario.role())) {
            throw new ValidationException("Acesso negado.");
        }
    }
}
