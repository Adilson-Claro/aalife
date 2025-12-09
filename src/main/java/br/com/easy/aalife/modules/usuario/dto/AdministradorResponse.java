package br.com.easy.aalife.modules.usuario.dto;

import br.com.easy.aalife.modules.usuario.model.Usuario;

public record AdministradorResponse(String nome,
                                    String email,
                                    String situacao) {

    public static AdministradorResponse of(Usuario usuario) {
        return new AdministradorResponse(
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getSituacao().getDescricao()
        );
    }
}
