package io.murilo.vendas.repository;

import io.murilo.vendas.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Integer> {

    List<Cliente> findByNome(@Param("nome") String nome);

    boolean existsByNome(String nome);

    @Query("delete from Cliente c where c.nome =:nome ")
    @Modifying
    void deleteByNome(String nome);
}
