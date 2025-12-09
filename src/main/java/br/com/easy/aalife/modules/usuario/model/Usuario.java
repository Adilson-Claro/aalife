package br.com.easy.aalife.modules.usuario.model;

import br.com.easy.aalife.modules.comum.enums.ERole;
import br.com.easy.aalife.modules.comum.enums.ESituacao;
import br.com.easy.aalife.modules.comum.enums.ETipoUsuario;
import br.com.easy.aalife.modules.comum.enums.ETipoConselho;
import br.com.easy.aalife.modules.usuario.dto.UsuarioAtualizacaoRequest;
import br.com.easy.aalife.modules.usuario.dto.UsuarioRequest;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer idade;

    private BigDecimal peso;

    private BigDecimal altura;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "senha", nullable = false)
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private ERole role;

    @Enumerated(EnumType.STRING)
    @Column(name = "situacao", nullable = false)
    private ESituacao situacao;

    @Column(name = "numero_conselho", unique = true)
    private Integer numeroConselho;

    @Column(name = "cpf", unique = true)
    private String cpf;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_conselho")
    private ETipoConselho tipoConselho;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_usuario", nullable = false)
    private ETipoUsuario tipoUsuario;

    public static Usuario of(UsuarioRequest request, PasswordEncoder passwordEncoder) {
        return Usuario.builder()
                .email(request.email())
                .nome(request.nome())
                .senha(passwordEncoder.encode(request.senha()))
                .tipoUsuario(request.tipoUsuario())
                .situacao(ESituacao.A)
                .altura(request.altura())
                .peso(request.peso())
                .idade(request.idade())
                .cpf(request.cpf())
                .numeroConselho(request.numeroConselho())
                .tipoConselho(request.tipoConselho())
                .build();
    }

    public void editar(UsuarioAtualizacaoRequest request, PasswordEncoder passwordEncoder) {
        this.email = request.email();
        this.nome = request.nome();
        this.senha = passwordEncoder.encode(request.senha());
    }

    public void alterarSituacao() {
        this.setSituacao(this.getSituacao() == ESituacao.A ? ESituacao.I : ESituacao.A);
    }

    public boolean isLoginCorreto(String senha, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(senha, this.senha);
    }
}
