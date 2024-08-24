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
        return ResponseEntity.ok(configuracaoService.listarConfiguracao());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Configuracao> updateConfiguracoes(@PathVariable Long id,
            @RequestBody Configuracao configuracao) {
        try {
            Configuracao updatedConfig = configuracaoService.atualizarConfiguracao(id, configuracao);
            return ResponseEntity.ok(updatedConfig);
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(null); // Retorne um erro apropriado, se necessário
        }
    }
}
