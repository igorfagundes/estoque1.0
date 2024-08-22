package com.thecode.controledeestoque.produtocontroller;

import com.thecode.controledeestoque.model.Produto;
import com.thecode.controledeestoque.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String cadastrarProduto(@RequestParam("nome") String nome,
            @RequestParam("preco") Double preco,
            Model model) {
        Produto produto = new Produto();
        produto.setNome(nome);
        produto.setPreco(preco);
        produtoService.cadastrarProduto(produto);

        model.addAttribute("mensagem", "Produto cadastrado com sucesso!");
        return "redirect:/produtos/listar";
    }

    @GetMapping("/excluir")
    public String mostrarFormularioExclusao(@RequestParam("id") Long id, Model model) {
        Optional<Produto> produto = produtoService.procurarProdutoPorId(id);
        if (produto.isPresent()) {
            model.addAttribute("produto", produto.get());
            return "excluir-produto";
        } else {
            model.addAttribute("mensagem", "Produto não encontrado!");
            return "listar-produtos";
        }
    }

    @PostMapping("/excluir/confirmar")
    public String confirmarExclusao(@RequestParam("id") Long id,
            @RequestParam("confirm") String confirm,
            Model model) {
        if ("sim".equals(confirm)) {
            Optional<Produto> produto = produtoService.procurarProdutoPorId(id);
            if (produto.isPresent()) {
                Produto p = produto.get();
                produtoService.excluirProduto(id);
                model.addAttribute("mensagem", String.format("Produto %d, %s, %.2f excluído com sucesso!",
                        p.getId(), p.getNome(), p.getPreco()));
            } else {
                model.addAttribute("mensagem", "Produto não encontrado!");
            }
        } else {
            model.addAttribute("mensagem", "A exclusão foi cancelada.");
        }

        return "redirect:/principal"; // Redireciona para a página principal
    }

    @GetMapping("/procurar")
    public String procurarProdutoPorId(@RequestParam Long id, Model model) {
        Optional<Produto> produto = produtoService.procurarProdutoPorId(id);
        if (produto.isPresent()) {
            model.addAttribute("produto", produto.get());
            return "procurar-produto";
        } else {
            model.addAttribute("mensagem", "Produto não encontrado!");
            return "listar-produtos";
        }
    }

    @GetMapping("/listar")
    public String listarTodosProdutos(Model model) {
        List<Produto> produtos = produtoService.listarTodosProdutos();
        model.addAttribute("produtos", produtos);
        return "listar-produtos";
    }

    @PostMapping("/modificar")
    public String modificarProduto(@RequestParam Long id,
            @RequestParam String nome,
            @RequestParam double preco,
            Model model) {
        Optional<Produto> produto = produtoService.procurarProdutoPorId(id);
        if (produto.isPresent()) {
            Produto p = produto.get();
            p.setNome(nome);
            p.setPreco(preco);
            produtoService.cadastrarProduto(p);
            model.addAttribute("produto", p);
            model.addAttribute("mensagem", "Produto modificado com sucesso!");
            return "modificar-produto";
        } else {
            model.addAttribute("mensagem", "Produto não encontrado!");
            return "listar-produtos";
        }
    }
}

@Controller
@RequestMapping("/principal")
class PrincipalController {

    @GetMapping
    public String principal() {
        return "principal";
    }
}
