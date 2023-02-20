package com.br.clinica.endereco;

import com.br.clinica.client.ViaCepDTO;
import com.br.clinica.endereco.dto.DadosEnderecoDTO;
import com.br.clinica.endereco.dto.DadosAtualizacaoEndereco;
import com.br.clinica.paciente.Paciente;
import com.br.clinica.paciente.dto.PacienteResponseDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

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
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "paciente_id", unique = true)
    private Paciente paciente;

    public Endereco(DadosEnderecoDTO dadosEndereco, ViaCepDTO viaCepDTO) {
        this.paciente = new Paciente(dadosEndereco.pacienteId());
        this.cep = dadosEndereco.cep();
        this.cidade = viaCepDTO.localidade();
        this.estado = viaCepDTO.uf();
        this.bairro = viaCepDTO.bairro();
        this.rua = viaCepDTO.logradouro();
        this.numero = dadosEndereco.numero();
        this.complemento = viaCepDTO.complemento();
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
