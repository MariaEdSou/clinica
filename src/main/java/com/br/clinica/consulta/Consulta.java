package com.br.clinica.consulta;

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
    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;
}
