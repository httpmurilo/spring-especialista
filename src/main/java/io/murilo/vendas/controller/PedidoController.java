package io.murilo.vendas.controller;

import io.murilo.vendas.Dto.PedidoDTO;
import io.murilo.vendas.domain.entity.Pedido;
import io.murilo.vendas.services.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pedido")
public class PedidoController {

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    private PedidoService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody PedidoDTO dto) {
        Pedido pedido = service.salvar(dto);
        return pedido.getId();
    }




}
