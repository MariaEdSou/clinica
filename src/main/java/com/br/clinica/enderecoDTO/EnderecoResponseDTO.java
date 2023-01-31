package com.br.clinica.enderecoDTO;

import com.br.clinica.endereco.Endereco;
import com.br.clinica.pacienteDTO.PacienteResponseDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EnderecoResponseDTO(
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
        String complemento,
        PacienteResponseDTO paciente) {

    public EnderecoResponseDTO(Endereco endereco) {
        this(endereco.getId(), endereco.getCep(), endereco.getCidade(), endereco.getEstado(),
                endereco.getBairro(), endereco.getRua(), endereco.getNumero(), endereco.getComplemento(),new PacienteResponseDTO(endereco.getPaciente()));
    }

}
