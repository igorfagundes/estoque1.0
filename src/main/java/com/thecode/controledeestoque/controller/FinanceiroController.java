package com.thecode.controledeestoque.controller;

import com.thecode.controledeestoque.model.Financeiro;
import com.thecode.controledeestoque.service.FinanceiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/financeiro")
public class FinanceiroController {

    @Autowired
    private FinanceiroService financeiroService;

    @GetMapping
    public List<Financeiro> getAllFinanceiro() {
        return financeiroService.findAll();
    }

    @PostMapping
    public Financeiro createFinanceiro(@RequestBody Financeiro financeiro) {
        return financeiroService.save(financeiro);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Financeiro> getFinanceiroById(@PathVariable Long id) {
        return financeiroService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Financeiro> updateFinanceiro(@PathVariable Long id, @RequestBody Financeiro financeiro) {
        return financeiroService.update(id, financeiro)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFinanceiro(@PathVariable Long id) {
        financeiroService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
