package com.thecode.controledeestoque.service;

import com.thecode.controledeestoque.model.Produto;
import com.thecode.controledeestoque.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    // Listar todos os produtos
    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    // Encontrar produto por ID
    public Optional<Produto> encontrarPorId(Long id) {
        return produtoRepository.findById(id);
    }

    // Salvar (cadastrar ou atualizar) um produto
    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

    // Excluir produto por ID
    public boolean excluir(Long id) {
        if (produtoRepository.existsById(id)) {
            produtoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Buscar produto por ID
    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id).orElse(null);
    }

    // Buscar produto por nome
    public Produto buscarPorNome(String nome) {
        return produtoRepository.findByNome(nome);
    }

    // Buscar produto por código de barras
    public Produto buscarPorCodigoBarras(String codigoBarras) {
        return produtoRepository.findByCodigoBarras(codigoBarras);
    }

    // Modificar um produto existente
    public boolean modificar(Long id, Produto produtoAtualizado) {
        Optional<Produto> produtoExistente = produtoRepository.findById(id);
        if (produtoExistente.isPresent()) {
            Produto produto = produtoExistente.get();
            produto.setNome(produtoAtualizado.getNome());
            produto.setPreco(produtoAtualizado.getPreco());
            produto.setDescricao(produtoAtualizado.getDescricao());
            produto.setImagem(produtoAtualizado.getImagem());
            produto.setCodigoQr(produtoAtualizado.getCodigoQr());
            produto.setQuantidadeEstoque(produtoAtualizado.getQuantidadeEstoque());
            produtoRepository.save(produto);
            return true;
        }
        return false;
    }
}
