package com.br.clinica.consulta;

import com.br.clinica.consultaDTO.Especialidade;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DadosAtualizacaoConsulta(

        @NotNull
        Long id,
        LocalDate data,
        String horario,
        String valor,
        Especialidade especialidade
) {
}
