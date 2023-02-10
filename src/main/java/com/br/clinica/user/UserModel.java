package com.br.clinica.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

//  @Column(nullable = false, unique = true) para que a coluna nao seja null e que os valores nao se repitam.
// @ManyToMany / @JoinTable(name = "tb_user_roles", uma terceira tabela criada pelo spring.
// joinColumns = @JoinColumn(name = "user_id"),oq vai ter na terceira tabela criada
//    inverseJoinColumns = @JoinColumn (name = "role_id"))

@Entity
@Table(name = "tb_user")
@Getter
@Setter
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
    @ManyToMany
    @JoinTable(name = "tb_user_roles" ,
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn (name = "role_id"))
    private List<RoleModel> roles;




    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    // verifica se a conta nao esta expirada
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // verifica se a conta naoesta bloqueada
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    // se e um usuario ativo

    @Override
    public boolean isEnabled() {
        return true;
    }
}
