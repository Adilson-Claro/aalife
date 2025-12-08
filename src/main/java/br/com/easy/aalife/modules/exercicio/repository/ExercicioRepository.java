package br.com.easy.aalife.modules.exercicio.repository;

import br.com.easy.aalife.modules.exercicio.model.Exercicio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExercicioRepository extends JpaRepository<Exercicio, Integer> {
}
