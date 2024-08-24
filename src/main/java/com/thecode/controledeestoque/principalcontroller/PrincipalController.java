package com.thecode.controledeestoque.principalcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/principal")
public class PrincipalController {

    @GetMapping
    public String principal() {
        return "principal"; // Retorna a página principal
    }

    @GetMapping("/produtos")
    public String produtos() {
        return "produtos"; // Retorna a página produtos
    }
}
