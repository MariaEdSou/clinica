package com.br.clinica.paciente.service;

import com.br.clinica.endereco.Endereco;
import com.br.clinica.endereco.dto.DadosAtualizacaoEndereco;
import com.br.clinica.endereco.dto.DadosEnderecoDTO;
import com.br.clinica.endereco.service.EnderecoService;
import com.br.clinica.paciente.Paciente;
import com.br.clinica.paciente.dto.DadosAtualizacaoPaciente;
import com.br.clinica.paciente.dto.DadosCadastroPacienteDTO;
import com.br.clinica.paciente.repository.PacienteRepository;
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

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class PacienteServiceTest {

    public static final String CPF = "1589974152545";
    @Mock
    private PacienteRepository repository;

    @Mock
    private EnderecoService enderecoService;

    @InjectMocks
    private PacienteService pacienteService;


    @Test
    void shoudDoesNotThowExceptionWhenDeleteById() {
        Paciente mockPatient = Mockito.mock(Paciente.class);
        Mockito.doReturn(Optional.of(mockPatient)).when(repository).findById(Mockito.any());

        Assertions.assertDoesNotThrow(() -> repository.findById("75899456214"));
    }

    @Test
    void mustThowAnExceptionWhenDeleteById() {
        Mockito.doReturn(Optional.empty()).when(repository).findById(Mockito.any());

        Assertions.assertThrows(ResponseStatusException.class, () -> pacienteService.deleteByCpf("56988745692"));
    }

    @Test
    void shoudNotThrowsAnExceptionWhenUpdatePatientByCpf() {
        Paciente mockPatient = Mockito.mock(Paciente.class);
        Mockito.doReturn(Optional.of(mockPatient)).when(repository).findById(Mockito.any());
        Mockito.doNothing().when(enderecoService).update(Mockito.any());
        Mockito.doNothing().when(mockPatient).update(Mockito.any());

        Assertions.assertDoesNotThrow(() -> pacienteService.update(CPF, new DadosAtualizacaoPaciente(
                new DadosAtualizacaoEndereco(10L, null, null, null, null, null, null, null),
                "maria", "maria@paciente", "997854628")));
    }

    @Test
    void shoudThowsAnExceptionWhenUpdatePatienByCpf() {
        Mockito.doReturn(Optional.empty()).when(repository).findById(Mockito.any());

        Assertions.assertThrows(ResponseStatusException.class, () -> pacienteService.update(CPF, new DadosAtualizacaoPaciente(
                new DadosAtualizacaoEndereco(10L, null, null, null, null, null, null, null),
                "maria", "maria@paciente", "997854628")));
    }


    @Test
    void shoudReturnListWhenFetchAPatient() {
        Paciente mockPatient = Mockito.mock(Paciente.class);
        Endereco mockAddress = Mockito.mock(Endereco.class);
        Mockito.doReturn(mockAddress).when(mockPatient).getEndereco();
        Mockito.doReturn(new PageImpl<>(List.of(mockPatient))).when(repository).findAll(Mockito.any(Pageable.class));

        Assertions.assertDoesNotThrow(() -> pacienteService.list(Mockito.mock(Pageable.class)));
    }


    @Test
    void shoudReturnPatient() {
        Paciente mockPatient = Mockito.mock(Paciente.class);
        Mockito.doReturn(mockPatient).when(repository).save(Mockito.any());

        Assertions.assertDoesNotThrow(() -> pacienteService.save(new DadosCadastroPacienteDTO("58944785948",
                "jose", "998456751", "jose@paciente", new DadosEnderecoDTO("58944785948", 18990L, 18L))));
    }
}
