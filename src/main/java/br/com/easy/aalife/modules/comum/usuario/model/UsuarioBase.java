package br.com.easy.aalife.modules.comum.usuario.model;

import br.com.easy.aalife.modules.comum.enums.ERole;
import br.com.easy.aalife.modules.comum.enums.ESituacao;
import br.com.easy.aalife.modules.comum.usuario.dto.UsuarioBaseAtualizacaoRequest;
import br.com.easy.aalife.modules.comum.usuario.dto.UsuarioBaseRequest;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuario_base")
@Inheritance(strategy = InheritanceType.JOINED)
public class UsuarioBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome_exibicao", nullable = false)
    private String nome;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "senha", nullable = false)
    private String senha;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private ERole role;

    @Column(name = "situacao", nullable = false)
    @Enumerated(EnumType.STRING)
    private ESituacao situacao;

    public static UsuarioBase of(UsuarioBaseRequest request) {
        return UsuarioBase.builder()
                .email(request.email())
                .nome(request.nome())
                .role(ERole.BASICO)
                .situacao(ESituacao.A)
                .build();
    }

    public void editar(UsuarioBaseAtualizacaoRequest request, PasswordEncoder passwordEncoder) {
        this.email = request.email();
        this.senha = request.senha();
        this.nome = passwordEncoder.encode(request.nome());
    }

    public void alterarSituacao() {
        this.setSituacao(this.getSituacao() == ESituacao.A ? ESituacao.I : ESituacao.A);
    }

    public boolean isLoginCorreto(String senha, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(senha, this.senha);
    }
}
