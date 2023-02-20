package com.br.clinica.endereco.controller;

import com.br.clinica.client.ViaCepClient;
import com.br.clinica.endereco.Endereco;
import com.br.clinica.endereco.dto.DadosEnderecoDTO;
import com.br.clinica.endereco.dto.DadosAtualizacaoEndereco;
import com.br.clinica.endereco.dto.EnderecoResponseDTO;
import com.br.clinica.endereco.repository.EnderecoRepository;
import com.br.clinica.endereco.service.EnderecoService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoRepository repository;
    private final Logger log = LoggerFactory.getLogger(EnderecoController.class);

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private ViaCepClient viaCepClient;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void cadastrar(@RequestBody DadosEnderecoDTO dadosEndereco) {
        enderecoService.cadastrar(dadosEndereco);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN' , 'ROLE_USER')")
    public ResponseEntity<List<EnderecoResponseDTO>> listar(@PageableDefault(sort = {"cidade"}) Pageable paginacao) {
        Optional<List<EnderecoResponseDTO>> enderecoResponseDTOS = enderecoService.listar(paginacao);
        return ResponseEntity.of(enderecoResponseDTOS);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void atualizar(@RequestBody DadosAtualizacaoEndereco dados) {
        enderecoService.atualizar(dados);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteById(@PathVariable Long id) {
        enderecoService.deleteById(id);
    }

}
