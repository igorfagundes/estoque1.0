package com.thecode.controledeestoque.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/principal")
public class PrincipalController {

    @GetMapping
    public String getPrincipalPage() {
        return "principal"; // Nome do arquivo HTML principal
    }

    @GetMapping("/produtos")
    public String getProdutosPage() {
        return "produtos";
    }

    @GetMapping("/fornecedores")
    public String getFornecedorPage() {
        return "fornecedores";
    }

    @GetMapping("/clientes")
    public String getClientePage() {
        return "clientes";
    }
}
