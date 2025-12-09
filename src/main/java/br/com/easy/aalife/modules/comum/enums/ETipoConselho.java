package br.com.easy.aalife.modules.comum.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ETipoConselho {
    CRN("Conselho Regional de Nutrição."),
    CRM("Conselho Regional de Medicina."),
    CRP("Conselho Regional de Psicologia."),
    CREF("Conselho Regional de Educação Física.");

    private final String descricao;
}
