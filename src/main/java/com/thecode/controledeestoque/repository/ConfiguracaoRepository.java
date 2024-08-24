package com.thecode.controledeestoque.repository;

import com.thecode.controledeestoque.model.Configuracao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfiguracaoRepository extends JpaRepository<Configuracao, Long> {
    // Métodos de consulta personalizados podem ser adicionados aqui, se necessário
}
