package com.br.clinica.paciente;

import com.br.clinica.consulta.Consulta;
import com.br.clinica.endereco.Endereco;
import com.br.clinica.paciente.dto.DadosAtualizacaoPaciente;
import com.br.clinica.paciente.dto.DadosCadastroPacienteDTO;
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
    @Column(unique=true)
    private String cpf;
    private String nome;
    @Column(unique=true)
    private String email;
    @Column(unique=true)
    private String telefone;
    @OneToMany(mappedBy = "paciente" , fetch = FetchType.EAGER)
    private List<Consulta> consulta;
    @OneToOne(mappedBy = "paciente", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Endereco endereco;



    public Paciente(DadosCadastroPacienteDTO dados) {
        this.nome = dados.nome();
        this.telefone = dados.telefone();
        this.email = dados.email();
        this.cpf = dados.cpf();

    }

    public Paciente(String cpf) {
        this.cpf = cpf;

    }

    public void update(DadosAtualizacaoPaciente dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.email() != null) {
            this.email = dados.email();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
    }
}
