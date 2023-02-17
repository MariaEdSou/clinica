package com.br.clinica.auth.service;

import com.br.clinica.auth.RoleDTO;
import com.br.clinica.auth.controller.RoleController;
import com.br.clinica.auth.model.RoleModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.function.Predicate.not;
@Service
public class RoleService {
    @Autowired
    private RoleRepository repository;
    private final Logger log = LoggerFactory.getLogger(RoleController.class);

    public void cadastar(RoleDTO roleName){
        repository.save(new RoleModel(roleName));
    }

    public Optional<List<RoleDTO>> listar(Pageable paginacao) {
        List<RoleDTO> roleNameDTOS = repository.findAll(paginacao)
                .map(RoleDTO::new)

                .toList();

        return Optional.of(roleNameDTOS)
                .filter(not(List::isEmpty));
    }



}
