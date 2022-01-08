package io.murilo.vendas.controller;

import io.murilo.vendas.Dto.AtualizarStatusPedidoDTO;
import io.murilo.vendas.Dto.InformacaoItemPedidoDTO;
import io.murilo.vendas.Dto.InformacoesPedidoDTO;
import io.murilo.vendas.Dto.PedidoDTO;
import io.murilo.vendas.domain.entity.ItemPedido;
import io.murilo.vendas.domain.entity.Pedido;
import io.murilo.vendas.domain.entity.StatusPedido;
import io.murilo.vendas.services.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/{id}")
    public InformacoesPedidoDTO getById(Integer id) {
        return service.obterPedidoCompleto(id)
                .map(this::converter)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Pedido não encontrado"));
    }

    private InformacoesPedidoDTO converter(Pedido pedido) {
        return new InformacoesPedidoDTO(pedido.getId(), pedido.getCliente().getCpf(),
                pedido.getCliente().getNome(), pedido.getTotal(),converter(pedido.getItens()), pedido.getStatus().name());
    }

    private List<InformacaoItemPedidoDTO> converter(List<ItemPedido> itens) {
        if(CollectionUtils.isEmpty(itens)) {
            return Collections.emptyList();
        }

        return itens.stream()
                .map(item ->
                        new InformacaoItemPedidoDTO(item.getProduto().getDescricao(),item.getProduto().getPreco(),item.getQuantidade())).collect(Collectors.toList());
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatus(@PathVariable Integer id, @RequestBody AtualizarStatusPedidoDTO dto) {
        String novoStatus = dto.getNovoStatus();
        service.atualizaStatus(id, StatusPedido.valueOf(novoStatus));
    }
}
