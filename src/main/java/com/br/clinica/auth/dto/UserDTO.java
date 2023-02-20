package com.br.clinica.auth.dto;

public record UserDTO(

        String username,
        String password,
        String roleId
) {
}
