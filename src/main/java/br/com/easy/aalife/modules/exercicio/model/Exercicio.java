package br.com.easy.aalife.modules.exercicio.model;

import br.com.easy.aalife.modules.exercicio.dto.ExercicioRequest;
import br.com.easy.aalife.modules.exercicio.enums.EExercicio;
import br.com.easy.aalife.modules.exercicio.enums.EGrupoMuscular;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "exercicio")
public class Exercicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    private Integer serie;

    private Integer peso;

    @Enumerated(EnumType.STRING)
    @Column(name = "grupo_muscular")
    private EGrupoMuscular grupoMuscular;

    @Enumerated(EnumType.STRING)
    @Column(name = "exercicio")
    private EExercicio exercicio;

    private String imagemExercicio;

    public static Exercicio of(ExercicioRequest request, String imagemExercicio) {
        return Exercicio.builder()
                .nome(request.nome())
                .serie(request.serie())
                .peso(request.peso())
                .grupoMuscular(request.grupoMuscular())
                .exercicio(request.exercicio())
                .imagemExercicio(imagemExercicio)
                .build();
    }
}
