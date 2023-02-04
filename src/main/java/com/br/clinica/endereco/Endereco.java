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
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id")
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

    public static record EnderecoResponseDTO(
            @NotNull
            Long id,
            @NotNull
            Long cep,
            @NotBlank
            String cidade,
            @NotBlank
            String estado,
            @NotBlank
            String bairro,
            @NotBlank
            String rua,
            @NotNull
            Long numero,
            @NotBlank
            String complemento,
            PacienteResponseDTO paciente) {

        public EnderecoResponseDTO(Endereco endereco) {
            this(endereco.getId(), endereco.getCep(), endereco.getCidade(), endereco.getEstado(),
                    endereco.getBairro(), endereco.getRua(), endereco.getNumero(), endereco.getComplemento(),new PacienteResponseDTO(endereco.getPaciente()));
        }

    }

    public static interface EnderecoRepository extends JpaRepository<Endereco, Long> {

        void deleteById(Long id);

        @org.springframework.data.jpa.repository.EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = {"paciente"})
        @Override
        Page<Endereco> findAll(Pageable pageable);
    }
}
