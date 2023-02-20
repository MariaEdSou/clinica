package com.br.clinica.auth.dto;

import com.br.clinica.auth.enumeration.RoleName;
import com.br.clinica.auth.model.RoleModel;

public record RoleDTO(RoleName roleName) {

    public RoleDTO(RoleModel roleModel) {
        this(roleModel.getRoleName());
    }

}
