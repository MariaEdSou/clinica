package com.br.clinica.auth.service;

import com.br.clinica.auth.controller.RoleController;
import com.br.clinica.auth.dto.RoleDTO;
import com.br.clinica.auth.enumeration.RoleName;
import com.br.clinica.auth.model.RoleModel;
import com.br.clinica.auth.repository.RoleRepository;
import jakarta.persistence.Entity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class roleServiceTest {

    @Mock
    private RoleRepository repository;

    @InjectMocks
    private RoleService roleService;

    @Test
    void shouldSaveARole() {
        RoleModel mockRole = Mockito.mock(RoleModel.class);
        Mockito.doReturn(mockRole).when(repository).save(Mockito.any());

        Assertions.assertDoesNotThrow(() -> roleService.save(new RoleDTO(RoleName.ROLE_ADMIN)));
    }

    @Test
    void  shouldReturnListWhenFetchRole() {
        RoleModel mockRole = Mockito.mock(RoleModel.class);
        Mockito.doReturn(new PageImpl<>(List.of(mockRole))).when(repository).findAll(Mockito.any(Pageable.class));

        Assertions.assertDoesNotThrow(() -> roleService.list(Mockito.mock(Pageable.class)));
    }





}
