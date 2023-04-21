package com.br.clinica.auth.dto;

import com.br.clinica.auth.enumeration.RoleName;
import com.br.clinica.auth.model.RoleModel;
//oq e record pq record

public record RoleDTO(RoleName roleName) {
//oe ta fazendo
    public RoleDTO(RoleModel roleModel) {
        this(roleModel.getRoleName());
    }

}
