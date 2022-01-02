package io.murilo.vendas.controller;

import io.murilo.vendas.services.PedidoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pedido")
public class PedidoController {

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    private PedidoService service;





}
