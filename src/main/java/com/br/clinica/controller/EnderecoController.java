package com.br.clinica.controller;

import com.br.clinica.consultaDTO.DadosEnderecoDTO;
import com.br.clinica.endereco.Endereco;
import com.br.clinica.repository.EnderecoRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
}
