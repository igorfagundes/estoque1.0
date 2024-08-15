package com.thecode.controledeestoque.produtorepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thecode.controledeestoque.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    Produto findBynome(String nome);
}
