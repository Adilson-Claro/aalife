package br.com.easy.aalife.modules.usuario.comum.model;

import br.com.easy.aalife.modules.comum.enums.ERole;
import br.com.easy.aalife.modules.comum.enums.ESituacao;
import br.com.easy.aalife.modules.usuario.administrador.dto.AdministradorAtualizacaoRequest;
import br.com.easy.aalife.modules.usuario.administrador.dto.AdministradorRequest;
import br.com.easy.aalife.modules.usuario.administrador.model.Administrador;
import br.com.easy.aalife.modules.usuario.comum.dto.UsuarioRequest;
import br.com.easy.aalife.modules.usuario.profissional.dto.ProfissionalAtualizacaoRequest;
import br.com.easy.aalife.modules.usuario.profissional.dto.ProfissionalRequest;
import br.com.easy.aalife.modules.usuario.profissional.model.Profissional;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

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

    @OneToMany(mappedBy = "usuario")
    private List<Profissional> profissionais;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Administrador administrador;

    public static Usuario ofComum(UsuarioRequest request, PasswordEncoder passwordEncoder) {
        return Usuario.builder()
                .nome(request.nome())
                .email(request.email())
                .senha(passwordEncoder.encode(request.senha()))
                .role(ERole.COMUM)
                .situacao(ESituacao.A)
                .build();
    }

    public static Usuario ofProfissional(ProfissionalRequest request, PasswordEncoder passwordEncoder) {
        return Usuario.builder()
                .nome(request.nome())
                .email(request.email())
                .senha(passwordEncoder.encode(request.senha()))
                .role(ERole.PROFISSIONAL)
                .situacao(ESituacao.A)
                .build();
    }

    public static Usuario ofAdministrador(AdministradorRequest request, PasswordEncoder passwordEncoder) {
        return Usuario.builder()
                .nome(request.nome())
                .email(request.email())
                .senha(passwordEncoder.encode(request.senha()))
                .role(ERole.ADMINISTRADOR)
                .situacao(ESituacao.A)
                .build();
    }

    public void editar(UsuarioRequest request, PasswordEncoder passwordEncoder) {
        this.email = request.email();
        this.nome = request.nome();
        this.senha = passwordEncoder.encode(request.senha());
    }

    public void editarProfissional(ProfissionalAtualizacaoRequest request,
                                   PasswordEncoder passwordEncoder) {
        this.nome = request.nome();
        this.email = request.email();
        this.senha = passwordEncoder.encode(senha);
    }

    public void editarAdministrador(AdministradorAtualizacaoRequest request,
                                   PasswordEncoder passwordEncoder) {
        this.nome = request.nome();
        this.email = request.email();
        this.senha = passwordEncoder.encode(senha);
    }

    public void alterarSituacao() {
        this.situacao = this.situacao == ESituacao.A ? ESituacao.I : ESituacao.A;
    }

    public boolean isLoginCorreto(String senha, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(senha, this.senha);
    }
}
