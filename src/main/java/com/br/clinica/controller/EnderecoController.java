package com.br.clinica.controller;

import com.br.clinica.consultaDTO.DadosEnderecoDTO;
import com.br.clinica.enderecoDTO.DadosAtualizacaoEndereco;
import com.br.clinica.endereco.Endereco;
import com.br.clinica.enderecoDTO.EnderecoResponseDTO;
import com.br.clinica.repository.EnderecoRepository;
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

import static java.util.function.Predicate.not;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoRepository repository;
    private final Logger log = LoggerFactory.getLogger(EnderecoController.class);

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrar(@RequestBody DadosEnderecoDTO dadosEndereco) {
        repository.save(new Endereco(dadosEndereco));
        log.info("endereco cadastrado");
    }

    @GetMapping
    public ResponseEntity<List<EnderecoResponseDTO>> listar(@PageableDefault(sort = {"cidade"}) Pageable paginacao) {
        List<EnderecoResponseDTO> enderecoResponseDTOS = repository.findAll(paginacao)
                .map(EnderecoResponseDTO::new)
                .stream()
                .toList();
        return ResponseEntity.of(Optional.of(enderecoResponseDTOS)
                .filter(not(List::isEmpty)));
    }

    @PutMapping
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@RequestBody DadosAtualizacaoEndereco dados) {
        var endereco = repository.getReferenceById(dados.id());
        endereco.atualizarInf(dados);
        log.info("Dado atualizado");
    }

    @DeleteMapping("/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        repository.deleteById(id);
        log.info("endereco deletado");
    }

}
