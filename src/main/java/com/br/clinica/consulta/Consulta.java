package com.br.clinica.consulta;

import com.br.clinica.consultaDTO.DadosCadastroParaConsultaDTO;
import com.br.clinica.consultaDTO.Especialidade;
import com.br.clinica.paciente.Paciente;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
    private LocalDateTime horario;
    private Double valor;
    @Embedded
    private Especialidade especialidade;
    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;


    public Consulta(DadosCadastroParaConsultaDTO dadosConsulta) {
        this.data = dadosConsulta.data();
        this.horario = dadosConsulta.horario();
        this.valor = dadosConsulta.valor();
    }
}
