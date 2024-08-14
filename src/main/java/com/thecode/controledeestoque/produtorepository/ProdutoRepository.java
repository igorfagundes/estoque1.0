package com.thecode.controledeestoque.produtorepository;

import com.thecode.controledeestoque.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    Produto findBynome(String nome);
}
