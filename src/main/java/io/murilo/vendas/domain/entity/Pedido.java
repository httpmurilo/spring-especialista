package io.murilo.vendas.domain.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //mapeamento
    //muitos pedidos para um cliente
    //Many minha entidade atual
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;


    @Column(name = "data_pedido")
    private LocalDate dataPedido;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Column(name = "total", precision = 20, scale = 2)
    private BigDecimal total;

    public List<ItemPedido> getItens() {
        if(this.itens == null){
            this.itens = new ArrayList<>();
        }
        return this.itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itens;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusPedido status;

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }
}
