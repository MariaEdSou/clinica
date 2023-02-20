package com.br.clinica.consulta.controller;

import com.br.clinica.consulta.dto.DadosAtualizacaoConsulta;
import com.br.clinica.consulta.dto.ConsultaResponseDTO;
import com.br.clinica.consulta.dto.DadosCadastroConsultaDTO;
import com.br.clinica.consulta.repository.ConsultaRepository;
import com.br.clinica.consulta.service.ConsultaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {

    @Autowired
    private ConsultaService service;
    private final Logger log = LoggerFactory.getLogger(ConsultaController.class);

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void cadastrar(@RequestBody DadosCadastroConsultaDTO dadosConsulta) {
        service.cadastrar(dadosConsulta);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<List<ConsultaResponseDTO>> listar(@PageableDefault(sort = {"data"}) Pageable paginacao) {
        Optional<List<ConsultaResponseDTO>> consultaResponseDTOS = service.listar(paginacao);

        return ResponseEntity.of(consultaResponseDTOS);
    }

    @PutMapping
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void atualizar(@RequestBody DadosAtualizacaoConsulta dados) {
        service.atualizar(dados);
    }

    //PathVariable passar id na url
    @DeleteMapping("/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }
}
