package com.thecode.controledeestoque.repository;

import com.thecode.controledeestoque.model.Financeiro;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FinanceiroRepository extends JpaRepository<Financeiro, Long> {

    // Encontrar boletos por status
    List<Financeiro> findByStatus(String status);
}
