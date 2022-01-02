package io.murilo.vendas.services.Impl;

import io.murilo.vendas.repository.PedidoRepository;
import io.murilo.vendas.services.PedidoService;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService {

    public PedidoServiceImpl(PedidoRepository repository) {
        this.repository = repository;
    }

    private PedidoRepository repository;
}
