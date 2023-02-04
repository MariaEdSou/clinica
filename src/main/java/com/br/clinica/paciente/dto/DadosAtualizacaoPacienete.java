package com.br.clinica.paciente.dto;

import jakarta.validation.constraints.NotEmpty;

public record DadosAtualizacaoPacienete(
        String nome,
        String email,
        String telefone
) {
}
