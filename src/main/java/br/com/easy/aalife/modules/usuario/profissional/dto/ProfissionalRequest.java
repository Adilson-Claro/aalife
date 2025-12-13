package br.com.easy.aalife.modules.usuario.profissional.dto;

import br.com.easy.aalife.modules.comum.enums.ETipoConselho;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProfissionalRequest(@NotBlank String nome,
                                  @NotBlank String email,
                                  @NotBlank String senha,
                                  @NotBlank String numeroConselho,
                                  String cpf,
                                  String cnpj,
                                  @NotNull ETipoConselho tipoConselho,
                                  @NotBlank String telefone) {
}
