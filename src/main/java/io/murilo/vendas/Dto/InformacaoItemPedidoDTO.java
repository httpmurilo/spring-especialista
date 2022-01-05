package io.murilo.vendas.Dto;

import java.math.BigDecimal;

public class InformacaoItemPedidoDTO {

    public InformacaoItemPedidoDTO() {
    }

    public InformacaoItemPedidoDTO(String descricaoProduto, BigDecimal precoUnitario, Integer quantidade) {
        this.descricaoProduto = descricaoProduto;
        this.precoUnitario = precoUnitario;
        this.quantidade = quantidade;
    }

    private String descricaoProduto;

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    private BigDecimal precoUnitario;
    private Integer quantidade;
}
