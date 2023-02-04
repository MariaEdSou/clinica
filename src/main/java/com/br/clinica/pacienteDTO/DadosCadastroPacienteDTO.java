package com.br.clinica.pacienteDTO;

import com.br.clinica.client.ViaCepDTO;
import com.br.clinica.enderecoDTO.EnderecoResponseDTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


public record DadosCadastroPacienteDTO(
        @NotBlank
        String cpf,
        @NotBlank
        String nome,
        @NotBlank
        String telefone,
        @NotBlank
        @Email
        String email,

        ViaCepDTO viaCepDTO
//        EnderecoResponseDTO endereco
) {
}
