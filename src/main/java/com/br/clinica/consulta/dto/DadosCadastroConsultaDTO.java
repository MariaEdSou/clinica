package com.br.clinica.consulta.dto;

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
        EspecialidadeConsulta especialidade) {
}
//   Consulta mockConsulta = Mockito.mock(Consulta.class);
//        Mockito.doReturn(Optional.of(mockConsulta)).when(repository).findById(Mockito.any());
//        Mockito.doNothing().when(mockConsulta).update(Mockito.any());
//
//        Assertions.assertDoesNotThrow(() -> consultaService.update(new DataUpdateConsultation(20L, LocalDate.now(), "20:30", "00", EspecialidadeConsulta.GINECOLOGIA)));
//    }