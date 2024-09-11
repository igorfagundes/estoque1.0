package com.thecode.controledeestoque.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private LocalDate nascimento;

    private String endereco;

    @Column(name = "data_abertura_cliente")
    private LocalDate dataAberturaCliente;

    private BigDecimal debito;

    private BigDecimal gastos;

    // Construtores
    public Cliente() {
    }

    public Cliente(String nome, LocalDate nascimento, String endereco, LocalDate dataAberturaCliente, BigDecimal debito,
            BigDecimal gastos) {
        this.nome = nome;
        this.nascimento = nascimento;
        this.endereco = endereco;
        this.dataAberturaCliente = dataAberturaCliente;
        this.debito = debito;
        this.gastos = gastos;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public LocalDate getDataAberturaCliente() {
        return dataAberturaCliente;
    }

    public void setDataAberturaCliente(LocalDate dataAberturaCliente) {
        this.dataAberturaCliente = dataAberturaCliente;
    }

    public BigDecimal getDebito() {
        return debito;
    }

    public void setDebito(BigDecimal debito) {
        this.debito = debito;
    }

    public BigDecimal getGastos() {
        return gastos;
    }

    public void setGastos(BigDecimal gastos) {
        this.gastos = gastos;
    }
}
