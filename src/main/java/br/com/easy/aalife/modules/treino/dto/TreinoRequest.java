package br.com.easy.aalife.modules.treino.dto;

import br.com.easy.aalife.modules.treino.enums.EExercicio;
import br.com.easy.aalife.modules.treino.enums.EGrupoMuscular;

public record TreinoRequest(String nome,
                            Integer serie,
                            Integer peso,
                            EGrupoMuscular grupoMuscular,
                            EExercicio exercicio) {
}
