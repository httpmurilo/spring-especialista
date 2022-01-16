package io.murilo.vendas.controller;

import io.jsonwebtoken.ExpiredJwtException;
import io.murilo.vendas.exceptions.GenericExceptionError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

@org.springframework.web.bind.annotation.RestControllerAdvice
public class RestControllerAdvice {

    @ExceptionHandler(GenericExceptionError.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handlerErros(GenericExceptionError exceptionError)  {
        String mensagemErro = exceptionError.getMessage();
        return new ApiErrors(mensagemErro);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleMethodNotValidException(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                                .getAllErrors()
                                .stream()
                                .map(e -> e.getDefaultMessage())
                                .collect(Collectors.toList());

        return new ApiErrors(errors);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleMethodExpiredToken(ExpiredJwtException ex) {
        String errors = ex.getMessage();
        return new ApiErrors(errors);
    }

}
