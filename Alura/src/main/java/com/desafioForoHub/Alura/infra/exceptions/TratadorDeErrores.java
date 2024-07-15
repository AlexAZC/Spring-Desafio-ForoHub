package com.desafioForoHub.Alura.infra.exceptions;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class TratadorDeErrores {


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarException404(){
        return ResponseEntity.notFound().build();
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarException400(MethodArgumentNotValidException e){

        var errores = e.getFieldErrors().stream()
                .map(ErrorValidacion::new)
                .toList();
        return ResponseEntity.badRequest().body(errores);
    }

}
