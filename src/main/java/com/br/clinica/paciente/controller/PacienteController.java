package com.br.clinica.paciente.controller;


import com.br.clinica.paciente.dto.DadosAtualizacaoPacienete;
import com.br.clinica.paciente.dto.DadosCadastroPacienteDTO;
import com.br.clinica.paciente.dto.PacienteResponseDTO;
import com.br.clinica.paciente.repository.PacienteRepository;
import com.br.clinica.endereco.service.EnderecoService;
import com.br.clinica.paciente.service.PacienteService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private PacienteService pacienteService;
    private final static Logger log = LoggerFactory.getLogger(PacienteController.class);

    //    @ResponseStatus(HttpStatus.CREATED)faz retornar status 201 - algo foi criado
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroPacienteDTO dados) {
        enderecoService.cadastrar(dados.endereco());
    }


    @GetMapping
    public ResponseEntity<List<PacienteResponseDTO>> listar(@PageableDefault(sort = {"nome"}) Pageable paginacao) {
        Optional<List<PacienteResponseDTO>> pacienteResponseDTOS = pacienteService.listar(paginacao);
        return ResponseEntity.of(pacienteResponseDTOS);
    }

    @PutMapping("/{cpf}")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable String cpf, @RequestBody DadosAtualizacaoPacienete dados) {
       pacienteService.atualizar(cpf,dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirPorCpf(@PathVariable String id) {
       pacienteService.excluirPorCpf(id);
    }

}
