package br.com.easy.aalife.modules.profissional.dto;

import br.com.easy.aalife.modules.profissional.model.UsuarioProfissional;

public record UsuarioProfissionalResponse(String numeroConselho,
                                          String nome,
                                          String email,
                                          String situacao) {

    public static UsuarioProfissionalResponse of(UsuarioProfissional usuarioProfissional) {
        return new UsuarioProfissionalResponse(
                usuarioProfissional.getNumeroConselho(),
                usuarioProfissional.getNome(),
                usuarioProfissional.getEmail(),
                usuarioProfissional.getSituacao().getDescricao()
        );
    }
}
