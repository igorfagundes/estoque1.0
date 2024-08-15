package com.thecode.controledeestoque.produtocontroller;

import com.thecode.controledeestoque.model.Produto;
import com.thecode.controledeestoque.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public String cadastrarProduto(Produto produto) {
        produtoService.cadastrarProduto(produto);
        return "redirect:/produtos/gerenciamento";
    }

    @PostMapping("/excluir")
    public String excluirProduto(@RequestParam Long id) {
        produtoService.excluirProduto(id);
        return "redirect:/produtos/gerenciamento";
    }

    @GetMapping("/procurar")
    public String procurarProdutoPorId(@RequestParam Long id, Model model) {
        Optional<Produto> produto = produtoService.procurarProdutoPorId(id);
        if (produto.isPresent()) {
            model.addAttribute("produto", produto.get());
        }
        return "gerenciamento-produtos";
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<Produto> procurarProdutoPorNome(@PathVariable String nome) {
        Produto produto = produtoService.procurarProdutoPorNome(nome);
        return produto != null ? ResponseEntity.ok(produto) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Produto>> listarTodosProdutos() {
        List<Produto> produtos = produtoService.listarTodosProdutos();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/gerenciamento")
    public String gerenciamentoProdutos() {
        return "gerenciamento-produtos"; // Refere-se ao arquivo HTML em
                                         // src/main/resources/templates/gerenciamento-produtos.html
    }
}
