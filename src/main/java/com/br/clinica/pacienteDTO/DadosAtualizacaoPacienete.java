package com.br.clinica.pacienteDTO;

import jakarta.validation.constraints.NotEmpty;

public record DadosAtualizacaoPacienete(
        @NotEmpty
        String cpf,
        String nome,
        String email,
        String telefone
) {
}
