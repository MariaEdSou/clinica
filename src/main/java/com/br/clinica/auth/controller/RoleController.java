package com.br.clinica.auth.controller;

import com.br.clinica.auth.dto.RoleDTO;
import com.br.clinica.auth.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @Operation(summary = "cadastro de roles")
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
    public void save(@RequestBody @Valid RoleDTO roleName) {
        roleService.save(roleName);
    }

    @Operation(summary = "listagem de roles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "403"),
            @ApiResponse(responseCode = "404"),
            @ApiResponse(responseCode = "401"),
            @ApiResponse(responseCode = "500"),
    })
    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN' , 'ROLE_USER')")
    public ResponseEntity<List<RoleDTO>> list(@PageableDefault(sort = {"roleName"}) Pageable paginacao) {
        Optional<List<RoleDTO>> roleNameDTOS = roleService.list(paginacao);
        return ResponseEntity.of(roleNameDTOS);
    }


}
