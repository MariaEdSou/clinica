package com.br.clinica.consulta.dto;

import com.br.clinica.consulta.Consulta;
import com.br.clinica.paciente.dto.PacienteResponseDTO;

import java.time.LocalDate;

public record ConsultaResponseDTO(
        Long id,
        LocalDate data,
        String horario,
        Double valor,
        EspecialidadeConsulta especialidade,
        PacienteResponseDTO paciente) {

    public ConsultaResponseDTO(Consulta consulta) {
        this(consulta.getId(), consulta.getData(), consulta.getHorario(),
                consulta.getValor(), consulta.getEspecialidade(), new PacienteResponseDTO(consulta.getPaciente()));
    }
}
