package br.com.easy.aalife.config.auth.security;

import br.com.easy.aalife.config.auth.jwt.JwtKeyProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

import javax.crypto.SecretKey;

@Configuration
@RequiredArgsConstructor
public class JwtDecoderConfig {

    private final JwtKeyProvider keyProvider;

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withSecretKey((SecretKey) keyProvider.getKey()).build();
    }
}
