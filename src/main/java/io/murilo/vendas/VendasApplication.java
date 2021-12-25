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

            Cliente cliente2 = new Cliente("Murilo Eduardo");
            clienteRepository.salvar(cliente2);

            List<Cliente> clienteList = new ArrayList<>();
            clienteList = clienteRepository.obterTodos();
            clienteList.forEach(cliente1 -> {
                System.out.println(cliente1.getId());
                System.out.println(cliente1.getNome());
            });
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }


}
