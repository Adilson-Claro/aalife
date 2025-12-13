package br.com.easy.aalife.modules.treino.repository;

import br.com.easy.aalife.modules.treino.model.Treino;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreinoRepository extends JpaRepository<Treino, Integer> {
}
