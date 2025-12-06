package br.com.easy.aalife.modules.profissional.controller.contract;

import br.com.easy.aalife.modules.profissional.dto.UsuarioProfissionalAtualizacaoRequest;
import br.com.easy.aalife.modules.profissional.dto.UsuarioProfissionalRequest;
import br.com.easy.aalife.modules.profissional.dto.UsuarioProfissionalResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

public interface IUsuarioProfissionalController {

    @PostMapping("/cadastrar")
    @Operation(summary = "Endpoint responsavel pelo cadastro de profissional")
    void salvar(@RequestBody @Valid UsuarioProfissionalRequest request);

    @PutMapping("/{id}/editar")
    @Operation(summary = "Endpoint responsavel pela edicao de profissional")
    void editar(@PathVariable Integer id, @RequestBody UsuarioProfissionalAtualizacaoRequest request);

    @PutMapping("/{id}/alterar-situacao")
    @Operation(summary = "Endpoint responsavel por alterar a situacao de um profissional")
    void alterarSituacao(@PathVariable Integer id);

    @GetMapping("/{id}")
    @Operation(summary = "Endpoint responsavel por buscar profissionais por id")
    UsuarioProfissionalResponse buscarPorId(@PathVariable Integer id);

    @GetMapping
    @Operation(summary = "Endpoint responsavel por buscar profissionai")
    Page<UsuarioProfissionalResponse> buscarUsuarios(Pageable pageable);
}
