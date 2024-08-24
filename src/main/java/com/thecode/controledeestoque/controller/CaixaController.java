package com.thecode.controledeestoque.controller;

import com.thecode.controledeestoque.model.Caixa;
import com.thecode.controledeestoque.service.CaixaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/caixa")
public class CaixaController {

    @Autowired
    private CaixaService caixaService;

    @GetMapping
    public List<Caixa> getAllCaixa() {
        return caixaService.listarTodos(); // Atualizado para listarTodos
    }

    @PostMapping
    public ResponseEntity<Caixa> createCaixa(@RequestBody Caixa caixa) {
        Caixa novoCaixa = caixaService.salvar(caixa); // Atualizado para salvar
        return ResponseEntity.ok(novoCaixa);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Caixa> getCaixaById(@PathVariable Long id) {
        Optional<Caixa> caixa = caixaService.encontrarPorId(id); // Atualizado para encontrarPorId
        return caixa.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Caixa> updateCaixa(@PathVariable Long id, @RequestBody Caixa caixa) {
        try {
            Caixa caixaAtualizado = caixaService.modificar(id, caixa); // Atualizado para modificar
            return ResponseEntity.ok(caixaAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCaixa(@PathVariable Long id) {
        try {
            caixaService.excluir(id); // Atualizado para excluir
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
