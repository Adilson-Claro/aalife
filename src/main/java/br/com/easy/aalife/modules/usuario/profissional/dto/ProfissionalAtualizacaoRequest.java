package br.com.easy.aalife.modules.usuario.profissional.dto;

public record ProfissionalAtualizacaoRequest(String nome,
                                             String email,
                                             String senha,
                                             String telefone) {
}
