package br.com.easy.aalife.modules.comum.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ESituacao {

    A("Ativo"),
    I("Inativo");

    private final String situacao;
}
