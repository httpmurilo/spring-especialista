package io.murilo.vendas.service;

import io.murilo.vendas.model.Cliente;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    public void salvarCliente(Cliente cliente) {
        validarCliente(cliente);
    }

    public void validarCliente(Cliente cliente) {

    }
}
