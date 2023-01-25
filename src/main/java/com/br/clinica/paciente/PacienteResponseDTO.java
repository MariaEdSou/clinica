package com.br.clinica.paciente;

public record PacienteResponseDTO(String cpf, String nome, String telefone, String email) {

        public PacienteResponseDTO(Paciente paciente){
                this(paciente.getCpf(),paciente.getNome(), paciente.getEmail(),paciente.getTelefone());
        }

}
