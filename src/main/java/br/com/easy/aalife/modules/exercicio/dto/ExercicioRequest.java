package br.com.easy.aalife.modules.exercicio.dto;

import br.com.easy.aalife.modules.exercicio.enums.EExercicio;
import br.com.easy.aalife.modules.exercicio.enums.EGrupoMuscular;

public record ExercicioRequest(String nome,
                               Integer serie,
                               Integer peso,
                               EGrupoMuscular grupoMuscular,
                               EExercicio exercicio) {
}
