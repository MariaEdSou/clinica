package com.br.clinica.controller;

import com.br.clinica.consulta.Consulta;
import com.br.clinica.consultaDTO.DadosCadastroParaConsultaDTO;
import com.br.clinica.repository.ConsultaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {

    @Autowired
    private ConsultaRepository repository;
    private final Logger log = LoggerFactory.getLogger(ConsultaController.class);

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrarPacienteEmConsulta(DadosCadastroParaConsultaDTO dadosConsulta) {
        repository.save(new Consulta(dadosConsulta));
        log.info("consulta cadastrada");
    }


}
