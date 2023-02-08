package com.br.clinica.endereco.controller;

import com.br.clinica.client.ViaCepClient;
import com.br.clinica.endereco.Endereco;
import com.br.clinica.endereco.dto.DadosEnderecoDTO;
import com.br.clinica.endereco.dto.DadosAtualizacaoEndereco;
import com.br.clinica.endereco.repository.EnderecoRepository;
import com.br.clinica.endereco.service.EnderecoService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoRepository repository;
    private final Logger log = LoggerFactory.getLogger(EnderecoController.class);

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private ViaCepClient viaCepClient;

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrar(@RequestBody DadosEnderecoDTO dadosEndereco) {
        enderecoService.cadastrar(dadosEndereco);
    }

    @GetMapping
    public ResponseEntity<List<Endereco.EnderecoResponseDTO>> listar(@PageableDefault(sort = {"cidade"}) Pageable paginacao) {
        Optional<List<Endereco.EnderecoResponseDTO>> enderecoResponseDTOS = enderecoService.listar(paginacao);
        return ResponseEntity.of(enderecoResponseDTOS);
    }

    @PutMapping
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@RequestBody DadosAtualizacaoEndereco dados) {
        enderecoService.atualizar(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        enderecoService.deleteById(id);
    }

}
