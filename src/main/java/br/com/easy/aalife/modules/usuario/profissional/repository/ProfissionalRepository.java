package br.com.easy.aalife.modules.usuario.profissional.repository;

import br.com.easy.aalife.modules.comum.enums.ETipoConselho;
import br.com.easy.aalife.modules.usuario.profissional.model.Profissional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfissionalRepository extends JpaRepository<Profissional, Integer> {

    Page<Profissional> findByTipoConselho(ETipoConselho tipoConselho, Pageable pageable);

    boolean existsByCpf(String cpf);

    boolean existsByTelefone(String telefone);

    boolean existsByTelefoneAndIdNot(String telefone, Integer id);

    boolean existsByNumeroConselho(String numeroConselho);
}
