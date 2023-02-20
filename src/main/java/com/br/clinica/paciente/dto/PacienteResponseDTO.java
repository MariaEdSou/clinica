package com.br.clinica.paciente.dto;

import com.br.clinica.endereco.dto.EnderecoPacienteResponseDTO;
import com.br.clinica.endereco.dto.EnderecoResponseDTO;
import com.br.clinica.paciente.Paciente;

public record PacienteResponseDTO(
        String cpf,
        String nome,
        String telefone,
        String email,
        EnderecoPacienteResponseDTO enderecoResponseDTO) {

    public PacienteResponseDTO(Paciente paciente) {
        this(paciente.getCpf(), paciente.getNome(), paciente.getTelefone(), paciente.getEmail(), new EnderecoPacienteResponseDTO(paciente.getEndereco()));
    }

}
