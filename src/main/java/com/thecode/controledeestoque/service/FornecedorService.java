package com.thecode.controledeestoque.service;

import com.thecode.controledeestoque.model.Fornecedor;
import com.thecode.controledeestoque.repository.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    // Retorna a lista completa de fornecedores
    public List<Fornecedor> listarTodos() {
        return fornecedorRepository.findAll();
    }

    // Busca um fornecedor pelo ID
    public Optional<Fornecedor> buscarPorId(Long id) {
        return fornecedorRepository.findById(id);
    }

    public List<Fornecedor> procurarFornecedoresPorNome(String nome) {
        return fornecedorRepository.findByNomeContaining(nome);
    }

    // Salva ou atualiza um fornecedor, verificando os campos empresa e seção
    public Fornecedor salvar(Fornecedor fornecedor) {
        validarCampos(fornecedor); // Validação básica dos campos empresa e seção
        return fornecedorRepository.save(fornecedor);
    }

    // Exclui um fornecedor pelo ID
    public boolean excluir(Long id) {
        if (fornecedorRepository.existsById(id)) {
            fornecedorRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Busca um fornecedor pelo nome
    public Fornecedor buscarPorNome(String nome) {
        return fornecedorRepository.findByNome(nome);
    }

    // Validação dos campos antes de salvar ou atualizar o fornecedor
    private void validarCampos(Fornecedor fornecedor) {
        if (fornecedor.getEmpresa() == null || fornecedor.getEmpresa().isEmpty()) {
            throw new IllegalArgumentException("O campo empresa é obrigatório.");
        }
        if (fornecedor.getSecao() == null || fornecedor.getSecao().isEmpty()) {
            throw new IllegalArgumentException("O campo seção é obrigatório.");
        }
    }
}
