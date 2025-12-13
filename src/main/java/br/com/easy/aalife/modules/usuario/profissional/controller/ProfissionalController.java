package br.com.easy.aalife.modules.usuario.profissional.controller;

import br.com.easy.aalife.modules.comum.enums.ETipoConselho;
import br.com.easy.aalife.modules.usuario.profissional.controller.contract.IProfissionalController;
import br.com.easy.aalife.modules.usuario.profissional.dto.ProfissionalAtualizacaoRequest;
import br.com.easy.aalife.modules.usuario.profissional.dto.ProfissionalRequest;
import br.com.easy.aalife.modules.usuario.profissional.dto.ProfissionalResponse;
import br.com.easy.aalife.modules.usuario.profissional.service.ProfissionalService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/profissional")
public class ProfissionalController implements IProfissionalController {

    private final ProfissionalService service;

    @Override
    public void salvar(ProfissionalRequest request) {
        service.salvar(request);
    }

    @Override
    public void editar(Integer id, ProfissionalAtualizacaoRequest request) {
        service.editar(id, request);
    }

    @Override
    public Page<ProfissionalResponse> findProfissionais(ETipoConselho tipoConselho, Pageable pageable) {
        return service.findProfissionais(tipoConselho, pageable);
    }
}
