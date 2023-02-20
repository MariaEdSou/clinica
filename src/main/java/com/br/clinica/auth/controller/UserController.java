package com.br.clinica.auth.controller;

import com.br.clinica.auth.dto.DadosAtualizacaoUserDTO;
import com.br.clinica.auth.dto.UserDTO;
import com.br.clinica.auth.dto.UserResponseDTO;
import com.br.clinica.auth.service.UserDetailsServiceImpl;
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
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserDetailsServiceImpl service;
    private final static Logger log = LoggerFactory.getLogger(UserController.class);

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public void save(@RequestBody UserDTO userDTO) {
        service.save(userDTO);
        log.info("registered user");
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_PACIENTE')")
    public ResponseEntity<List<UserResponseDTO>> listar(@PageableDefault(size = 5) Pageable paginacao) {
        Optional<List<UserResponseDTO>> userResponseDTOS = service.listar(paginacao);
        return ResponseEntity.of(userResponseDTOS);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void atualizar(@PathVariable String id, @RequestBody DadosAtualizacaoUserDTO dados) {
        service.atualizar(id, dados);
    }


}
