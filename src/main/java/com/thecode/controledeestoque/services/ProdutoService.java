package com.thecode.controledeestoque.services;

import com.thecode.controledeestoque.model.Produto;
import com.thecode.controledeestoque.produtorepository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    public void cadastrarProduto(Produto produto) {
        produtoRepository.save(produto);
    }

    public void excluirProduto(Long id) {
        produtoRepository.deleteById(id);
    }

    public Optional<Produto> procurarProdutoPorId(Long id) {
        return produtoRepository.findById(id);
    }

    public Produto procurarProdutoPorNome(String nome) {
        return produtoRepository.findBynome(nome);
    }

    public List<Produto> listarTodosProdutos() {
        return produtoRepository.findAll();
    }
}
