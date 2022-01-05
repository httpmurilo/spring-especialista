package io.murilo.vendas.services.Impl;

import io.murilo.vendas.Dto.ItemPedidoDTO;
import io.murilo.vendas.Dto.PedidoDTO;
import io.murilo.vendas.domain.entity.Cliente;
import io.murilo.vendas.domain.entity.ItemPedido;
import io.murilo.vendas.domain.entity.Pedido;
import io.murilo.vendas.domain.entity.Produto;
import io.murilo.vendas.exceptions.GenericExceptionError;
import io.murilo.vendas.repository.ClienteRepository;
import io.murilo.vendas.repository.ItensPedidoRepository;
import io.murilo.vendas.repository.PedidoRepository;
import io.murilo.vendas.repository.ProdutoRepository;
import io.murilo.vendas.services.PedidoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoServiceImpl implements PedidoService {

    public PedidoServiceImpl(PedidoRepository repository, ClienteRepository clienteRepository, ProdutoRepository produtoRepository,
                             ItensPedidoRepository itensPedidoRepository) {
        this.repository = repository;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
        this.itensPedidoRepository = itensPedidoRepository;

    }

    protected PedidoRepository repository;
    protected ClienteRepository clienteRepository;
    protected ProdutoRepository produtoRepository;
    protected ItensPedidoRepository itensPedidoRepository;

    @Override
    @Transactional
    public Pedido salvar(PedidoDTO dto) {

        Integer idCliente = dto.getCliente();
        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new GenericExceptionError("Cliente não encontrado"));

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());

        pedido.setCliente(cliente);

        List<ItemPedido> itemPedidos = converterItems(pedido, dto.getItems());
        repository.save(pedido);
        itensPedidoRepository.saveAll(itemPedidos);

        pedido.setItens(itemPedidos);

        return pedido;
    }

    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id) {
        return repository.findByIdFetchItens(id);
    }

    private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items) {
        if(items.isEmpty()) {
            throw new GenericExceptionError("Não é possivel realizar um pedido sem itens!!");
        }

        return items.stream()
                .map(dto -> {

                    Integer idProduto = dto.getProduto();

                    Produto produto = produtoRepository.findById(idProduto).orElseThrow(() -> new GenericExceptionError("Produto invalido"));

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);

                   return itemPedido;
                }).collect(Collectors.toList());

    }
}
