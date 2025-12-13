package br.com.easy.aalife.config.auth.jwt;

import br.com.easy.aalife.modules.usuario.comum.model.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JwtTokenService {

    private final JwtKeyProvider keyProvider;
    private final JwtProperties properties;

    public String gerarToken(Usuario usuario) {
        var now = Instant.now();
        var expiration = now.plus(properties.getExpirationHours(), ChronoUnit.HOURS);

        return Jwts.builder()
                .setIssuer("api")
                .setSubject(usuario.getEmail())
                .claim("roles", List.of(usuario.getRole().name()))
                .claim("nome", usuario.getNome())
                .claim("id", usuario.getId())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(expiration))
                .signWith(keyProvider.getKey(), SignatureAlgorithm.HS256)
                .compact();
    }
}
