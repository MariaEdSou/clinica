package com.br.clinica.endereco.service;

import com.br.clinica.client.ViaCepClient;
import com.br.clinica.client.ViaCepDTO;
import com.br.clinica.endereco.Endereco;
import com.br.clinica.endereco.controller.EnderecoController;
import com.br.clinica.endereco.dto.DadosAtualizacaoEndereco;
import com.br.clinica.endereco.dto.DadosEnderecoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

import static java.util.function.Predicate.not;

@Service
public class EnderecoService {

    @Autowired
    private Endereco.EnderecoRepository repository;
    private final Logger log = LoggerFactory.getLogger(EnderecoController.class);

    @Autowired
    private ViaCepClient viaCepClient;

    public void cadastrar(String cpf, DadosEnderecoDTO dadosEndereco) {

    }

    public void cadastrar(DadosEnderecoDTO dadosEndereco) {
        ViaCepDTO viaCepDTO = viaCepClient.getEndereco(String.valueOf(dadosEndereco.cep())).getBody();
        repository.save(new Endereco(dadosEndereco, viaCepDTO));
        log.info("endereco cadastrado");
    }

    public Optional<List<Endereco.EnderecoResponseDTO>> listar(@PageableDefault(sort = {"cidade"}) Pageable paginacao) {
        List<Endereco.EnderecoResponseDTO> enderecoResponseDTOS = repository.findAll(paginacao)
                .map(Endereco.EnderecoResponseDTO::new)
                .stream()
                .toList();

        return Optional.of(enderecoResponseDTOS)
                .filter(not(List::isEmpty));
    }

    public void atualizar(@RequestBody DadosAtualizacaoEndereco dados) {
        var endereco = repository.getReferenceById(dados.id());
        endereco.atualizarInf(dados);
        log.info("Dado atualizado");
    }


    public void deleteById(@PathVariable Long id) {
        repository.deleteById(id);
        log.info("endereco deletado");
    }


}
