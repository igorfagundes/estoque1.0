package com.thecode.controledeestoque.controller;

import com.thecode.controledeestoque.model.Cliente;
import com.thecode.controledeestoque.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/principal/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/listar")
    public String listarClientes(Model model, @RequestParam(value = "error", required = false) String error) {
        List<Cliente> clientes = clienteService.listarTodos();
        model.addAttribute("clientes", clientes);
        if (error != null) {
            model.addAttribute("message", new Message(error, "error"));
        }
        model.addAttribute("viewContent", "listar-clientes"); // Nome do template específico
        return "principal"; // Nome do template principal
    }

    @GetMapping("/novo")
    public String novoClienteForm(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "novo-cliente";
    }

    @PostMapping("/salvar")
    public String salvarCliente(@ModelAttribute Cliente cliente, RedirectAttributes redirectAttributes) {
        try {
            clienteService.salvar(cliente);
            redirectAttributes.addFlashAttribute("message", new Message("Cliente cadastrado com sucesso!", "success"));
            return "redirect:/principal/clientes/novo"; // Redireciona para o formulário com a mensagem
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message",
                    new Message("Erro ao salvar cliente: " + e.getMessage(), "error"));
            return "redirect:/principal/clientes/novo"; // Redireciona para o formulário com a mensagem
        }
    }

    // Defina a classe Message para transportar mensagens de feedback
    public static class Message {
        private final String text;
        private final String type; // 'success' ou 'error'

        public Message(String text, String type) {
            this.text = text;
            this.type = type;
        }

        public String getText() {
            return text;
        }

        public String getType() {
            return type;
        }
    }

    @GetMapping("/editar/{id}")
    public String editarClienteForm(@PathVariable Long id, Model model) {
        Optional<Cliente> cliente = clienteService.buscarPorId(id);
        if (cliente.isPresent()) {
            model.addAttribute("cliente", cliente.get());
            return "novo-cliente";
        } else {
            return "redirect:/principal/clientes/listar?error=Cliente%20não%20encontrado!";
        }
    }

    @GetMapping("/excluir/{id}")
    public String excluirCliente(@PathVariable Long id, Model model) {
        Optional<Cliente> cliente = clienteService.buscarPorId(id);
        if (cliente.isPresent()) {
            clienteService.excluir(id);
            return "redirect:/principal/clientes/listar";
        } else {
            return "redirect:/principal/clientes/listar?error=Cliente%20não%20encontrado!";
        }
    }

    @GetMapping("/buscar")
    public String buscarClientesPorNome(@RequestParam String nome, Model model) {
        List<Cliente> clientes = clienteService.buscarPorNome(nome);
        if (clientes.isEmpty()) {
            model.addAttribute("error", "Nenhum cliente encontrado com o nome informado.");
        }
        model.addAttribute("clientes", clientes);
        model.addAttribute("viewContent", "listar-clientes :: content"); // Nome do fragmento
        return "principal"; // Nome do template principal
    }
}
