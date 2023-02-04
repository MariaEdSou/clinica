package com.br.clinica.endereco;

import com.br.clinica.client.ViaCepDTO;
import com.br.clinica.consultaDTO.DadosEnderecoDTO;
import com.br.clinica.enderecoDTO.DadosAtualizacaoEndereco;
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

    public Endereco(DadosEnderecoDTO dadosEndereco, ViaCepDTO viaCepDTO) {
        this.paciente = new Paciente(dadosEndereco.pacienteId());
        this.cep = dadosEndereco.cep();
        this.cidade = dadosEndereco.cidade();
        this.estado = viaCepDTO.estado();
        this.bairro = viaCepDTO.bairro();
        this.rua = dadosEndereco.rua();
        this.numero = dadosEndereco.numero();
        this.complemento = viaCepDTO.complemento();


    }

    public Endereco(DadosEnderecoDTO dadosEndereco) {

    }

    public void atualizarInf(DadosAtualizacaoEndereco dados) {
        if (dados.cep() != null) {
            this.cep = dados.cep();
        }
        if (dados.cidade() != null) {
            this.cidade = dados.cidade();
        }
        if (dados.estado() != null) {
            this.estado = dados.estado();
        }
        if (dados.bairro() != null) {
            this.bairro = dados.bairro();
        }
        if (dados.rua() != null) {
            this.rua = dados.rua();
        }
        if (dados.numero() != null) {
            this.numero = dados.numero();
        }
        if (dados.complemento() != null) {
            this.complemento = dados.complemento();
        }
    }
}
