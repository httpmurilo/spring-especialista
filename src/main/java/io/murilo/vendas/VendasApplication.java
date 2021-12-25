package io.murilo.vendas;

import io.murilo.vendas.domain.entity.Cliente;
import io.murilo.vendas.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class VendasApplication {


    @Bean
    public CommandLineRunner init(@Autowired ClienteRepository clienteRepository){
        return args -> {
            Cliente cliente = new Cliente("Murilo");
            clienteRepository.salvar(cliente);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }


}
