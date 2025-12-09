package br.com.easy.aalife.modules.usuario.controller;

import br.com.easy.aalife.modules.usuario.controller.contract.IUsuarioController;
import br.com.easy.aalife.modules.usuario.dto.*;
import br.com.easy.aalife.modules.usuario.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/usuario")
public class UsuarioController implements IUsuarioController {

    private final UsuarioService service;

    @Override
    public void salvar(UsuarioRequest request) {
        service.salvar(request);
    }

    @Override
    public void editar(Integer id, UsuarioAtualizacaoRequest request) {
        service.editar(id, request);
    }

    @Override
    public void alterarSituacao(Integer id) {
        service.alterarSituacao(id);
    }

    @Override
    public UsuarioResponse buscarPorId(Integer id) {
        return service.buscarPorId(id);
    }

    @Override
    public Page<ProfissionalResponse> buscarProfissional(Integer id, Pageable pageable) {
        return service.buscarProfissional(id, pageable);
    }

    @Override
    public Page<AdministradorResponse> buscarAdministrador(Integer id, Pageable pageable) {
        return service.buscarAdministrador(id, pageable);
    }

    @Override
    public Page<UsuarioResponse> buscarUsuarios(Pageable pageable) {
        return service.buscarUsuarios(pageable);
    }
}
