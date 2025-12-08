package br.com.easy.aalife.config.auth.dto;

import br.com.easy.aalife.modules.comum.enums.ERole;

public record UsuarioLogado(Integer id,
                            String nome,
                            String email,
                            ERole role) {
}
