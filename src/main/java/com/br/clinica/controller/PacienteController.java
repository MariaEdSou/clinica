package com.br.clinica.controller;

import com.br.clinica.pacienteDTO.DadosAtualizacaoPacienete;
import com.br.clinica.pacienteDTO.PacienteResponseDTO;
import com.br.clinica.pacienteDTO.DadosCadastroPacienteDTO;
import com.br.clinica.paciente.Paciente;
import com.br.clinica.repository.PacienteRepository;
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

import static java.util.function.Predicate.not;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;
    private final static Logger log = LoggerFactory.getLogger(PacienteController.class);

    //    @ResponseStatus(HttpStatus.CREATED)faz retornar status 201 - algo foi criado
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroPacienteDTO dados) {
        repository.save(new Paciente(dados));
    }


    @GetMapping
    public ResponseEntity<List<PacienteResponseDTO>> listar(@PageableDefault(sort = {"nome"}) Pageable paginacao) {
        List<PacienteResponseDTO> pacienteResponseDTOS = repository.findAll(paginacao)
                .map(PacienteResponseDTO::new)
                .stream()
                .toList();

        return ResponseEntity.of(Optional.of(pacienteResponseDTOS)
                .filter(not(List::isEmpty)));
    }

    @PutMapping("/{cpf}")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable String cpf, @RequestBody DadosAtualizacaoPacienete dados) {
        var paciente = repository.getReferenceById(cpf);
        paciente.atualizar(dados);
        log.info("dado atualizado");

    }

    @DeleteMapping("/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirPorCpf(@PathVariable String id) {
        repository.deleteByCpf(id);
        log.info("paciente deletado");
    }

}
