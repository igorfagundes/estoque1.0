package com.thecode.controledeestoque.repository;

import com.thecode.controledeestoque.model.Caixa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaixaRepository extends JpaRepository<Caixa, Long> {
    // Métodos de consulta personalizados podem ser adicionados aqui, se necessário
}
