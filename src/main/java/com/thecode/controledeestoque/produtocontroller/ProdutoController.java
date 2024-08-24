package com.thecode.controledeestoque.produtocontroller;

import com.thecode.controledeestoque.model.Produto;
import com.thecode.controledeestoque.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/principal/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    // Mostrar formulário de cadastro
    @GetMapping("/cadastrar")
    public String mostrarFormCadastro(Model model) {
        model.addAttribute("produto", new Produto());
        return "cadastrar-produto";
    }

    // Processar cadastro de produto
    @PostMapping("/cadastrar")
    public String cadastrarProduto(@RequestParam("nome") String nome,
            @RequestParam("preco") Double preco,
            Model model) {
        Produto produto = new Produto();
        produto.setNome(nome);
        produto.setPreco(preco);
        produtoService.cadastrarProduto(produto);

        model.addAttribute("status", "success");
        model.addAttribute("mensagem", String.format("Produto cadastrado com sucesso! ID: %d, Nome: %s, Preço: %.2f",
                produto.getId(), produto.getNome(), produto.getPreco()));
        return "resultado-produto";
    }

    // Mostrar formulário de exclusão
    @GetMapping("/excluir")
    public String mostrarFormularioExclusao(@RequestParam("id") Long id, Model model) {
        Optional<Produto> produto = produtoService.procurarProdutoPorId(id);
        if (produto.isPresent()) {
            model.addAttribute("produto", produto.get());
            return "excluir-produto";
        } else {
            model.addAttribute("mensagem", "Produto não encontrado!");
            return "resultado-produto";
        }
    }

    // Confirmar exclusão de produto
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
        return "resultado-produto";
    }

    // Procurar produto por ID
    @GetMapping("/procurar")
    public String procurarProdutoPorId(@RequestParam Long id, Model model) {
        Optional<Produto> produto = produtoService.procurarProdutoPorId(id);
        if (produto.isPresent()) {
            model.addAttribute("produto", produto.get());
            return "resultado-produto";
        } else {
            model.addAttribute("mensagem", "Produto não encontrado!");
            return "resultado-produto";
        }
    }

    // Modificar produto
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
            model.addAttribute("mensagem",
                    String.format("Produto modificado com sucesso! ID: %d, Nome: %s, Preço: %.2f",
                            p.getId(), p.getNome(), p.getPreco()));
            return "resultado-produto";
        } else {
            model.addAttribute("mensagem", "Produto não encontrado!");
            return "resultado-produto";
        }
    }
}
