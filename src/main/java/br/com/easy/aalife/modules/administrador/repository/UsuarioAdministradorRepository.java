package br.com.easy.aalife.modules.administrador.repository;

import br.com.easy.aalife.modules.administrador.model.UsuarioAdministrador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioAdministradorRepository extends JpaRepository<UsuarioAdministrador, Integer> {

    boolean existsByEmail(String email);
}
