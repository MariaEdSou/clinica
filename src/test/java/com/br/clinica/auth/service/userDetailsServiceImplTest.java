package com.br.clinica.auth.service;

import com.br.clinica.auth.dto.DadosAtualizacaoUserDTO;
import com.br.clinica.auth.dto.UserDTO;
import com.br.clinica.auth.model.UserModel;
import com.br.clinica.auth.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class userDetailsServiceImplTest {

    @Mock
    private UserRepository userRepositoryy;

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;


   @Test
    void shouldReturnAUser(){
       UserModel mockUser = Mockito.mock(UserModel.class);
       Mockito.doReturn(mockUser).when(userRepositoryy).save(Mockito.any());

       Assertions.assertDoesNotThrow(() -> userDetailsService.save(new UserDTO("Joao","05100517","ROLE_ADMIN")));
   }

   @Test
    void shoudReturnList() {
       UserModel mockUser = Mockito.mock(UserModel.class);
       Mockito.doReturn(new PageImpl<>(List.of(mockUser))).when(userRepositoryy).findAll(Mockito.any(Pageable.class));

       Assertions.assertDoesNotThrow(() -> userDetailsService.list(Mockito.mock(Pageable.class)));

   }

   @Test
    void shouldReturnUpdate() {
       UserModel mockUser = Mockito.mock(UserModel.class);
       Mockito.doReturn(mockUser).when(userRepositoryy).getReferenceById(Mockito.any());

       Assertions.assertDoesNotThrow(() -> userDetailsService.update("20117800",new DadosAtualizacaoUserDTO()));
   }












}
