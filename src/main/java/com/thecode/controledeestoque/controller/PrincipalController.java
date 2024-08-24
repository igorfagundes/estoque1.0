package com.thecode.controledeestoque.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PrincipalController {

    @GetMapping("/principal")
    public String getPrincipalPage() {
        return "principal"; // Nome do arquivo HTML no diretório templates
    }
}
