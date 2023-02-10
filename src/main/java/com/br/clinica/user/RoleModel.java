package com.br.clinica.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.UUID;

//GrantedAuthority tipo de controle de acesso que o spring security trabalha
@Entity
@Table(name = "tb_role")
@Getter
@Setter
public class RoleModel implements GrantedAuthority, Serializable {
    private static final long serialVersionUID = 1l;
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String roleId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private RoleName roleName;

    //roleName.toString();p transformar o enum em string, de acordo com o retorno do metodo
    @Override
    public String getAuthority() {
        return this.roleName.toString();
    }
}
