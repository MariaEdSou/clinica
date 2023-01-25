package com.br.clinica.pacienteDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


public record DadosCadastroPacienteDTO(
        @NotBlank
        String cpf,
        @NotBlank
        String nome,
        @NotBlank
        String telefone,
        @NotBlank
        @Email
        String email) {
}
