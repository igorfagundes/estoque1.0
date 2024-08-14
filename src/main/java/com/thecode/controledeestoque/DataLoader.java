package com.thecode.controledeestoque;

import com.thecode.controledeestoque.model.Produto;
import com.thecode.controledeestoque.produtorepository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public void run(String... args) throws Exception {
        Produto produto = new Produto();
        produto.setNome("Produto Teste");
        produto.setPreco(29.99);
        produtoRepository.save(produto);
    }
}
