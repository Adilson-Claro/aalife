package br.com.easy.aalife.modules.usuario.profissional.model;

import br.com.easy.aalife.modules.comum.enums.ETipoConselho;
import br.com.easy.aalife.modules.usuario.comum.model.Usuario;
import br.com.easy.aalife.modules.usuario.profissional.dto.ProfissionalAtualizacaoRequest;
import br.com.easy.aalife.modules.usuario.profissional.dto.ProfissionalRequest;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "profissional")
public class Profissional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "numero_conselho", nullable = false, unique = true)
    private String numeroConselho;

    @Column(name = "cpf", unique = true)
    private String cpf;

    @Column(name = "cnpj", unique = true)
    private String cnpj;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_conselho", nullable = false)
    private ETipoConselho tipoConselho;

    @Column(name = "telefone", nullable = false, unique = true)
    private String telefone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public static Profissional of(ProfissionalRequest request, Usuario usuario) {
        return Profissional.builder()
                .nome(request.nome())
                .numeroConselho(request.numeroConselho())
                .cpf(request.cpf())
                .cnpj(request.cnpj())
                .tipoConselho(request.tipoConselho())
                .telefone(request.telefone())
                .usuario(usuario)
                .build();
    }

    public void editar(ProfissionalAtualizacaoRequest request) {
        this.nome = request.nome();
        this.telefone = request.telefone();
    }
}