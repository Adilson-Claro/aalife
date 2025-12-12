package br.com.easy.aalife.config.auth.jwt;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.Key;

@Getter
@Component
@RequiredArgsConstructor
public class JwtKeyProvider {

    private final JwtProperties properties;
    private Key key;

    @PostConstruct
    public void init() {
        var keyBytes = Decoders.BASE64.decode(properties.getSecret());
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }
}
