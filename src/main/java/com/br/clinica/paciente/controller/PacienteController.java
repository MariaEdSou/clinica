package com.br.clinica.paciente.controller;


import com.br.clinica.paciente.dto.DadosAtualizacaoPaciente;
import com.br.clinica.paciente.dto.DadosCadastroPacienteDTO;
import com.br.clinica.paciente.dto.PacienteResponseDTO;
import com.br.clinica.paciente.service.PacienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    //    @ResponseStatus(HttpStatus.CREATED)faz retornar status 201 - algo foi criado
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void save(@RequestBody @Valid DadosCadastroPacienteDTO dados) {
        pacienteService.save(dados);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN' , 'ROLE_USER')")
    @GetMapping
    public ResponseEntity<List<PacienteResponseDTO>> list(@PageableDefault(sort = {"nome"}) Pageable paginacao) {
        Optional<List<PacienteResponseDTO>> pacienteResponseDTOS = pacienteService.list(paginacao);
        return ResponseEntity.of(pacienteResponseDTOS);
    }

    @PutMapping("/{cpf}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void update(@PathVariable String cpf, @RequestBody DadosAtualizacaoPaciente dados) {
        pacienteService.update(cpf, dados);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteByCpf(@PathVariable String id) {
        pacienteService.excluirPorCpf(id);
    }

}
