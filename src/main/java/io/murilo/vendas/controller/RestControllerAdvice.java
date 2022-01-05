package io.murilo.vendas.controller;

import io.murilo.vendas.exceptions.GenericExceptionError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@org.springframework.web.bind.annotation.RestControllerAdvice
public class RestControllerAdvice {

    @ExceptionHandler(GenericExceptionError.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handlerErros(GenericExceptionError exceptionError)  {
        String mensagemErro = exceptionError.getMessage();
        return new ApiErrors(mensagemErro);
    }
}
