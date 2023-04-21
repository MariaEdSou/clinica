package com.br.clinica.consulta.service;

import com.br.clinica.consulta.Consulta;
import com.br.clinica.consulta.dto.DadosCadastroConsultaDTO;
import com.br.clinica.consulta.dto.DataUpdateConsultation;
import com.br.clinica.consulta.dto.EspecialidadeConsulta;
import com.br.clinica.consulta.repository.ConsultaRepository;
import com.br.clinica.endereco.Endereco;
import com.br.clinica.paciente.Paciente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

//@ExtendWith(MockitoExtension.class)fala que a class vai ter mock
@ExtendWith(MockitoExtension.class)
public class ConsultaServiceTest {

    @Mock
    private ConsultaRepository repository;

    @InjectMocks
    private ConsultaService consultaService;

    @Test
    void shouldDoesNotThrowExceptionWhenDeleteById() {
        Consulta mockConsulta = Mockito.mock(Consulta.class);
        Mockito.doReturn(Optional.of(mockConsulta)).when(repository).findById(Mockito.any());
        Mockito.doNothing().when(repository).delete(Mockito.any(Consulta.class));

        Assertions.assertDoesNotThrow(() -> consultaService.deleteById(10L));
    }

    @Test
    void shouldThrowAnExceptionWhenDeletQueryById() {
        Mockito.doReturn(Optional.empty()).when(repository).findById(Mockito.any());

        Assertions.assertThrows(ResponseStatusException.class, () -> consultaService.deleteById(10L));
    }

    @Test
    void mustnotThrowAnExceptionWhenUpdateInQuery() {
        Consulta mockConsulta = Mockito.mock(Consulta.class);
        Mockito.doReturn(Optional.of(mockConsulta)).when(repository).findById(Mockito.any());
        Mockito.doNothing().when(mockConsulta).update(Mockito.any());

        Assertions.assertDoesNotThrow(() -> consultaService.update(new DataUpdateConsultation(20L, LocalDate.now(), "20:30", "00", EspecialidadeConsulta.GINECOLOGIA)));
    }

    @Test
    void mustThrowAnExceptionWhenUpdatingAQuery() {
        Mockito.doReturn(Optional.empty()).when(repository).findById(Mockito.any());

        Assertions.assertThrows(ResponseStatusException.class, () -> consultaService.update(new DataUpdateConsultation(20L, LocalDate.now(), "20:30", "00", EspecialidadeConsulta.GINECOLOGIA)));
    }

    @Test
    void mustReturnAListWhenFetchAQuery() {
        Consulta mockConsulta = Mockito.mock(Consulta.class);
        Paciente mockPaciente = Mockito.mock(Paciente.class);
        Mockito.doReturn(Mockito.mock(Endereco.class))
                .when(mockPaciente).getEndereco();
        Mockito.doReturn(mockPaciente).when(mockConsulta).getPaciente();
        Mockito.doReturn(new PageImpl<>(List.of(mockConsulta))).when(repository).findAll(Mockito.any(Pageable.class));

        Assertions.assertDoesNotThrow(() -> consultaService.list(Mockito.mock(Pageable.class)));
    }

    @Test
    void shouldSaveAQuery() {
        Consulta mockConsulta = Mockito.mock(Consulta.class);
        Mockito.doReturn(mockConsulta).when(repository).save(Mockito.any());

       Assertions.assertDoesNotThrow(() -> consultaService.save(new DadosCadastroConsultaDTO("20588914875",LocalDate.now(),"20:20",10.5, EspecialidadeConsulta.CARDIOLOGIA)));
    }

}
