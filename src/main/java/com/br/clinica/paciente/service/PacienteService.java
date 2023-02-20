package com.br.clinica.paciente.service;

import com.br.clinica.paciente.Paciente;
import com.br.clinica.paciente.controller.PacienteController;
import com.br.clinica.paciente.dto.DadosAtualizacaoPaciente;
import com.br.clinica.paciente.dto.DadosCadastroPacienteDTO;
import com.br.clinica.paciente.dto.PacienteResponseDTO;
import com.br.clinica.paciente.repository.PacienteRepository;
import com.br.clinica.endereco.service.EnderecoService;
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
public class PacienteService {

    @Autowired
    private PacienteRepository repository;
    @Autowired
    private EnderecoService enderecoService;
    private final static Logger log = LoggerFactory.getLogger(PacienteController.class);

    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public void save(DadosCadastroPacienteDTO dados) {
        repository.save(new Paciente(dados));
        enderecoService.save(dados.endereco());
        log.info("registered user");
    }

    public Optional<List<PacienteResponseDTO>> list(Pageable paginacao) {
        List<PacienteResponseDTO> pacienteResponseDTOS = repository.findAll(paginacao)
                .map(PacienteResponseDTO::new)
                .toList();

        return Optional.of(pacienteResponseDTOS)
                .filter(not(List::isEmpty));
    }

    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(String cpf, DadosAtualizacaoPaciente dados) {
        var paciente = repository.getReferenceById(cpf);
        paciente.atualizar(dados);
        enderecoService.update(dados.dadosAtualizacaoEndereco());
        log.info("update data");
    }

    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirPorCpf(String id) {
        repository.deleteByCpf(id);
        log.info("deleted patient");
    }


}
