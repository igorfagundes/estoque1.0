package com.thecode.controledeestoque.service;

import com.thecode.controledeestoque.model.Caixa;
import com.thecode.controledeestoque.repository.CaixaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CaixaService {

    @Autowired
    private CaixaRepository caixaRepository;

    // Listar todas as entradas e saídas de caixa
    public List<Caixa> listarTodos() {
        return caixaRepository.findAll();
    }

    // Encontrar caixa por ID
    public Optional<Caixa> encontrarPorId(Long id) {
        return caixaRepository.findById(id);
    }

    // Salvar (cadastrar ou atualizar) uma transação de caixa
    public Caixa salvar(Caixa caixa) {
        return caixaRepository.save(caixa);
    }

    // Excluir caixa por ID
    public void excluir(Long id) {
        caixaRepository.deleteById(id);
    }

    // Cadastrar uma nova transação de caixa (mesmo método que salvar)
    public Caixa cadastrar(Caixa caixa) {
        return salvar(caixa);
    }

    // Modificar uma transação de caixa existente
    public Caixa modificar(Long id, Caixa caixaAtualizado) {
        // Verifica se a transação de caixa existe
        Optional<Caixa> caixaExistente = caixaRepository.findById(id);
        if (caixaExistente.isPresent()) {
            Caixa caixa = caixaExistente.get();
            // Atualiza os campos da transação de caixa existente
            caixa.setData(caixaAtualizado.getData());
            caixa.setTipo(caixaAtualizado.getTipo());
            caixa.setValor(caixaAtualizado.getValor());
            caixa.setDescricao(caixaAtualizado.getDescricao());
            caixa.setUsuario(caixaAtualizado.getUsuario());
            caixa.setFormaPagamento(caixaAtualizado.getFormaPagamento());
            caixa.setCategoria(caixaAtualizado.getCategoria());
            // Salva as alterações
            return caixaRepository.save(caixa);
        } else {
            // Lança exceção ou retorna um valor indicando que a transação não foi
            // encontrada
            throw new RuntimeException("Transação de caixa não encontrada com o ID: " + id);
        }
    }
}
