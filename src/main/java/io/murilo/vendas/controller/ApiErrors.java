package io.murilo.vendas.controller;

import java.util.Arrays;
import java.util.List;

public class ApiErrors {

    public List<String> getErrors() {
        return errors;
    }

    private List<String> errors;

    public ApiErrors(String mensagem) {
        this.errors = Arrays.asList(mensagem);
    }
}
