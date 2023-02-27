package com.br.clinica.paciente.controller;


import com.br.clinica.paciente.dto.DadosAtualizacaoPaciente;
import com.br.clinica.paciente.dto.DadosCadastroPacienteDTO;
import com.br.clinica.paciente.dto.PacienteResponseDTO;
import com.br.clinica.paciente.service.PacienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "cadastro de pacientes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201"),
            @ApiResponse(responseCode = "400"),
            @ApiResponse(responseCode = "403"),
            @ApiResponse(responseCode = "401"),
            @ApiResponse(responseCode = "500"),
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void save(@RequestBody @Valid DadosCadastroPacienteDTO dados) {
        pacienteService.save(dados);
    }

    @Operation(summary = "listagem de pacientes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "403"),
            @ApiResponse(responseCode = "404"),
            @ApiResponse(responseCode = "401"),
            @ApiResponse(responseCode = "500"),
    })
    @PreAuthorize("hasAnyRole('ROLE_ADMIN' , 'ROLE_USER')")
    @GetMapping
    public ResponseEntity<List<PacienteResponseDTO>> list(@PageableDefault(sort = {"nome"}) Pageable paginacao) {
        Optional<List<PacienteResponseDTO>> pacienteResponseDTOS = pacienteService.list(paginacao);
        return ResponseEntity.of(pacienteResponseDTOS);
    }

    @Operation(summary = "atualizacao de consultas de pacientes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204"),
            @ApiResponse(responseCode = "403"),
            @ApiResponse(responseCode = "401"),
            @ApiResponse(responseCode = "500"),
    })
    @PutMapping("/{cpf}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable String cpf, @RequestBody DadosAtualizacaoPaciente dados) {
        pacienteService.update(cpf, dados);
    }

    @Operation(summary = "exclusao de consultas de pacientes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204"),
            @ApiResponse(responseCode = "403"),
            @ApiResponse(responseCode = "401"),
            @ApiResponse(responseCode = "500"),
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByCpf(@PathVariable String id) {
        pacienteService.deleteByCpf(id);
    }

}
