package com.thecode.controledeestoque.repository;

import com.thecode.controledeestoque.model.Financeiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinanceiroRepository extends JpaRepository<Financeiro, Long> {
    // Métodos de consulta personalizados podem ser adicionados aqui, se necessário
}
