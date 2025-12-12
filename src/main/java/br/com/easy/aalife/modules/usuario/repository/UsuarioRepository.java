package br.com.easy.aalife.modules.usuario.repository;

import br.com.easy.aalife.modules.comum.enums.ETipoUsuario;
import br.com.easy.aalife.modules.usuario.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByEmail(String email);

    boolean existsByEmailAndIdNot(String email, Integer id);

    boolean existsByEmail(String email);

    boolean existsByNumeroConselho(Integer numeroConselho);

    Page<Usuario> findById(Integer id, Pageable pageable);

    List<Usuario> findByTipoUsuario(ETipoUsuario tipoUsuario);
}
