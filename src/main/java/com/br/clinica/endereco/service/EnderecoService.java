package com.br.clinica.endereco.service;

import com.br.clinica.client.ViaCepClient;
import com.br.clinica.client.ViaCepDTO;
import com.br.clinica.endereco.Endereco;
import com.br.clinica.endereco.controller.EnderecoController;
import com.br.clinica.endereco.dto.DadosAtualizacaoEndereco;
import com.br.clinica.endereco.dto.DadosEnderecoDTO;
import com.br.clinica.endereco.dto.EnderecoResponseDTO;
import com.br.clinica.endereco.repository.EnderecoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static java.util.function.Predicate.not;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository repository;
    private final Logger log = LoggerFactory.getLogger(EnderecoController.class);

    @Autowired
    private ViaCepClient viaCepClient;

    @Transactional
    public void save(DadosEnderecoDTO dadosEndereco) {
        ViaCepDTO viaCepDTO = viaCepClient.getEndereco(String.valueOf(dadosEndereco.cep())).getBody();
        repository.save(new Endereco(dadosEndereco, viaCepDTO));
        log.info("registered address");
    }

    public Optional<List<EnderecoResponseDTO>> list(Pageable paginacao) {
        List<EnderecoResponseDTO> enderecoResponseDTOS = repository.findAll(paginacao)
                .map(EnderecoResponseDTO::new)
                .stream()
                .toList();

        return Optional.of(enderecoResponseDTOS)
                .filter(not(List::isEmpty));
    }

    @Transactional
    public void update(DadosAtualizacaoEndereco dados) {
        var endereco = repository.findById(dados.id()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ENDERECO NAO ENCONTRADO"));
        endereco.atualizarInf(dados);
        log.info("update data");
    }

    @Transactional
    public void deleteById(Long id) {
        repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ENDERECO ID NAO ENCONTRADO"));
        log.info("delete address");
    }


}
