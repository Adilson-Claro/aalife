package br.com.easy.aalife.modules.profissional.model;

import br.com.easy.aalife.modules.comum.enums.ERole;
import br.com.easy.aalife.modules.comum.enums.ESituacao;
import br.com.easy.aalife.modules.comum.usuario.model.UsuarioBase;
import br.com.easy.aalife.modules.profissional.dto.UsuarioProfissionalAtualizacaoRequest;
import br.com.easy.aalife.modules.profissional.dto.UsuarioProfissionalRequest;
import br.com.easy.aalife.modules.profissional.enums.ETipoConselho;
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
@Table(name = "usuario_profissional")
public class UsuarioProfissional extends UsuarioBase {

    @Column(name = "numero_conselho", unique = true, nullable = false)
    private String numeroConselho;

    @Column(name = "cpf", unique = true, nullable = false)
    private String cpf;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_conselho")
    private ETipoConselho tipoConselho;

    public static UsuarioProfissional of(UsuarioProfissionalRequest request, PasswordEncoder passwordEncoder) {
        return UsuarioProfissional.builder()
                .email(request.email())
                .numeroConselho(request.numeroConselho())
                .senha(passwordEncoder.encode(request.senha()))
                .nome(request.nome())
                .cpf(request.cpf())
                .tipoConselho(request.tipoConselho())
                .role(ERole.PROFISSIONAL)
                .situacao(ESituacao.A)
                .build();
    }

    public void editar(UsuarioProfissionalAtualizacaoRequest request, PasswordEncoder passwordEncoder) {
        this.setEmail(request.email());
        this.setNome(request.nome());
        this.setSenha(passwordEncoder.encode(request.senha()));
    }
}
