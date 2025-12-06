package br.com.easy.aalife.modules.comum.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ERole {

    ADMINISTRADOR("Administrador"),
    BASICO("Usuário básico"),
    PROFISSIONAL("Usuário profissional");

    private final String descricao;
}