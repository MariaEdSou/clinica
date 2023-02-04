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
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

import static java.util.function.Predicate.not;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository repository;
    private ViaCepClient viaCepClient;

    private final Logger log = LoggerFactory.getLogger(ConsultaService.class);


    public void cadastrar(@RequestBody DadosCadastroConsultaDTO dadosConsulta) {
        repository.save(new Consulta(dadosConsulta));
        log.info("consulta cadastrada");
    }

    public Optional<List<ConsultaResponseDTO>> listar(@PageableDefault(sort = {"data"}) Pageable paginacao) {
        List<ConsultaResponseDTO> consultaResponseDTOS = repository.findAll(paginacao)
                .map(ConsultaResponseDTO::new)
                .stream()
                .toList();

        return Optional.of(consultaResponseDTOS)
                .filter(not(List::isEmpty));

    }

    public void atualizar(@RequestBody DadosAtualizacaoConsulta dados) {
        var consulta = repository.getReferenceById(dados.id());
        consulta.atualizar(dados);
        log.info("Dado atualizado");

    }

    public void deleteById(@PathVariable Long id) {
        repository.deleteById(id);
        log.info("consulta deletada");
    }
}
