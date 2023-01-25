package com.br.clinica.controller;

import com.br.clinica.consulta.Consulta;
import com.br.clinica.paciente.DadosAtualizacaoPacienteDTO;
import com.br.clinica.paciente.DadosCadastroPacienteDTO;
import com.br.clinica.paciente.DadosListagemPacienteDTO;
import com.br.clinica.paciente.Paciente;
import com.br.clinica.repository.PacienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    //    @ResponseStatus(HttpStatus.CREATED)faz retornar status 201 - algo foi criado
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroPacienteDTO dados) {
        repository.save(new Paciente(dados));
    }


    @GetMapping
    public ResponseEntity<List<DadosAtualizacaoPacienteDTO>> listar(@PageableDefault( sort = {"nome"}) Pageable paginacao) {
        List<DadosAtualizacaoPacienteDTO> dadosAtualizacaoPacienteDTOS = repository.findAll(paginacao)
                .map(DadosAtualizacaoPacienteDTO::new)
                .stream()
                .toList();

        return ResponseEntity.of(Optional.of(dadosAtualizacaoPacienteDTOS)
                .filter(not(List::isEmpty)));
    }

}
