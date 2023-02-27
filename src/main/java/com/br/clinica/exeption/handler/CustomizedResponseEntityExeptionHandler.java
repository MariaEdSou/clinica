package com.br.clinica.exeption.handler;

import com.br.clinica.paciente.dto.ResponseErrorValidationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
@RestControllerAdvice
public class CustomizedResponseEntityExeptionHandler {

    @Autowired
    private MessageSource messageSource;

    private final static String PREFIX_MESSAGE = "O campo %s %s";

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ResponseErrorDTO> handleAllExceptions(Exception ex) {
        ResponseErrorDTO responseErrorDTO = new ResponseErrorDTO(500, "Algo deu errado, tente novamente mais tarde");
        return new ResponseEntity<>(responseErrorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ResponseErrorDTO> ResponseStatusException(ResponseStatusException ex) {
        ResponseErrorDTO responseErrorDTO = new ResponseErrorDTO(ex.getStatusCode().value(), ex.getMessage());
        return ResponseEntity.status(ex.getStatusCode()).body(responseErrorDTO); 
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ResponseEntity<List<ResponseErrorValidationDTO>> handlingException(BindException exception) {
        final List<FieldError> fieldsError = exception.getBindingResult().getFieldErrors();

        return ResponseEntity.status(BAD_REQUEST)
                .body(
                        fieldsError.stream().map(fieldError -> {
                            var message = this.messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
                            return ResponseErrorValidationDTO.builder()
                                    .code(400)
                                    .message(String.format(PREFIX_MESSAGE, fieldError.getField(), message))
                                    .fieldError(fieldError.getField())
                                    .build();
                        }).toList());
    }
}
