package br.com.easy.aalife.modules.usuario.comum.repository;

import br.com.easy.aalife.modules.usuario.comum.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByEmail(String email);

    boolean existsByEmailAndIdNot(String email, Integer id);

    boolean existsByEmail(String email);
}
