package com.br.clinica.pacienteDTO;

import com.br.clinica.paciente.Paciente;

public record PacienteResponseDTO(String cpf, String nome, String telefone, String email) {

        public PacienteResponseDTO(Paciente paciente){
                this(paciente.getCpf(),paciente.getNome(), paciente.getTelefone(),paciente.getEmail());
        }

}
