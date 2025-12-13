package br.com.easy.aalife.modules.usuario.profissional.controller.contract;

import br.com.easy.aalife.modules.comum.enums.ETipoConselho;
import br.com.easy.aalife.modules.usuario.profissional.dto.ProfissionalAtualizacaoRequest;
import br.com.easy.aalife.modules.usuario.profissional.dto.ProfissionalRequest;
import br.com.easy.aalife.modules.usuario.profissional.dto.ProfissionalResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

public interface IProfissionalController {

    @PostMapping("/cadastrar")
    @Operation(summary = "Endpoint responsavel pelo cadastro de usuario comum.")
    void salvar(@RequestBody @Valid ProfissionalRequest request);

    @PutMapping("/{id}/editar")
    @Operation(summary = "Endpoint responsavel pela edicao de usuarios")
    void editar(@PathVariable Integer id, @RequestBody ProfissionalAtualizacaoRequest request);

    @GetMapping
    @Operation(summary = "Endpoint responsavel por buscar profissional")
    Page<ProfissionalResponse> findProfissionais(@RequestParam ETipoConselho tipoConselho, Pageable pageable);
}
