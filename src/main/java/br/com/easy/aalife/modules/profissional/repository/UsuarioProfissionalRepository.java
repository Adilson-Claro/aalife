package br.com.easy.aalife.modules.profissional.repository;

import br.com.easy.aalife.modules.profissional.model.UsuarioProfissional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioProfissionalRepository extends JpaRepository<UsuarioProfissional, Integer> {

    boolean existsByEmail(String email);

    boolean existsByNumeroConselho(String numeroConselho);
}
