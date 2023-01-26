package com.br.clinica.consultaDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DadosCadastroConsultaDTO(

        @NotEmpty
        String pacienteId,
        @NotNull
        LocalDate data,
        @NotNull
        String horario,
        @NotNull
        Double valor,
        @NotNull
        @Valid
        Especialidade especialidade) {
}
