package br.com.easy.aalife.modules.comum.usuario.dto;

import br.com.easy.aalife.modules.comum.usuario.model.UsuarioBase;

public record UsuarioBaseResponse(String nome,
                                  String email,
                                  String situacao) {

    public static UsuarioBaseResponse of(UsuarioBase usuarioBase) {
        return new UsuarioBaseResponse(
                usuarioBase.getNome(),
                usuarioBase.getEmail(),
                usuarioBase.getSituacao().getDescricao());
    }
}
