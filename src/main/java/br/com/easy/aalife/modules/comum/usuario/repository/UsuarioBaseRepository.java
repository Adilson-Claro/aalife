package br.com.easy.aalife.modules.comum.usuario.repository;

import br.com.easy.aalife.modules.comum.usuario.model.UsuarioBase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioBaseRepository extends JpaRepository<UsuarioBase, Integer> {

    Optional<UsuarioBase> findByEmail(String username);

    boolean existsByEmail(String username);
}
