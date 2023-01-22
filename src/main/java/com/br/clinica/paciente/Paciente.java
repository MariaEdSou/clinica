package com.br.clinica.paciente;

import com.br.clinica.consulta.Consulta;
import com.br.clinica.endereco.Endereco;
import jakarta.persistence.*;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Table(name = "pacientes")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Paciente {

    @Id
    private String cpf;
    private String nome;
    private String email;
    private String telefone;
    @OneToMany(mappedBy = "paciente")
    private List<Consulta> consulta;
    @OneToOne(mappedBy = "paciente")
    private Endereco endereco;
}
