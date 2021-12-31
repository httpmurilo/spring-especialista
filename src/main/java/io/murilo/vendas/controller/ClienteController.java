package io.murilo.vendas.controller;

import io.murilo.vendas.domain.entity.Cliente;
import io.murilo.vendas.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable  Integer id){
        Optional<Cliente> cliente = repository.findById(id);
        return ResponseEntity.of(cliente);
    }
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Cliente> save(@RequestBody Cliente cliente) {
        Cliente clienteSalvo = repository.save(cliente);
        return ResponseEntity.of(Optional.of(clienteSalvo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete( @PathVariable Integer id) {
        Optional<Cliente> cliente = repository.findById(id);

        if(!cliente.isPresent()) {
           throw new ResponseStatusException(
                   HttpStatus.NOT_FOUND, "entidade não encontrada"
           );
        }

        repository.delete(cliente.get());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity deletar(@PathVariable Integer id, @RequestBody Cliente cliente) {
        return repository
                .findById(id)
                .map(clienteExistente -> {
                    cliente.setId(clienteExistente.getId());
                    repository.save(cliente);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/")
    public ResponseEntity FindByClienteFilter(Cliente filtro) {
        ExampleMatcher matcher = ExampleMatcher
                                    .matching()
                                    .withIgnoreCase()
                                    .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(filtro, matcher);
        List<Cliente> lista = repository.findAll(example);
        return ResponseEntity.ok(lista);


    }
}

