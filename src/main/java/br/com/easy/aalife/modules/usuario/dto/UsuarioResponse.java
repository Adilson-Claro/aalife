package br.com.easy.aalife.modules.usuario.dto;

import br.com.easy.aalife.modules.usuario.model.Usuario;

public record UsuarioResponse(String nome,
                              String email,
                              String situacao) {

    public static UsuarioResponse of(Usuario usuario) {
        return new UsuarioResponse(
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getSituacao().getDescricao());
    }
}
