package io.murilo.vendas.Dto;

import java.math.BigDecimal;
import java.util.List;

public class InformacoesPedidoDTO {

    public InformacoesPedidoDTO(Integer codigo, String cpf, String nomeCliente, BigDecimal total, List<InformacaoItemPedidoDTO> items) {
        this.codigo = codigo;
        this.cpf = cpf;
        this.nomeCliente = nomeCliente;
        this.total = total;
        this.items = items;
    }

    public InformacoesPedidoDTO() {
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<InformacaoItemPedidoDTO> getItems() {
        return items;
    }

    public void setItems(List<InformacaoItemPedidoDTO> items) {
        this.items = items;
    }

    private Integer codigo;
    private String cpf;
    private String nomeCliente;
    private BigDecimal total;
    private List<InformacaoItemPedidoDTO> items;

}
