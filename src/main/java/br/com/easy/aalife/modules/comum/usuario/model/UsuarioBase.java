package br.com.easy.aalife.modules.comum.usuario.model;

import br.com.easy.aalife.modules.comum.enums.ERole;
import br.com.easy.aalife.modules.comum.enums.ESituacao;
import br.com.easy.aalife.modules.comum.usuario.dto.UsuarioBaseRequest;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuario_base")
@Inheritance(strategy = InheritanceType.JOINED)
public class UsuarioBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private ERole role;

    @Column(name = "situacao")
    @Enumerated(EnumType.STRING)
    private ESituacao situacao;

    public boolean isLoginCorreto(String password, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(password, this.password);
    }

    public static UsuarioBase of(UsuarioBaseRequest request) {
        return UsuarioBase.builder()
                .username(request.username())
                .role(ERole.BASICO)
                .situacao(ESituacao.A)
                .build();
    }
}
