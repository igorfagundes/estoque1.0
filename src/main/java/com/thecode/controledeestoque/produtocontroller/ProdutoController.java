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

    @GetMapping("/cadastrar")
    public String mostrarFormCadastro(Model model) {
        model.addAttribute("produto", new Produto());
        return "cadastrar-produto";
    }

    @PostMapping
    public String cadastrarProduto(Produto produto, Model model) {
        produtoService.cadastrarProduto(produto);
        model.addAttribute("produto", produto);
        return "cadastrar-produto";
    }

    @PostMapping("/excluir")
    public String excluirProduto(@RequestParam Long id, Model model) {
        Optional<Produto> produto = produtoService.procurarProdutoPorId(id);
        if (produto.isPresent()) {
            produtoService.excluirProduto(id);
            model.addAttribute("produto", produto.get());
        }
        return "excluir-produto";
    }

    @GetMapping("/procurar")
    public String procurarProdutoPorId(@RequestParam Long id, Model model) {
        Optional<Produto> produto = produtoService.procurarProdutoPorId(id);
        produto.ifPresent(value -> model.addAttribute("produto", value));
        return "procurar-produto";
    }

    @GetMapping("/listar")
    public String listarTodosProdutos(Model model) {
        List<Produto> produtos = produtoService.listarTodosProdutos();
        model.addAttribute("produtos", produtos);
        return "listar-produtos";
    }

    @PostMapping("/modificar")
    public String modificarProduto(@RequestParam Long id, @RequestParam String nome, @RequestParam double preco,
            Model model) {
        Optional<Produto> produto = produtoService.procurarProdutoPorId(id);
        if (produto.isPresent()) {
            Produto p = produto.get();
            p.setNome(nome);
            p.setPreco(preco);
            produtoService.cadastrarProduto(p);
            model.addAttribute("produto", p);
        }
        return "modificar-produto";
    }
}
