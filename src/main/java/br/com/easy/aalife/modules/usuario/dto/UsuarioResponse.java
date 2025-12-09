package br.com.easy.aalife.modules.usuario.dto;

import br.com.easy.aalife.modules.usuario.model.Usuario;

public record UsuarioResponse(Integer id,
                              String nome,
                              String email,
                              String situacao) {

    public static UsuarioResponse of(Usuario usuario) {
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getSituacao().getDescricao());
    }
}
