package br.com.easy.aalife.modules.administrador.controller.contract;

import br.com.easy.aalife.modules.administrador.dto.UsuarioAdministradorAtualizacaoRequest;
import br.com.easy.aalife.modules.administrador.dto.UsuarioAdministradorRequest;
import br.com.easy.aalife.modules.administrador.dto.UsuarioAdministradorResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

public interface IUsuarioAdministradorController {

    @PostMapping("/cadastrar")
    @Operation(summary = "Endpoint responsavel pelo cadastro de profissional")
    void salvar(@RequestBody @Valid UsuarioAdministradorRequest request);

    @PutMapping("/{id}/editar")
    @Operation(summary = "Endpoint responsavel pela edicao de profissional")
    void editar(@PathVariable Integer id, @RequestBody UsuarioAdministradorAtualizacaoRequest request);

    @PutMapping("/{id}/alterar-situacao")
    @Operation(summary = "Endpoint responsavel por alterar a situacao de um profissional")
    void alterarSituacao(@PathVariable Integer id);

    @GetMapping("/{id}")
    @Operation(summary = "Endpoint responsavel por buscar profissionais por id")
    UsuarioAdministradorResponse buscarPorId(@PathVariable Integer id);

    @GetMapping
    @Operation(summary = "Endpoint responsavel por buscar profissionai")
    Page<UsuarioAdministradorResponse> buscarUsuarios(Pageable pageable);
}
