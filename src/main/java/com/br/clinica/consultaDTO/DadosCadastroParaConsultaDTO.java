package com.br.clinica.consultaDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record DadosCadastroParaConsultaDTO(
        @NotNull
        LocalDate data,
        @NotNull
        LocalDateTime horario,
        @NotNull
        Double valor,
        @NotNull
        @Valid
        Especialidade especialidade) {
}
