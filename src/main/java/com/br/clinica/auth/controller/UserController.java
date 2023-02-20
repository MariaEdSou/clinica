package com.br.clinica.auth.controller;

import com.br.clinica.auth.dto.DadosAtualizacaoUserDTO;
import com.br.clinica.auth.dto.UserDTO;
import com.br.clinica.auth.dto.UserResponseDTO;
import com.br.clinica.auth.service.UserDetailsServiceImpl;
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
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserDetailsServiceImpl service;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void save(@RequestBody UserDTO userDTO) {
        service.save(userDTO);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_PACIENTE')")
    public ResponseEntity<List<UserResponseDTO>> list(@PageableDefault(size = 5) Pageable paginacao) {
        Optional<List<UserResponseDTO>> userResponseDTOS = service.list(paginacao);
        return ResponseEntity.of(userResponseDTOS);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void update(@PathVariable String id, @RequestBody DadosAtualizacaoUserDTO dados) {
        service.update(id, dados);
    }


}
