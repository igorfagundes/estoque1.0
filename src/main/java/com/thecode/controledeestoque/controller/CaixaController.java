package com.thecode.controledeestoque.controller;

import com.thecode.controledeestoque.model.Caixa;
import com.thecode.controledeestoque.service.CaixaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/caixa")
public class CaixaController {

    @Autowired
    private CaixaService caixaService;

    @GetMapping
    public List<Caixa> getAllCaixa() {
        return caixaService.findAll();
    }

    @PostMapping
    public Caixa createCaixa(@RequestBody Caixa caixa) {
        return caixaService.save(caixa);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Caixa> getCaixaById(@PathVariable Long id) {
        return caixaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Caixa> updateCaixa(@PathVariable Long id, @RequestBody Caixa caixa) {
        return caixaService.update(id, caixa)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCaixa(@PathVariable Long id) {
        caixaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
