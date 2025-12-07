package br.com.easy.aalife.modules.administrador.model;

import br.com.easy.aalife.modules.administrador.dto.UsuarioAdministradorAtualizacaoRequest;
import br.com.easy.aalife.modules.administrador.dto.UsuarioAdministradorRequest;
import br.com.easy.aalife.modules.comum.enums.ERole;
import br.com.easy.aalife.modules.comum.enums.ESituacao;
import br.com.easy.aalife.modules.usuario.model.UsuarioBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;

//TODO VALIDAR ADM

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuario_administrador")
public class UsuarioAdministrador extends UsuarioBase {

    @Column(name = "cpf", unique = true, nullable = false)
    private String cpf;

    public static UsuarioAdministrador of(UsuarioAdministradorRequest request, PasswordEncoder passwordEncoder) {
        return UsuarioAdministrador.builder()
                .email(request.email())
                .senha(passwordEncoder.encode(request.senha()))
                .nome(request.nome())
                .cpf(request.cpf())
                .role(ERole.ADMINISTRADOR)
                .situacao(ESituacao.A)
                .build();
    }

    public void editar(UsuarioAdministradorAtualizacaoRequest request, PasswordEncoder passwordEncoder) {
        this.setEmail(request.email());
        this.setNome(request.nome());
        this.setSenha(passwordEncoder.encode(request.senha()));
    }
}
