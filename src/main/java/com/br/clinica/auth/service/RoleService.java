package com.br.clinica.auth.service;

import com.br.clinica.auth.dto.RoleDTO;
import com.br.clinica.auth.controller.RoleController;
import com.br.clinica.auth.model.RoleModel;
import com.br.clinica.auth.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Optional;

import static java.util.function.Predicate.not;

@Service
public class RoleService {
    @Autowired
    private RoleRepository repository;
    private final Logger log = LoggerFactory.getLogger(RoleController.class);

    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public void save(RoleDTO roleName) {
        repository.save(new RoleModel(roleName));
        log.info("registered role");
    }

    public Optional<List<RoleDTO>> list(Pageable paginacao) {
        List<RoleDTO> roleNameDTOS = repository.findAll(paginacao)
                .map(RoleDTO::new)
                .toList();

        return Optional.of(roleNameDTOS)
                .filter(not(List::isEmpty));
    }


}
