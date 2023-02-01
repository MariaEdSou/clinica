package com.br.clinica.consulta;

import com.br.clinica.consultaDTO.DadosAtualizacaoConsulta;
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
        this.especialidade = dadosConsulta.especialidade();
    }

    public void atualizar(DadosAtualizacaoConsulta dados) {
        if (dados.data() != null) {
            this.data = dados.data();
        }
        if (dados.horario() != null) {
            this.horario = dados.horario();
        }
        if (dados.horario() != null) {
            this.horario = dados.horario();
        }
        if (dados.especialidade() != null) {
            this.especialidade = dados.especialidade();
        }

    }

}
