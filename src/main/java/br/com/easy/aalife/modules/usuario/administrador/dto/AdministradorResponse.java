package br.com.easy.aalife.modules.usuario.administrador.dto;

import br.com.easy.aalife.modules.usuario.administrador.model.Administrador;

public record AdministradorResponse(Integer id,
                                    String nome,
                                    String email,
                                    String situacao) {

    public static AdministradorResponse of(Administrador administrador) {
        var usuario = administrador.getUsuario();

        return new AdministradorResponse(
                administrador.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getSituacao().getDescricao()
        );
    }
}
