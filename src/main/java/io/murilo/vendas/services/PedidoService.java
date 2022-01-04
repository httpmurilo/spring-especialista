package io.murilo.vendas.services;

import io.murilo.vendas.Dto.PedidoDTO;
import io.murilo.vendas.domain.entity.Pedido;

public interface PedidoService {

    Pedido salvar(PedidoDTO dto);
}
