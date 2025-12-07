package br.com.easy.aalife.modules.administrador.dto;

import br.com.easy.aalife.modules.administrador.model.UsuarioAdministrador;

public record UsuarioAdministradorResponse(String nome,
                                           String email,
                                           String situacao) {

    public static UsuarioAdministradorResponse of(UsuarioAdministrador usuarioAdministrador) {
        return new UsuarioAdministradorResponse(
                usuarioAdministrador.getNome(),
                usuarioAdministrador.getEmail(),
                usuarioAdministrador.getSituacao().getDescricao()
        );
    }
}
