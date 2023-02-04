package com.br.clinica.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "viaCep", url = "https://viacep.com.br/ws")
public interface ViaCepClient {


    @GetMapping("/{cep}/json/")
    ResponseEntity<ViaCepDTO> getEndereco(@PathVariable String cep);
}
