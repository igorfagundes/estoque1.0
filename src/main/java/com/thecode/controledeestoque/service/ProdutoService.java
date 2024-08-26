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

    // Cadastrar um novo produto (mesmo método que salvar)
    public Produto cadastrar(Produto produto) {
        return salvar(produto);
    }

    // Modificar um produto existente
    public boolean modificar(Long id, Produto produtoAtualizado) {
        // Verifica se o produto existe
        Optional<Produto> produtoExistente = produtoRepository.findById(id);
        if (produtoExistente.isPresent()) {
            Produto produto = produtoExistente.get();
            // Atualiza os campos do produto existente
            produto.setNome(produtoAtualizado.getNome());
            produto.setPreco(produtoAtualizado.getPreco());
            produto.setDescricao(produtoAtualizado.getDescricao()); // Verifique o nome correto do método
            produto.setImagem(produtoAtualizado.getImagem());
            produto.setCodigoQr(produtoAtualizado.getCodigoQr());
            produto.setQuantidadeEstoque(produtoAtualizado.getQuantidadeEstoque());
            // Salva as alterações
            produtoRepository.save(produto);
            return true;
        }
        return false; // Produto não encontrado
    }
}
