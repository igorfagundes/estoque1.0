package com.thecode.controledeestoque.controller;

import com.thecode.controledeestoque.model.Configuracao;
import com.thecode.controledeestoque.service.ConfiguracaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/configuracoes")
public class ConfiguracaoController {

    @Autowired
    private ConfiguracaoService configuracaoService;

    @GetMapping
    public ResponseEntity<Configuracao> getConfiguracoes() {
        return ResponseEntity.ok(configuracaoService.findCurrentSettings());
    }

    @PutMapping
    public ResponseEntity<Configuracao> updateConfiguracoes(@RequestBody Configuracao configuracao) {
        return ResponseEntity.ok(configuracaoService.update(configuracao));
    }
}
