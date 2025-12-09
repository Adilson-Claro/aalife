package br.com.easy.aalife.modules.comum.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ETipoUsuario {

    COMUM("Usuario comum"),
    ADMINISTRADOR("Administrador"),
    PROFISSIONAL("Profissional de saude");

    private final String descricao;
}
