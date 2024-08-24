package com.thecode.controledeestoque.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Configuracao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeEmpresa;
    private String logoUrl;
    private String endereco;
    private String contatoEmail;
    private String contatoTelefone;
    private String formatoData;
    private String moeda;
    private String idioma;
    private Boolean backupAutomatico;
    private String categoriaProduto;
    private String metodoPagamento;
    private String parametrosFinanciamento;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getContatoEmail() {
        return contatoEmail;
    }

    public void setContatoEmail(String contatoEmail) {
        this.contatoEmail = contatoEmail;
    }

    public String getContatoTelefone() {
        return contatoTelefone;
    }

    public void setContatoTelefone(String contatoTelefone) {
        this.contatoTelefone = contatoTelefone;
    }

    public String getFormatoData() {
        return formatoData;
    }

    public void setFormatoData(String formatoData) {
        this.formatoData = formatoData;
    }

    public String getMoeda() {
        return moeda;
    }

    public void setMoeda(String moeda) {
        this.moeda = moeda;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Boolean getBackupAutomatico() {
        return backupAutomatico;
    }

    public void setBackupAutomatico(Boolean backupAutomatico) {
        this.backupAutomatico = backupAutomatico;
    }

    public String getCategoriaProduto() {
        return categoriaProduto;
    }

    public void setCategoriaProduto(String categoriaProduto) {
        this.categoriaProduto = categoriaProduto;
    }

    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public String getParametrosFinanciamento() {
        return parametrosFinanciamento;
    }

    public void setParametrosFinanciamento(String parametrosFinanciamento) {
        this.parametrosFinanciamento = parametrosFinanciamento;
    }

    @Override
    public String toString() {
        return "Configuracao{" +
                "id=" + id +
                ", nomeEmpresa='" + nomeEmpresa + '\'' +
                ", logoUrl='" + logoUrl + '\'' +
                ", endereco='" + endereco + '\'' +
                ", contatoEmail='" + contatoEmail + '\'' +
                ", contatoTelefone='" + contatoTelefone + '\'' +
                ", formatoData='" + formatoData + '\'' +
                ", moeda='" + moeda + '\'' +
                ", idioma='" + idioma + '\'' +
                ", backupAutomatico=" + backupAutomatico +
                ", categoriaProduto='" + categoriaProduto + '\'' +
                ", metodoPagamento='" + metodoPagamento + '\'' +
                ", parametrosFinanciamento='" + parametrosFinanciamento + '\'' +
                '}';
    }
}
