package com.thecode.controledeestoque.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Caixa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime data;
    private String tipo; // Abertura ou Fechamento
    private Double valor;
    private String descricao;
    private String usuario; // Identificação do usuário
    private String formaPagamento; // Dinheiro, Cartão, etc.
    private String categoria; // Abertura, Fechamento, Venda, Compra, Despesa, etc.

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    // Métodos para abrir e fechar o caixa

    public void abrirCaixa(String usuario, Double valorInicial) {
        this.data = LocalDateTime.now();
        this.tipo = "Abertura";
        this.valor = valorInicial; // Valor inicial para abertura do caixa
        this.descricao = "Abertura do caixa pelo usuário: " + usuario;
        this.usuario = usuario;
        this.formaPagamento = "Dinheiro"; // Pode ser ajustado conforme necessário
        this.categoria = "Abertura";
    }

    public void fecharCaixa(Double valorFinal) {
        this.data = LocalDateTime.now();
        this.tipo = "Fechamento";
        this.valor = valorFinal; // Valor final ao fechar o caixa
        this.descricao = "Fechamento do caixa";
        this.categoria = "Fechamento";
    }

    public void encerrarDia(Double valorFinal) {
        // Pode ser usado para gerar relatórios ou operações finais
        this.fecharCaixa(valorFinal);
        // Adicione lógica adicional aqui, se necessário
    }

    @Override
    public String toString() {
        return "Caixa{" +
                "id=" + id +
                ", data=" + data +
                ", tipo='" + tipo + '\'' +
                ", valor=" + valor +
                ", descricao='" + descricao + '\'' +
                ", usuario='" + usuario + '\'' +
                ", formaPagamento='" + formaPagamento + '\'' +
                ", categoria='" + categoria + '\'' +
                '}';
    }
}
