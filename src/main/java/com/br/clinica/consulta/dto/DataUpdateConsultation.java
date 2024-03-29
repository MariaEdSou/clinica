package com.br.clinica.consulta.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DataUpdateConsultation(
        @NotNull
        Long id,
        LocalDate data,
        String horario,
        String valor,
        EspecialidadeConsulta especialidade
) {
}
