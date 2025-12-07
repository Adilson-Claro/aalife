package br.com.easy.aalife.modules.administrador.dto;

import jakarta.validation.constraints.NotBlank;

public record UsuarioAdministradorRequest(@NotBlank String email,
                                          @NotBlank String senha,
                                          @NotBlank String nome,
                                          @NotBlank String cpf) {
}
