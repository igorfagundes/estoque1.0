package com.thecode.controledeestoque.service;

import com.thecode.controledeestoque.model.Configuracao;
import com.thecode.controledeestoque.repository.ConfiguracaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConfiguracaoService {

    @Autowired
    private ConfiguracaoRepository configuracaoRepository;

    // Encontrar configuração por ID
    public Optional<Configuracao> encontrarPorId(Long id) {
        return configuracaoRepository.findById(id);
    }

    // Listar a configuração (somente uma configuração esperada)
    public Configuracao listarConfiguracao() {
        return configuracaoRepository.findAll().stream().findFirst()
                .orElseThrow(() -> new RuntimeException("Nenhuma configuração encontrada"));
    }

    // Atualizar configuração específica (somente a primeira atualização permitida)
    public Configuracao atualizarConfiguracao(Long id, Configuracao configuracaoAtualizada) {
        Optional<Configuracao> configuracaoExistente = configuracaoRepository.findById(id);
        if (configuracaoExistente.isPresent()) {
            Configuracao configuracao = configuracaoExistente.get();

            // Verifica se a configuração já foi atualizada
            if (configuracao.getNomeEmpresa() == null) { // Modificar essa condição conforme a lógica que define se a
                                                         // configuração já foi modificada
                configuracao.setNomeEmpresa(configuracaoAtualizada.getNomeEmpresa());
                configuracao.setLogoUrl(configuracaoAtualizada.getLogoUrl());
                configuracao.setEndereco(configuracaoAtualizada.getEndereco());
                configuracao.setContatoEmail(configuracaoAtualizada.getContatoEmail());
                configuracao.setContatoTelefone(configuracaoAtualizada.getContatoTelefone());
                configuracao.setFormatoData(configuracaoAtualizada.getFormatoData());
                configuracao.setMoeda(configuracaoAtualizada.getMoeda());
                configuracao.setIdioma(configuracaoAtualizada.getIdioma());
                configuracao.setBackupAutomatico(configuracaoAtualizada.getBackupAutomatico());
                configuracao.setCategoriaProduto(configuracaoAtualizada.getCategoriaProduto());
                configuracao.setMetodoPagamento(configuracaoAtualizada.getMetodoPagamento());
                configuracao.setParametrosFinanciamento(configuracaoAtualizada.getParametrosFinanciamento());

                return configuracaoRepository.save(configuracao);
            } else {
                throw new RuntimeException(
                        "Configuração já foi modificada. Contate o administrador para alterações adicionais.");
            }
        } else {
            throw new RuntimeException("Configuração não encontrada com o ID: " + id);
        }
    }
}
