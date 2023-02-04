package com.br.clinica.paciente.service;

import com.br.clinica.paciente.Paciente;
import com.br.clinica.paciente.controller.PacienteController;
import com.br.clinica.paciente.dto.DadosAtualizacaoPacienete;
import com.br.clinica.paciente.dto.DadosCadastroPacienteDTO;
import com.br.clinica.paciente.dto.PacienteResponseDTO;
import com.br.clinica.paciente.repository.PacienteRepository;
import com.br.clinica.endereco.service.EnderecoService;
import jakarta.validation.Valid;
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
public class PacienteService {

    @Autowired
    private PacienteRepository repository;

    @Autowired
    private EnderecoService enderecoService;

    private final Logger log = LoggerFactory.getLogger(PacienteController.class);


    public void cadastrar(@RequestBody @Valid DadosCadastroPacienteDTO dados) {
        repository.save(new Paciente(dados));
        enderecoService.cadastrar(dados.endereco());
    }

    public Optional<List<PacienteResponseDTO>> listar(@PageableDefault(sort = {"nome"}) Pageable paginacao) {
        List<PacienteResponseDTO> pacienteResponseDTOS = repository.findAll(paginacao)
                .map(PacienteResponseDTO::new)
                .stream()
                .toList();

        return Optional.of(pacienteResponseDTOS)
                .filter(not(List::isEmpty));
    }

    public void atualizar(@PathVariable String cpf, @RequestBody DadosAtualizacaoPacienete dados) {
        var paciente = repository.getReferenceById(cpf);
        paciente.atualizar(dados);
        log.info("dado atualizado");

    }

    public void excluirPorCpf(@PathVariable String id) {
        repository.deleteByCpf(id);
        log.info("paciente deletado");
    }


}
