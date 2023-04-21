package com.br.clinica.endereco.service;

import com.br.clinica.client.ViaCepClient;
import com.br.clinica.client.ViaCepDTO;
import com.br.clinica.endereco.Endereco;
import com.br.clinica.endereco.controller.EnderecoController;
import com.br.clinica.endereco.dto.DadosAtualizacaoEndereco;
import com.br.clinica.endereco.dto.DadosEnderecoDTO;
import com.br.clinica.endereco.repository.EnderecoRepository;
import com.br.clinica.paciente.Paciente;
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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class EnderecoServiceTest {

    @Mock
    private EnderecoRepository repository;
    @Mock
    private ViaCepClient viaCepClient;

    @InjectMocks
    private EnderecoService enderecoService;

    @Test
    void shoudDoesNotThowsExceptionDeleteByAddress() {
        Endereco mockAddress = Mockito.mock(Endereco.class);
        Mockito.doReturn(Optional.of(mockAddress)).when(repository).findById(Mockito.any());
        Mockito.doNothing().when(repository).delete(Mockito.any(Endereco.class));

        Assertions.assertDoesNotThrow(() -> enderecoService.deleteById(10L));

    }

    @Test
    void shouldThrowsAnExceptionWhenDeleteAddress() {
        Mockito.doReturn(Optional.empty()).when(repository).findById(Mockito.any());

        Assertions.assertThrows(ResponseStatusException.class, () -> enderecoService.deleteById(20L));
    }

    @Test
    void shouldNotThrowsExceptionWhenUpdateAddress() {
        Endereco mockAddress = Mockito.mock(Endereco.class);
        Mockito.doReturn(Optional.of(mockAddress)).when(repository).findById(Mockito.any());
        Mockito.doNothing().when(mockAddress).atualizarInf(Mockito.any());

        Assertions.assertDoesNotThrow(() -> enderecoService.update(new DadosAtualizacaoEndereco(20L, 18990L, "OURINHOS",
                "SP", "CDHU", "AMELHA MARIA", 15L, "CASA")));

    }

    @Test
    void shouldThrowsExceptionWhenUpdateAddres() {
        Mockito.doReturn(Optional.empty()).when(repository).findById(Mockito.any());

        Assertions.assertThrows(ResponseStatusException.class, () -> enderecoService.update(new DadosAtualizacaoEndereco(
                20L, 18990L, "OURINHOS", "SP", "CDHU", "AMELHA MARIA", 15L, "CASA")));
    }

    @Test
    void mustRetunListWhenFetchAddress() {
        Endereco mockAddress = Mockito.mock(Endereco.class);
        Paciente mockPatient = Mockito.mock(Paciente.class);
        Mockito.doReturn(mockPatient).when(mockAddress).getPaciente();
        Mockito.doReturn(mockAddress).when(mockPatient).getEndereco();
        Mockito.doReturn(new PageImpl<>(List.of(mockAddress))).when(repository).findAll(Mockito.any(Pageable.class));

        Assertions.assertDoesNotThrow(() -> enderecoService.list(Mockito.mock(Pageable.class)));

    }

    @Test
    void shoudReturnAddress() {
        Endereco mockAddress = Mockito.mock(Endereco.class);
        ResponseEntity<ViaCepDTO> respostViaCep = ResponseEntity.status(200).body(new ViaCepDTO(
                "18990", "Maria", "Casa", "Amelha", "Cidade", "SP"));
        Mockito.doReturn(respostViaCep).when(viaCepClient).getEndereco(Mockito.any());
        Mockito.doReturn(mockAddress).when(repository).save(Mockito.any());

        Assertions.assertDoesNotThrow(() -> enderecoService.save(new DadosEnderecoDTO(
                "15899648575", 18990L, 500L)));
    }

}
