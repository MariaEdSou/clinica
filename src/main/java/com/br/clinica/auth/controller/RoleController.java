package com.br.clinica.auth.controller;

import com.br.clinica.auth.dto.RoleDTO;
import com.br.clinica.auth.service.RoleService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("/roles")
public class RoleController {

    private final Logger log = LoggerFactory.getLogger(RoleController.class);
    private final RoleService roleService;


    public RoleController(RoleService roleService) {
        this.roleService = roleService;


    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void cadastar(@RequestBody @Valid RoleDTO roleName) {
        roleService.cadastar(roleName);

    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN' , 'ROLE_USER')")
    public ResponseEntity<List<RoleDTO>> listar(@PageableDefault(sort = {"roleName"}) Pageable paginacao) {
        Optional<List<RoleDTO>> roleNameDTOS = roleService.listar(paginacao);
        return ResponseEntity.of(roleNameDTOS);
    }


}
