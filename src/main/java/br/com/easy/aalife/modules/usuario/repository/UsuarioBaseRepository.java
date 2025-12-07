package br.com.easy.aalife.modules.usuario.repository;

import br.com.easy.aalife.modules.usuario.model.UsuarioBase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioBaseRepository extends JpaRepository<UsuarioBase, Integer> {

    Optional<UsuarioBase> findByEmail(String email);

    boolean existsByEmailAndIdNot(String email, Integer id);

    boolean existsByEmail(String email);
}
