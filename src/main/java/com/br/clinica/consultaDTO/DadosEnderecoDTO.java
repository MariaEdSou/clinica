package com.br.clinica.consultaDTO;

import com.br.clinica.endereco.Endereco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record DadosEnderecoDTO(

        @NotEmpty
        String pacienteId,
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
        String complemento) {


}
