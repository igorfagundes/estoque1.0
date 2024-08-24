package com.thecode.controledeestoque.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;

@Entity
public class Financeiro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao; // Descrição do boleto ou transação
    private LocalDate dataLancamento; // Data de lançamento do boleto
    private LocalDate dataVencimento; // Data de vencimento do boleto
    private LocalDate dataPagamento; // Data em que o boleto foi pago (se aplicável)
    private Integer valor; // Valor do boleto (agora integral)
    private Integer valorPago; // Valor pago (agora integral)
    private String status; // Status do boleto (Pendente, Pago, Atrasado)
    private String tipo; // Tipo de transação (Boleto, Financiamento, etc.)
    private String linkBoleto; // Link para download do boleto

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public Integer getValorPago() {
        return valorPago;
    }

    public void setValorPago(Integer valorPago) {
        this.valorPago = valorPago;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getLinkBoleto() {
        return linkBoleto;
    }

    public void setLinkBoleto(String linkBoleto) {
        this.linkBoleto = linkBoleto;
    }

    @Override
    public String toString() {
        return "Financeiro{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", dataLancamento=" + dataLancamento +
                ", dataVencimento=" + dataVencimento +
                ", dataPagamento=" + dataPagamento +
                ", valor=" + valor +
                ", valorPago=" + valorPago +
                ", status='" + status + '\'' +
                ", tipo='" + tipo + '\'' +
                ", linkBoleto='" + linkBoleto + '\'' +
                '}';
    }
}
