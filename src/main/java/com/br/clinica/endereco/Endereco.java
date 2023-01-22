package com.br.clinica.endereco;

import com.br.clinica.paciente.Paciente;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "endereco")
@Getter
@Entity
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long cep;
    private String cidade;
    private String estado;
    private String bairro;
    private String rua;
    private Long numero;
    private String complemento;
    @OneToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

}
