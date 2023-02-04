package com.br.clinica.client;

public record ViaCepDTO(
        String cep,
        String logradouro,
        String complemento,
        String bairro,
        String localidade,
        String uf
) {
}
