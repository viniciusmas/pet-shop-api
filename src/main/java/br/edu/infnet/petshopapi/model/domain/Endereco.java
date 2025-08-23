package br.edu.infnet.petshopapi.model.domain;

public class Endereco {

    private String logradouro;
    private Integer numero;
    private String bairro;
    private String cidade;
    private String estado;
    private Integer cep;

    @Override
    public String toString() {
        return String.format("%s - %d - %s - %s - %s - %d", logradouro, numero, bairro, cidade, estado, cep);
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getCep() {
        return cep;
    }

    public void setCep(Integer cep) {
        this.cep = cep;
    }
}
