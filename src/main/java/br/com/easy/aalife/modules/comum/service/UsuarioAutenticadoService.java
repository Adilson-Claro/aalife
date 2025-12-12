package br.com.easy.aalife.modules.comum.service;

import br.com.easy.aalife.config.auth.dto.UsuarioLogado;
import br.com.easy.aalife.config.exceptions.ValidationException;
import br.com.easy.aalife.modules.comum.enums.ERole;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
public class UsuarioAutenticadoService {

    public UsuarioLogado getUsuarioLogado() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new ValidationException("Usuário não autenticado.");
        }

        var jwtToken = (Jwt) authentication.getPrincipal();

        return new UsuarioLogado(
                ((Number) jwtToken.getClaim("id")).intValue(),
                jwtToken.getClaimAsString("nome"),
                jwtToken.getSubject(),
                ERole.valueOf(jwtToken.getClaimAsString("roles").replaceAll("[\\[\\]]",""))
        );
    }

    public void validarAdministrador() {
        var usuario = getUsuarioLogado();
        if (!ERole.ADMINISTRADOR.equals(usuario.role())) {
            throw new ValidationException("Acesso negado.");
        }
    }
}
