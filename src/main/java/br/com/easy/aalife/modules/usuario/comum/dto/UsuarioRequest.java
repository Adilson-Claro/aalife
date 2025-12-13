package br.com.easy.aalife.modules.usuario.comum.dto;

import jakarta.validation.constraints.NotBlank;

public record UsuarioRequest(@NotBlank String email,
                             @NotBlank String senha,
                             @NotBlank String nome) {
}
