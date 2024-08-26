package com.thecode.controledeestoque.repository;

import com.thecode.controledeestoque.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    Produto findByNome(String nome);

    Produto findByCodigoBarras(String codigoBarras);
    // Métodos de consulta personalizados podem ser adicionados aqui, se necessário
}
