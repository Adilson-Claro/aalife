package br.com.easy.aalife.modules.usuario.dto;

public record UsuarioAtualizacaoRequest(String nome,
                                        String email,
                                        String senha) {
}
