package com.br.clinica.controller;

import com.br.clinica.consulta.Consulta;
import com.br.clinica.consultaDTO.DadosCadastroConsultaDTO;
import com.br.clinica.repository.ConsultaRepository;
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

import static java.util.function.Predicate.not;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {

    @Autowired
    private ConsultaRepository repository;
    private final Logger log = LoggerFactory.getLogger(ConsultaController.class);

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrarPacienteEmConsulta(@RequestBody DadosCadastroConsultaDTO dadosConsulta) {
        repository.save(new Consulta(dadosConsulta));
        log.info("consulta cadastrada");
    }

    @GetMapping
    public ResponseEntity<List<ConsultaResponseDTO>> listar(@PageableDefault(sort = {"data"})Pageable paginacao) {
        List<ConsultaResponseDTO> consultaResponseDTOS = repository.findAll(paginacao)
                .map(ConsultaResponseDTO::new)
                .stream()
                .toList();

        return ResponseEntity.of(Optional.of(consultaResponseDTOS)
                .filter(not(List::isEmpty)));

    }
}