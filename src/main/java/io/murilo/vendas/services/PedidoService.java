package io.murilo.vendas.services;

import io.murilo.vendas.Dto.PedidoDTO;
import io.murilo.vendas.domain.entity.Pedido;
import io.murilo.vendas.domain.entity.StatusPedido;

import java.util.Optional;

public interface PedidoService {

    Pedido salvar(PedidoDTO dto);
    Optional<Pedido> obterPedidoCompleto(Integer id);
    void atualizaStatus(Integer id, StatusPedido statusPedido);
}
