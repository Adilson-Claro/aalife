package br.com.easy.aalife.config.auth;

import br.com.easy.aalife.config.auth.dto.LoginRequest;
import br.com.easy.aalife.config.auth.dto.LoginResponse;
import br.com.easy.aalife.config.exceptions.NotFoundException;
import br.com.easy.aalife.config.exceptions.ValidationException;
import br.com.easy.aalife.modules.comum.usuario.dto.UsuarioBaseRequest;
import br.com.easy.aalife.modules.comum.usuario.model.UsuarioBase;
import br.com.easy.aalife.modules.comum.usuario.repository.UsuarioBaseRepository;
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

    private final UsuarioBaseRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtEncoder jwtEncoder;

    public void registrar(UsuarioBaseRequest request) {
        validarUsuarioExistente(request.username());
        var usuario = UsuarioBase.of(request);
        usuario.setPassword(passwordEncoder.encode(request.password()));
        repository.save(usuario);
    }

    public LoginResponse login(LoginRequest request) {
        var usuario = validarUsuarioNaoEncontrado(request.username());
        validarSenha(request.password(), usuario);
        var claims = montarClaim(usuario);
        var token = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        return new LoginResponse(token);
    }

    private JwtClaimsSet montarClaim(UsuarioBase usuario) {
        var now = Instant.now();

        return JwtClaimsSet.builder()
                .issuer("api")
                .subject(usuario.getUsername())
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

    private UsuarioBase validarUsuarioNaoEncontrado(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
    }

    private void validarUsuarioExistente(String username) {
        if (repository.existsByUsername(username)) {
            throw new ValidationException("Este usuário já existe.");
        }
    }
}
