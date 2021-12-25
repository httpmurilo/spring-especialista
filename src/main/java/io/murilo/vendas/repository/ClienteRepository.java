package io.murilo.vendas.repository;

import io.murilo.vendas.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ClienteRepository {

    private static String INSERT = "insert into cliente (nome) values (?)";
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Cliente salvar(Cliente cliente){
        jdbcTemplate.update(INSERT, cliente.getNome());
        return cliente;
    }
}
