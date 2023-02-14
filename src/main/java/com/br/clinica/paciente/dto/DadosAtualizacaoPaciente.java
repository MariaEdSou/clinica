package com.br.clinica.paciente.dto;

import com.br.clinica.endereco.dto.DadosAtualizacaoEndereco;
import com.br.clinica.endereco.dto.DadosEnderecoDTO;
import com.br.clinica.endereco.dto.EnderecoResponseDTO;

public record DadosAtualizacaoPaciente(

        DadosAtualizacaoEndereco dadosAtualizacaoEndereco,
        String nome,
        String email,
        String telefone
) {
}
