package br.com.easy.aalife.modules.profissional.dto;

import jakarta.validation.constraints.NotBlank;

public record UsuarioProfissionalRequest(@NotBlank String numeroConselho,
                                         @NotBlank String email,
                                         @NotBlank String senha,
                                         @NotBlank String nome,
                                         @NotBlank String cpf) {
}
