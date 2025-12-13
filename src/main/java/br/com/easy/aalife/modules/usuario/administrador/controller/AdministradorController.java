package br.com.easy.aalife.modules.usuario.administrador.controller;

import br.com.easy.aalife.modules.usuario.administrador.controller.contract.IAdministradorController;
import br.com.easy.aalife.modules.usuario.administrador.dto.AdministradorAtualizacaoRequest;
import br.com.easy.aalife.modules.usuario.administrador.dto.AdministradorRequest;
import br.com.easy.aalife.modules.usuario.administrador.dto.AdministradorResponse;
import br.com.easy.aalife.modules.usuario.administrador.service.AdministradorService;
import br.com.easy.aalife.modules.usuario.comum.dto.UsuarioRequest;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/administrador")
public class AdministradorController implements IAdministradorController {

    private final AdministradorService service;

    @Override
    public void salvar(AdministradorRequest request) {
        service.salvar(request);
    }

    @Override
    public void editar(Integer id, AdministradorAtualizacaoRequest request) {
        service.editar(id, request);
    }
}
