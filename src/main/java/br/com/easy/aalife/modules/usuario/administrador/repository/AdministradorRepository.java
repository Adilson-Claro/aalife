package br.com.easy.aalife.modules.usuario.administrador.repository;

import br.com.easy.aalife.modules.usuario.administrador.model.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministradorRepository extends JpaRepository<Administrador, Integer> {

    boolean existsByCpf(String cpf);

    boolean existsByTelefone(String telefone);

    boolean existsByTelefoneAndIdNot(String telefone, Integer id);
}
