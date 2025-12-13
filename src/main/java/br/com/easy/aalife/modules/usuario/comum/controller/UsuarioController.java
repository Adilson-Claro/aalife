package br.com.easy.aalife.modules.usuario.comum.controller;

import br.com.easy.aalife.modules.usuario.comum.controller.contract.IUsuarioController;
import br.com.easy.aalife.modules.usuario.comum.dto.*;
import br.com.easy.aalife.modules.usuario.comum.service.UsuarioService;
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
    public void editar(Integer id, UsuarioRequest request) {
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
    public Page<UsuarioResponse> buscarUsuarios(Pageable pageable) {
        return service.buscarUsuarios(pageable);
    }
}
