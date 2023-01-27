package com.br.clinica.consulta;

import com.br.clinica.consultaDTO.DadosCadastroConsultaDTO;
import com.br.clinica.consultaDTO.Especialidade;
import com.br.clinica.paciente.Paciente;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Table(name = "consultas")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate data;
    private String horario;
    private Double valor;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;


    public Consulta(DadosCadastroConsultaDTO dadosConsulta) {
        this.paciente = new Paciente(dadosConsulta.pacienteId());
        this.data = dadosConsulta.data();
        this.horario = dadosConsulta.horario();
        this.valor = dadosConsulta.valor();
    }
}
