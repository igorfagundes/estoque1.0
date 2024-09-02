package com.thecode.controledeestoque.service;

import com.thecode.controledeestoque.model.Produto;
import com.thecode.controledeestoque.repository.ProdutoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    // Listar todos os produtos
    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    // Encontrar produto por ID, retorna null se não encontrado
    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id).orElse(null);
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

    // Buscar produtos por nome
    public List<Produto> buscarPorNome(String nome) {
        return produtoRepository.findByNomeContainingIgnoreCase(nome);
    }

    // Buscar produto por código de barras
    public Produto buscarPorCodigoBarras(String codigoBarras) {
        return produtoRepository.findByCodigoBarras(codigoBarras);
    }

    // Modificar um produto existente
    public boolean modificar(Long id, Produto produtoAtualizado) {
        Produto produtoExistente = produtoRepository.findById(id).orElse(null);
        if (produtoExistente != null) {
            produtoExistente.setNome(produtoAtualizado.getNome());
            produtoExistente.setPreco(produtoAtualizado.getPreco());
            produtoExistente.setDescricao(produtoAtualizado.getDescricao());
            produtoExistente.setImagem(produtoAtualizado.getImagem());
            produtoExistente.setCodigoQr(produtoAtualizado.getCodigoQr());
            produtoExistente.setQuantidadeEstoque(produtoAtualizado.getQuantidadeEstoque());
            produtoRepository.save(produtoExistente);
            return true;
        }
        return false;
    }

    public List<Produto> procurarProdutos(String nome, String codigoBarras, String categoria) {
        // Se todos os critérios forem nulos ou vazios, retorne todos os produtos
        if ((nome == null || nome.isEmpty()) && (codigoBarras == null || codigoBarras.isEmpty())
                && (categoria == null || categoria.isEmpty())) {
            return produtoRepository.findAll();
        }

        // Exemplo simples usando o repository padrão
        return produtoRepository.findByNomeContainingAndCodigoBarrasContaining(
                nome != null ? nome : "",
                codigoBarras != null ? codigoBarras : "");
    }
}
