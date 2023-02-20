package com.br.clinica.consulta.service;

import com.br.clinica.client.ViaCepClient;
import com.br.clinica.consulta.Consulta;
import com.br.clinica.consulta.dto.ConsultaResponseDTO;
import com.br.clinica.consulta.dto.DadosAtualizacaoConsulta;
import com.br.clinica.consulta.dto.DadosCadastroConsultaDTO;
import com.br.clinica.consulta.repository.ConsultaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Optional;

import static java.util.function.Predicate.not;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository repository;

    private final Logger log = LoggerFactory.getLogger(ConsultaService.class);

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void save(DadosCadastroConsultaDTO dadosConsulta) {
        repository.save(new Consulta(dadosConsulta));
        log.info("query registered");
    }

    public Optional<List<ConsultaResponseDTO>> list(Pageable paginacao) {
        List<ConsultaResponseDTO> consultaResponseDTOS = repository.findAll(paginacao)
                .map(ConsultaResponseDTO::new)
                .stream()
                .toList();

        return Optional.of(consultaResponseDTOS)
                .filter(not(List::isEmpty));

    }

    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(DadosAtualizacaoConsulta dados) {
        var consulta = repository.getReferenceById(dados.id());
        consulta.atualizar(dados);
        log.info("update data");

    }

    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(Long id) {
        repository.findById(id).ifPresent(e -> repository.delete(e));
        log.info("query deleted");
    }
}
