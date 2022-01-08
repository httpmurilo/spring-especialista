package io.murilo.vendas.Dto;

public class AtualizarStatusPedidoDTO {
    public String getNovoStatus() {
        return novoStatus;
    }

    public void setNovoStatus(String novoStatus) {
        this.novoStatus = novoStatus;
    }

    private String novoStatus;
}
