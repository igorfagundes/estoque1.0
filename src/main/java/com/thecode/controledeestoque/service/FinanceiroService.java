package com.thecode.controledeestoque.service;

import com.thecode.controledeestoque.model.Financeiro;
import com.thecode.controledeestoque.repository.FinanceiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FinanceiroService {

    @Autowired
    private FinanceiroRepository financeiroRepository;

    // Listar todos os boletos (administrador)
    public List<Financeiro> listarTodos() {
        return financeiroRepository.findAll();
    }

    // Encontrar boleto por ID (administrador)
    public Optional<Financeiro> encontrarPorId(Long id) {
        return financeiroRepository.findById(id);
    }

    // Pesquisar boletos atrasados (cliente)
    public List<Financeiro> pesquisarBoletosAtrasados() {
        return financeiroRepository.findByStatus("Atrasado");
    }

    // Pesquisar boletos por status (cliente)
    public List<Financeiro> pesquisarPorStatus(String status) {
        return financeiroRepository.findByStatus(status);
    }

    // Salvar (cadastrar ou atualizar) um boleto (administrador)
    public Financeiro salvar(Financeiro financeiro) {
        return financeiroRepository.save(financeiro);
    }

    // Excluir boleto por ID (administrador)
    public void excluir(Long id) {
        financeiroRepository.deleteById(id);
    }

    // Modificar status de um boleto existente (administrador)
    public Financeiro modificarStatus(Long id, String novoStatus) {
        Optional<Financeiro> financeiroExistente = financeiroRepository.findById(id);
        if (financeiroExistente.isPresent()) {
            Financeiro financeiro = financeiroExistente.get();
            financeiro.setStatus(novoStatus);
            return financeiroRepository.save(financeiro);
        } else {
            throw new RuntimeException("Boleto não encontrado com o ID: " + id);
        }
    }

    // Adicionar link para download (administrador)
    public Financeiro adicionarLinkBoleto(Long id, String linkBoleto) {
        Optional<Financeiro> financeiroExistente = financeiroRepository.findById(id);
        if (financeiroExistente.isPresent()) {
            Financeiro financeiro = financeiroExistente.get();
            financeiro.setLinkBoleto(linkBoleto);
            return financeiroRepository.save(financeiro);
        } else {
            throw new RuntimeException("Boleto não encontrado com o ID: " + id);
        }
    }
}
