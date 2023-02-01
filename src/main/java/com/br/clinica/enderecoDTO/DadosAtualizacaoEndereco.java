package com.br.clinica.enderecoDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoEndereco(

        @NotNull
        Long id,

        Long cep,

        String cidade,

        String estado,

        String bairro,

        String rua,

        Long numero,

        String complemento ) {
}
