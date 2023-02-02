package com.br.clinica.pacienteDTO;

import jakarta.validation.constraints.NotEmpty;

public record DadosAtualizacaoPacienete(
        String nome,
        String email,
        String telefone
) {
}
