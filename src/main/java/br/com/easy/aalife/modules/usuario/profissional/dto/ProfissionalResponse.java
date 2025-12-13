package br.com.easy.aalife.modules.usuario.profissional.dto;

import br.com.easy.aalife.modules.usuario.profissional.model.Profissional;

public record ProfissionalResponse(String numeroConselho,
                                   String nome,
                                   String telefone) {

    public static ProfissionalResponse of(Profissional profissional) {
        return new ProfissionalResponse(
                profissional.getNumeroConselho(),
                profissional.getNome(),
                profissional.getTelefone()
        );
    }
}
