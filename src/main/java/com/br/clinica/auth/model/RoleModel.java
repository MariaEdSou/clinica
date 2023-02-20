package com.br.clinica.auth.model;

import com.br.clinica.auth.dto.RoleDTO;
import com.br.clinica.auth.enumeration.RoleName;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

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


    public RoleModel(RoleDTO roleNameDTO) {
        this.roleName = roleNameDTO.roleName();
    }

    public RoleModel(String roleId) {
        this.roleId = roleId;
    }

    public RoleModel() {
    }

    @Override
    public String getAuthority() {
        return this.roleName.toString();
    }
}
