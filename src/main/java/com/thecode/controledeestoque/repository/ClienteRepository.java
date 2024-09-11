package com.thecode.controledeestoque.repository;

import com.thecode.controledeestoque.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    // Método para buscar clientes pelo nome
    List<Cliente> findByNomeContaining(String nome);
}
