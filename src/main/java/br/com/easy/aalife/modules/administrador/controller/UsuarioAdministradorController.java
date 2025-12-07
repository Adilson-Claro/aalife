package br.com.easy.aalife.modules.administrador.controller;

import br.com.easy.aalife.modules.administrador.controller.contract.IUsuarioAdministradorController;
import br.com.easy.aalife.modules.administrador.dto.UsuarioAdministradorAtualizacaoRequest;
import br.com.easy.aalife.modules.administrador.dto.UsuarioAdministradorRequest;
import br.com.easy.aalife.modules.administrador.dto.UsuarioAdministradorResponse;
import br.com.easy.aalife.modules.administrador.service.UsuarioAdministradorService;
import br.com.easy.aalife.modules.profissional.dto.UsuarioProfissionalAtualizacaoRequest;
import br.com.easy.aalife.modules.profissional.dto.UsuarioProfissionalRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/administrador")
@RequiredArgsConstructor
public class UsuarioAdministradorController implements IUsuarioAdministradorController {

    private final UsuarioAdministradorService service;

    @Override
    public void salvar(UsuarioAdministradorRequest request) {
        service.salvar(request);
    }

    @Override
    public void editar(Integer id, UsuarioAdministradorAtualizacaoRequest request) {
        service.editar(id, request);
    }

    @Override
    public void alterarSituacao(Integer id) {
        service.alterarSituacao(id);
    }

    @Override
    public UsuarioAdministradorResponse buscarPorId(Integer id) {
        return service.buscarPorId(id);
    }

    @Override
    public Page<UsuarioAdministradorResponse> buscarUsuarios(Pageable pageable) {
        return service.buscarUsuarios(pageable);
    }
}
