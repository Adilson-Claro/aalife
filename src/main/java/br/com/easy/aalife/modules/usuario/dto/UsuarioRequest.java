package br.com.easy.aalife.modules.usuario.dto;

import br.com.easy.aalife.modules.comum.enums.ETipoConselho;
import br.com.easy.aalife.modules.comum.enums.ETipoUsuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record UsuarioRequest(@NotBlank String email,
                             @NotBlank String senha,
                             @NotBlank String nome,
                             Integer idade,
                             BigDecimal peso,
                             BigDecimal altura,
                             Integer numeroConselho,
                             String cpf,
                             ETipoConselho tipoConselho,
                             @NotNull ETipoUsuario tipoUsuario) {
}
