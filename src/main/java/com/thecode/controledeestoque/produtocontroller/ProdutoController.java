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
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/produtos/cadastrar")
    public String mostrarFormCadastro(Model model) {
        model.addAttribute("produto", new Produto());
        return "cadastrar-produto"; // Nome do arquivo HTML para o formulário de cadastro
    }

    @PostMapping("/produtos")
    public String cadastrarProduto(@RequestParam("nome") String nome,
            @RequestParam("preco") Double preco,
            Model model) {
        Produto produto = new Produto();
        produto.setNome(nome);
        produto.setPreco(preco);

        produtoService.cadastrarProduto(produto);

        // Adiciona uma mensagem de sucesso
        model.addAttribute("mensagem", "Produto cadastrado com sucesso!");
        return "redirect:/produtos/listar"; // Redireciona para a página de listagem ou outra página relevante
    }

    @GetMapping("/produtos/excluir")
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

    @PostMapping("/produtos/excluir/confirmar")
    public String confirmarExclusao(@RequestParam("id") Long id,
            @RequestParam("confirm") String confirm,
            Model model) {
        if ("sim".equals(confirm)) {
            Optional<Produto> produto = produtoService.procurarProdutoPorId(id);
            if (produto.isPresent()) {
                produtoService.excluirProduto(id);
                model.addAttribute("produto", produto.get());
                model.addAttribute("produtoExcluido", true);
                model.addAttribute("mensagem", "Produto excluído com sucesso!");
                return "excluir-produto"; // Volta para a página de confirmação da exclusão com mensagem de sucesso
            } else {
                model.addAttribute("mensagem", "Produto não encontrado!");
                return "listar-produtos";
            }
        } else {
            return "redirect:/produtos/excluir?id=" + id; // Retorna para a página de exclusão com o ID do produto
        }
    }

    @GetMapping("/produtos/procurar")
    public String procurarProdutoPorId(@RequestParam Long id, Model model) {
        Optional<Produto> produto = produtoService.procurarProdutoPorId(id);
        if (produto.isPresent()) {
            model.addAttribute("produto", produto.get());
            return "procurar-produto";
        } else {
            model.addAttribute("mensagem", "Produto não encontrado!");
            return "listar-produtos"; // Volta para a listagem se não encontrar o produto
        }
    }

    @GetMapping("/produtos/listar")
    public String listarTodosProdutos(Model model) {
        List<Produto> produtos = produtoService.listarTodosProdutos();
        model.addAttribute("produtos", produtos);
        return "listar-produtos"; // Nome do arquivo HTML para listar produtos
    }

    @PostMapping("/produtos/modificar")
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
            return "modificar-produto"; // Volta para a página de modificação com as informações atualizadas
        } else {
            model.addAttribute("mensagem", "Produto não encontrado!");
            return "listar-produtos";
        }
    }

    // Classe interna para lidar com a navegação para a página principal
    @Controller
    public class PrincipalController {

        @GetMapping("/principal")
        public String principal() {
            return "principal"; // Refere-se ao arquivo principal.html em src/main/resources/templates/
        }
    }

}
