package com.thecode.controledeestoque.controller;

import com.thecode.controledeestoque.model.Fornecedor;
import com.thecode.controledeestoque.service.FornecedorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List; // Corrigido import
import java.util.Optional;

@Controller
@RequestMapping("/principal/fornecedores")
public class FornecedorController {

    @Autowired
    private FornecedorService fornecedorService;

    @GetMapping("/listar")
    public String listarTodos(Model model) {
        model.addAttribute("fornecedores", fornecedorService.listarTodos());
        return "listar-fornecedores";
    }

    @GetMapping("/cadastrar")
    public String mostrarFormularioCadastro(Model model) {
        model.addAttribute("fornecedor", new Fornecedor());
        return "cadastrar-fornecedor";
    }

    @PostMapping("/cadastrar")
    public String criarFornecedor(@ModelAttribute Fornecedor fornecedor, Model model) {
        fornecedorService.salvar(fornecedor);
        model.addAttribute("mensagem", "Fornecedor cadastrado com sucesso!");
        return "redirect:/principal/fornecedores/listar";
    }

    @GetMapping("/modificar")
    public String mostrarFormularioModificar(@PathVariable Long id, Model model) {
        Optional<Fornecedor> fornecedorOpt = fornecedorService.buscarPorId(id);
        if (fornecedorOpt.isPresent()) {
            model.addAttribute("fornecedor", fornecedorOpt.get());
            return "modificar-fornecedor";
        } else {
            model.addAttribute("mensagem", "Fornecedor não encontrado.");
            return "resultado-fornecedor";
        }
    }

    @PostMapping("/modificar")
    public String modificarFornecedor(@RequestParam Long id,
            @RequestParam String empresa,
            @RequestParam String secao,
            Model model) {
        Optional<Fornecedor> fornecedorOpt = fornecedorService.buscarPorId(id);
        if (fornecedorOpt.isPresent()) {
            Fornecedor fornecedor = fornecedorOpt.get();
            fornecedor.setEmpresa(empresa);
            fornecedor.setSecao(secao);
            fornecedorService.salvar(fornecedor);
            model.addAttribute("mensagem", "Fornecedor modificado com sucesso!");
            return "redirect:/principal/fornecedores/listar";
        } else {
            model.addAttribute("mensagem", "Fornecedor não encontrado.");
            return "resultado-fornecedor";
        }
    }

    @GetMapping("/excluir")
    public String mostrarFormularioExcluir(@RequestParam(required = false) Long id, Model model) {
        if (id != null) {
            Optional<Fornecedor> fornecedorOpt = fornecedorService.buscarPorId(id);
            if (fornecedorOpt.isPresent()) {
                model.addAttribute("fornecedor", fornecedorOpt.get());
                return "confirmar-exclusao";
            } else {
                model.addAttribute("mensagem", "Fornecedor não encontrado com o ID fornecido.");
            }
        } else {
            model.addAttribute("mensagem", "Por favor, forneça um ID para procurar.");
        }
        return "excluir-fornecedor";
    }

    @PostMapping("/excluir")
    public String excluirFornecedor(@RequestParam Long id, Model model) {
        try {
            fornecedorService.excluir(id);
            model.addAttribute("mensagem", "Fornecedor excluído com sucesso!");
        } catch (Exception e) {
            model.addAttribute("mensagem", "Erro ao excluir fornecedor.");
        }
        return "redirect:/principal/fornecedores/listar";
    }

    @GetMapping("/procurar")
    public String mostrarFormularioProcurar(Model model) {
        model.addAttribute("fornecedor", new Fornecedor());
        return "procurar-fornecedor";
    }

    @PostMapping("/procurar")
    public String procurarFornecedor(@RequestParam(required = false) Long id,
            @RequestParam(required = false) String nome,
            Model model) {
        if (id != null) {
            Optional<Fornecedor> fornecedorOpt = fornecedorService.buscarPorId(id);
            if (fornecedorOpt.isPresent()) {
                model.addAttribute("fornecedor", fornecedorOpt.get());
                model.addAttribute("mensagem", "Fornecedor encontrado!");
            } else {
                model.addAttribute("mensagem", "Fornecedor não encontrado com o ID fornecido.");
            }
        } else if (nome != null && !nome.isEmpty()) {
            List<Fornecedor> fornecedores = fornecedorService.procurarFornecedoresPorNome(nome);
            if (!fornecedores.isEmpty()) {
                model.addAttribute("fornecedores", fornecedores);
                model.addAttribute("mensagem", "Fornecedores encontrados!");
            } else {
                model.addAttribute("mensagem", "Nenhum fornecedor encontrado com o nome fornecido.");
            }
        } else {
            model.addAttribute("mensagem", "Por favor, forneça um ID ou nome para procurar.");
        }
        return "resultado-fornecedor";
    }
}
