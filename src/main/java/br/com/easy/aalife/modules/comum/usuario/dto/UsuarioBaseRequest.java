package br.com.easy.aalife.modules.comum.usuario.dto;

import jakarta.validation.constraints.NotBlank;

public record UsuarioBaseRequest(@NotBlank String email,
                                 @NotBlank String senha,
                                 @NotBlank String nome) {
}
