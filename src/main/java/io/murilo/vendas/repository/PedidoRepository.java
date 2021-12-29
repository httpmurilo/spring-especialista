package io.murilo.vendas.repository;

import io.murilo.vendas.domain.entity.Cliente;
import io.murilo.vendas.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    List<Pedido> findByCliente(Cliente cliente);
}
