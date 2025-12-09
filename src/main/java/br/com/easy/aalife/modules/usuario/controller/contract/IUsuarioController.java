package br.com.easy.aalife.modules.usuario.controller.contract;

import br.com.easy.aalife.modules.usuario.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

public interface IUsuarioController {

    @PostMapping("/cadastrar")
    @Operation(summary = "Endpoint responsavel pelo cadastro de usuario comum.")
    void salvar(@RequestBody @Valid UsuarioRequest request);

    @PutMapping("/{id}/editar")
    @Operation(summary = "Endpoint responsavel pela edicao de profissional")
    void editar(@PathVariable Integer id, @RequestBody UsuarioAtualizacaoRequest request);

    @PutMapping("/{id}/alterar-situacao")
    @Operation(summary = "Endpoint responsavel por alterar a situacao de um profissional")
    void alterarSituacao(@PathVariable Integer id);

    @GetMapping("/{id}")
    @Operation(summary = "Endpoint responsavel por buscar usuario por id")
    UsuarioResponse buscarPorId(@PathVariable Integer id);

    @GetMapping("/{id}/profissional")
    @Operation(summary = "Endpoint responsavel por buscar usuario por id")
    Page<ProfissionalResponse> buscarProfissional(@PathVariable Integer id, Pageable pageable);

    @GetMapping("/{id}/administrador")
    @Operation(summary = "Endpoint responsavel por buscar usuario por id")
    Page<AdministradorResponse> buscarAdministrador(@PathVariable Integer id, Pageable pageable);

    @GetMapping
    @Operation(summary = "Endpoint responsavel por buscar usuarios")
    Page<UsuarioResponse> buscarUsuarios(Pageable pageable);
}
