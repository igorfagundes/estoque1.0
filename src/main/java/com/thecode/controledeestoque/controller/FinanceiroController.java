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
        return financeiroService.listarTodos(); // Alterado para corresponder ao método no serviço
    }

    @PostMapping
    public Financeiro createFinanceiro(@RequestBody Financeiro financeiro) {
        return financeiroService.salvar(financeiro); // Alterado para corresponder ao método no serviço
    }

    @GetMapping("/{id}")
    public ResponseEntity<Financeiro> getFinanceiroById(@PathVariable Long id) {
        return financeiroService.encontrarPorId(id) // Alterado para corresponder ao método no serviço
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Financeiro> updateFinanceiro(@PathVariable Long id, @RequestBody Financeiro financeiro) {
        // Não há método de atualização diretamente no serviço, então seria necessário
        // adicionar um
        // Você pode usar o método de modificarStatus para atualizar o status ou criar
        // um método específico para atualização completa
        return ResponseEntity.ok(financeiroService.salvar(financeiro)); // Aqui assumimos que `salvar` pode ser usado
                                                                        // para atualizar
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFinanceiro(@PathVariable Long id) {
        financeiroService.excluir(id); // Alterado para corresponder ao método no serviço
        return ResponseEntity.noContent().build();
    }
}
