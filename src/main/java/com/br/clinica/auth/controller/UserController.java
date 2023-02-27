package com.br.clinica.auth.controller;

import com.br.clinica.auth.dto.DadosAtualizacaoUserDTO;
import com.br.clinica.auth.dto.UserDTO;
import com.br.clinica.auth.dto.UserResponseDTO;
import com.br.clinica.auth.service.UserDetailsServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "cadastro de usuarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201"),
            @ApiResponse(responseCode = "400"),
            @ApiResponse(responseCode = "403"),
            @ApiResponse(responseCode = "401"),
            @ApiResponse(responseCode = "500"),
    })
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody UserDTO userDTO) {
        service.save(userDTO);
    }

    @Operation(summary = "listagem de usuarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "403"),
            @ApiResponse(responseCode = "404"),
            @ApiResponse(responseCode = "401"),
            @ApiResponse(responseCode = "500"),
    })
    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_PACIENTE')")
    public ResponseEntity<List<UserResponseDTO>> list(@PageableDefault(size = 5) Pageable paginacao) {
        Optional<List<UserResponseDTO>> userResponseDTOS = service.list(paginacao);
        return ResponseEntity.of(userResponseDTOS);
    }

    @Operation(summary = "atualizacao de usuarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204"),
            @ApiResponse(responseCode = "403"),
            @ApiResponse(responseCode = "401"),
            @ApiResponse(responseCode = "500"),
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable String id, @RequestBody DadosAtualizacaoUserDTO dados) {
        service.update(id, dados);
    }


}
