package com.br.clinica.paciente.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class ResponseErrorValidationDTO {

    private int code;
    private String message;
    private String fieldError;
}