package com.br.clinica.exeption.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseErrorDTO {

    private int code;
    private String message;


}
