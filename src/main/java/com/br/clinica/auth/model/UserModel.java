package com.br.clinica.auth.model;

import com.br.clinica.auth.dto.DadosAtualizacaoUserDTO;
import com.br.clinica.auth.dto.UserDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import static java.util.Objects.nonNull;

@Entity
@Table(name = "tb_user")
@Getter
@Setter
@NoArgsConstructor
public class UserModel implements UserDetails, Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String userId;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<RoleModel> roles;

    public UserModel(UserDTO userDTO) {
        this.username = userDTO.username();
        this.password = (new BCryptPasswordEncoder().encode(userDTO.password()));
        this.roles = List.of(new RoleModel(userDTO.roleId()));
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    // se a conta nao expirou
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //se a conta nao esta bloqueada
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //se a credencial nao expirou
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // se esta ativo
    @Override
    public boolean isEnabled() {
        return true;
    }

    public void update(DadosAtualizacaoUserDTO dadosAtualizacao) {
        if (nonNull(dadosAtualizacao.getUsername())) {
            this.username = dadosAtualizacao.getUsername();
        }
        if (nonNull(dadosAtualizacao.getPassword())) {
            this.password = new BCryptPasswordEncoder().encode(dadosAtualizacao.getPassword());
        }
    }
}
