package com.thecode.controledeestoque.repository;

import com.thecode.controledeestoque.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    // Busca produto pelo nome, retornando um único produto
    // Assumindo que o nome é único, se não for, prefira usar o método abaixo
    Produto findByNome(String nome);

    // Busca produtos cujo nome contenha o texto fornecido (não sensível a
    // maiúsculas/minúsculas)
    // Se o nome não for único, este é o método mais seguro
    List<Produto> findByNomeContainingIgnoreCase(String nome);

    // Busca produto pelo código de barras
    Produto findByCodigoBarras(String codigoBarras);

    List<Produto> findByNomeContainingAndCodigoBarrasContaining(Object object, Object object2);
}
