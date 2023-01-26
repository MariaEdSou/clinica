package com.br.clinica.controller;

import com.br.clinica.consulta.Consulta;
import com.br.clinica.consultaDTO.Especialidade;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ConsultaResponseDTO(Long id, LocalDate data, String horario, Double valor, Especialidade especialidade) {

    public ConsultaResponseDTO(Consulta consulta){
        this(consulta.getId(),consulta.getData(),consulta.getHorario(),
                consulta.getValor(),consulta.getEspecialidade());
    }
}
