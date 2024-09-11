package com.thecode.controledeestoque.service;

import com.thecode.controledeestoque.model.Cliente;
import com.thecode.controledeestoque.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    // Retorna a lista completa de clientes
    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    // Busca um cliente pelo ID
    public Optional<Cliente> buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }

    // Busca clientes por nome (busca parcial)
    public List<Cliente> buscarPorNome(String nome) {
        return clienteRepository.findByNomeContaining(nome);
    }

    // Salva ou atualiza um cliente
    public Cliente salvar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    // Exclui um cliente pelo ID
    public boolean excluir(Long id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
