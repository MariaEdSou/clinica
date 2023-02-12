package com.br.clinica.endereco.dto;

import com.br.clinica.endereco.Endereco;
import com.br.clinica.paciente.dto.PacienteResponseDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EnderecoPacienteResponseDTO(
        @NotNull
        Long id,
        @NotNull
        Long cep,
        @NotBlank
        String cidade,
        @NotBlank
        String estado,
        @NotBlank
        String bairro,
        @NotBlank
        String rua,
        @NotNull
        Long numero,
        @NotBlank
        String complemento) {

    public EnderecoPacienteResponseDTO(Endereco endereco) {
        this(endereco.getId(), endereco.getCep(), endereco.getCidade(), endereco.getEstado(),
                endereco.getBairro(), endereco.getRua(), endereco.getNumero(), endereco.getComplemento());
    }

}