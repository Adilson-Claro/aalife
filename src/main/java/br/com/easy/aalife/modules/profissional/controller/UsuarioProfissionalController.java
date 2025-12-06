package br.com.easy.aalife.modules.profissional.controller;

import br.com.easy.aalife.modules.profissional.controller.contract.IUsuarioProfissionalController;
import br.com.easy.aalife.modules.profissional.dto.UsuarioProfissionalAtualizacaoRequest;
import br.com.easy.aalife.modules.profissional.dto.UsuarioProfissionalRequest;
import br.com.easy.aalife.modules.profissional.dto.UsuarioProfissionalResponse;
import br.com.easy.aalife.modules.profissional.service.UsuarioProfissionalService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/profissional")
public class UsuarioProfissionalController implements IUsuarioProfissionalController {

    private final UsuarioProfissionalService service;

    @Override
    public void salvar(UsuarioProfissionalRequest request) {
        service.salvar(request);
    }

    @Override
    public void editar(Integer id, UsuarioProfissionalAtualizacaoRequest request) {
        service.editar(id, request);
    }

    @Override
    public void alterarSituacao(Integer id) {
        service.alterarSituacao(id);
    }

    @Override
    public UsuarioProfissionalResponse buscarPorId(Integer id) {
        return service.buscarPorId(id);
    }

    @Override
    public Page<UsuarioProfissionalResponse> buscarUsuarios(Pageable pageable) {
        return service.buscarUsuarios(pageable);
    }
}
