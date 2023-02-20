package com.br.clinica.auth.dto;

import com.br.clinica.auth.enumeration.RoleName;
import com.br.clinica.auth.model.UserModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

//.stream().map converte

@Getter
@Setter
public class UserResponseDTO {

    private String username;
    private List<RoleName> roleName;

    public UserResponseDTO(UserModel model) {
        this.username = model.getUsername();
        this.roleName = model.getRoles().stream().map(role -> role.getRoleName())
                .toList();


    }
}
