package com.thecode.controledeestoque.repository;

import com.thecode.controledeestoque.model.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
    Fornecedor findByNome(String nome);

    List<Fornecedor> findByNomeContaining(String nome);
}
