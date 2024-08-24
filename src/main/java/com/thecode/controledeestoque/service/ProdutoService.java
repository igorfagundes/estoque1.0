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
    public void excluir(Long id) {
        produtoRepository.deleteById(id);
    }

    // Cadastrar um novo produto (mesmo método que salvar)
    public Produto cadastrar(Produto produto) {
        return salvar(produto);
    }

    // Modificar um produto existente
    public Produto modificar(Long id, Produto produtoAtualizado) {
        // Verifica se o produto existe
        Optional<Produto> produtoExistente = produtoRepository.findById(id);
        if (produtoExistente.isPresent()) {
            Produto produto = produtoExistente.get();
            // Atualiza os campos do produto existente
            produto.setNome(produtoAtualizado.getNome());
            produto.setPreco(produtoAtualizado.getPreco());
            produto.setDescrição(produtoAtualizado.getDescrição()); // Confirme o nome correto do método
            produto.setImagem(produtoAtualizado.getImagem());
            produto.setCodigoQr(produtoAtualizado.getCodigoQr());
            produto.setQuantidadeEstoque(produtoAtualizado.getQuantidadeEstoque());
            produto.setSaidaEstoque(produtoAtualizado.getSaidaEstoque());
            // Salva as alterações
            return produtoRepository.save(produto);
        } else {
            // Lança exceção ou retorna um valor indicando que o produto não foi encontrado
            throw new RuntimeException("Produto não encontrado com o ID: " + id);
        }
    }
}
