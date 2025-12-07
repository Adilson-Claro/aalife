package br.com.easy.aalife.modules.usuario.dto;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record UsuarioBaseRequest(@NotBlank String email,
                                 @NotBlank String senha,
                                 @NotBlank String nome,
                                 Integer idade,
                                 BigDecimal peso,
                                 BigDecimal altura) {
}
