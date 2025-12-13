package br.com.easy.aalife.modules.treino.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EGrupoMuscular {

    PEITORAL("Peitoral"),
    COSTAS("Costas"),
    OMBROS("Ombros"),
    BICEPS("Bíceps"),
    TRICEPS("Tríceps"),
    ANTEBRACO("Antebraço"),
    ABDOMEN("Abdômen"),
    GLUTEOS("Glúteos"),
    QUADRICEPS("Quadríceps"),
    POSTERIOR_DE_COXA("Posterior de Coxa"),
    PANTURRILHA("Panturrilha"),
    LOMBAR("Lombar"),
    TRAPEZIO("Trapézio"),
    ADUTORES("Adutores"),
    ABDUTORES("Abdutores"),
    CORE("Core"),
    CARDIO("Cardio");

    private final String descricao;
}
