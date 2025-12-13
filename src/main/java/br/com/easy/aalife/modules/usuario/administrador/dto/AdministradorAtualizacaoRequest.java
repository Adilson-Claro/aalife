package br.com.easy.aalife.modules.usuario.administrador.dto;

public record AdministradorAtualizacaoRequest(String email,
                                              String senha,
                                              String telefone,
                                              String nome) {
}
