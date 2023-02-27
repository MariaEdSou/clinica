package com.br.clinica.paciente.dto;

import com.br.clinica.endereco.dto.DadosEnderecoDTO;
import jakarta.validation.Valid;
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
        String email,
        @Valid
        DadosEnderecoDTO endereco
) {
}
