package br.com.easy.aalife.config.auth;

import br.com.easy.aalife.config.auth.dto.UsuarioLogado;
import br.com.easy.aalife.modules.comum.enums.ERole;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
public class UsuarioAutenticado {

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
}
