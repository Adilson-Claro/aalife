package br.com.easy.aalife.modules.usuario.administrador.model;

import br.com.easy.aalife.modules.usuario.administrador.dto.AdministradorAtualizacaoRequest;
import br.com.easy.aalife.modules.usuario.administrador.dto.AdministradorRequest;
import br.com.easy.aalife.modules.usuario.comum.model.Usuario;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "administrador")
public class Administrador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "telefone", nullable = false, unique = true)
    private String telefone;

    @OneToOne(optional = false)
    @JoinColumn(name = "usuario_id", unique = true)
    private Usuario usuario;

    public static Administrador of(AdministradorRequest request, Usuario usuario) {
        return Administrador.builder()
                .usuario(usuario)
                .nome(request.nome())
                .cpf(request.cpf())
                .telefone(request.telefone())
                .build();
    }

    public void editar(AdministradorAtualizacaoRequest request) {
        this.telefone = request.telefone();
        this.nome = request.nome();
    }
}
