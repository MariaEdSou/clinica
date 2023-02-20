package com.br.clinica.consulta.controller;

import com.br.clinica.consulta.dto.DadosAtualizacaoConsulta;
import com.br.clinica.consulta.dto.ConsultaResponseDTO;
import com.br.clinica.consulta.dto.DadosCadastroConsultaDTO;
import com.br.clinica.consulta.service.ConsultaService;
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
@RequestMapping("/consulta")
public class ConsultaController {

    @Autowired
    private ConsultaService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody DadosCadastroConsultaDTO dadosConsulta) {
        service.save(dadosConsulta);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<List<ConsultaResponseDTO>> list(@PageableDefault(sort = {"data"}) Pageable paginacao) {
        Optional<List<ConsultaResponseDTO>> consultaResponseDTOS = service.list(paginacao);

        return ResponseEntity.of(consultaResponseDTOS);
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void update(@RequestBody DadosAtualizacaoConsulta dados) {
        service.update(dados);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }
}
