package com.thecode.controledeestoque.controller;

import com.thecode.controledeestoque.model.Produto;
import com.thecode.controledeestoque.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/principal/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/listar")
    public String listarTodos(Model model) {
        // Lógica para listar todos os produtos
        return "produtos-list";
    }

    @GetMapping("/cadastrar")
    public String mostrarFormularioCadastro(Model model) {
        model.addAttribute("produto", new Produto()); // Adiciona um novo objeto Produto ao modelo
        return "cadastrar-produto"; // Página HTML para o formulário de cadastro
    }

    @PostMapping("/cadastrar")
    public String criarProduto(@ModelAttribute Produto produto, Model model) {
        produtoService.salvar(produto);
        model.addAttribute("mensagem", "Produto cadastrado com sucesso!");
        return "resultado-produto"; // Página HTML para o resultado do cadastro
    }

    @GetMapping("/apagar")
    public String mostrarFormularioApagar() {
        return "apagar-produto"; // Página HTML para apagar produtos
    }

    @PostMapping("/apagar")
    public String apagarProduto(@RequestParam Long id, Model model) {
        boolean sucesso = produtoService.excluir(id);
        if (sucesso) {
            model.addAttribute("mensagem", "Produto apagado com sucesso!");
        } else {
            model.addAttribute("mensagem", "Produto não encontrado.");
        }
        return "resultado-produto"; // Página HTML para o resultado da exclusão
    }

    @GetMapping("/modificar")
    public String mostrarFormularioModificar() {
        return "modificar-produto"; // Página HTML para modificar produtos
    }

    @PostMapping("/modificar")
    public String modificarProduto(@RequestParam Long id, @ModelAttribute Produto produto, Model model) {
        boolean sucesso = produtoService.modificar(id, produto);
        if (sucesso) {
            model.addAttribute("mensagem", "Produto modificado com sucesso!");
        } else {
            model.addAttribute("mensagem", "Produto não encontrado.");
        }
        return "resultado-produto"; // Página HTML para o resultado da modificação
    }

    @GetMapping("/procurar")
    public String mostrarFormularioProcurar() {
        return "procurar-produto"; // Página HTML para procurar produtos
    }

    @PostMapping("/procurar")
    public String procurarProduto(@RequestParam Long id, Model model) {
        Optional<Produto> produto = produtoService.encontrarPorId(id);
        if (produto.isPresent()) {
            model.addAttribute("produto", produto.get());
            return "resultado-produto"; // Página HTML para exibir o resultado da procura
        } else {
            model.addAttribute("mensagem", "Produto não encontrado.");
            return "resultado-produto"; // Página HTML para o resultado da procura
        }
    }
}
