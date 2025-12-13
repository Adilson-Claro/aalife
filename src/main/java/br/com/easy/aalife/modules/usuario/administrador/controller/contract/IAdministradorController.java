package br.com.easy.aalife.modules.usuario.administrador.controller.contract;

import br.com.easy.aalife.modules.usuario.administrador.dto.AdministradorAtualizacaoRequest;
import br.com.easy.aalife.modules.usuario.administrador.dto.AdministradorRequest;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface IAdministradorController {

    @PostMapping("/cadastrar")
    @Operation(summary = "Endpoint responsavel pelo cadastro de usuario comum.")
    void salvar(@RequestBody @Valid AdministradorRequest request);

    @PutMapping("/{id}/editar")
    @Operation(summary = "Endpoint responsavel pela edicao de usuarios")
    void editar(@PathVariable Integer id, @RequestBody AdministradorAtualizacaoRequest request);
}
