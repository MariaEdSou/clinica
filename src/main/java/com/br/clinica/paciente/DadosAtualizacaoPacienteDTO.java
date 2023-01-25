package com.br.clinica.paciente;

public record DadosAtualizacaoPacienteDTO(String cpf, String nome, String telefone,String email) {

        public DadosAtualizacaoPacienteDTO(Paciente paciente){
                this(paciente.getNome(), paciente.getEmail(), paciente.getCpf(),paciente.getTelefone());
        }

}
