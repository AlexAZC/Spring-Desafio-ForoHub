package com.desafioForoHub.Alura.infra.exceptions;

import org.springframework.validation.FieldError;

public record ErrorValidacion(
        String campo,
        String error
) {

    public ErrorValidacion(FieldError fieldError){
        this(fieldError.getField(), fieldError.getDefaultMessage());
    }

}
