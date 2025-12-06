package br.com.easy.aalife.modules.comum.usuario.controller;

import br.com.easy.aalife.modules.comum.usuario.controller.contract.IUsuarioBaseController;
import br.com.easy.aalife.modules.comum.usuario.dto.UsuarioBaseAtualizacaoRequest;
import br.com.easy.aalife.modules.comum.usuario.dto.UsuarioBaseRequest;
import br.com.easy.aalife.modules.comum.usuario.dto.UsuarioBaseResponse;
import br.com.easy.aalife.modules.comum.usuario.service.UsuarioBaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/usuario")
public class UsuarioBaseController implements IUsuarioBaseController {

    private final UsuarioBaseService service;

    @Override
    public void salvar(UsuarioBaseRequest request) {
        service.salvar(request);
    }

    @Override
    public void editar(Integer id, UsuarioBaseAtualizacaoRequest request) {
        service.editar(id, request);
    }

    @Override
    public void alterarSituacao(Integer id) {
        service.alterarSituacao(id);
    }

    @Override
    public UsuarioBaseResponse buscarPorId(Integer id) {
        return service.buscarPorId(id);
    }

    @Override
    public Page<UsuarioBaseResponse> buscarUsuarios(Pageable pageable) {
        return service.buscarUsuarios(pageable);
    }
}
