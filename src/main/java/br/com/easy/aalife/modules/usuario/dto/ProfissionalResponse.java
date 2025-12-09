package br.com.easy.aalife.modules.usuario.dto;

import br.com.easy.aalife.modules.usuario.model.Usuario;

public record ProfissionalResponse(Integer numeroConselho,
                                   String nome,
                                   String email,
                                   String situacao) {

    public static ProfissionalResponse of(Usuario usuario) {
        return new ProfissionalResponse(
                usuario.getNumeroConselho(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getSituacao().getDescricao()
        );
    }
}
