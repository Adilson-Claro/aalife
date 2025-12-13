package br.com.easy.aalife.modules.usuario.administrador.dto;

import jakarta.validation.constraints.NotBlank;

public record AdministradorRequest(@NotBlank String email,
                                   @NotBlank String senha,
                                   @NotBlank String nome,
                                   @NotBlank String cpf,
                                   @NotBlank String telefone) {
}
