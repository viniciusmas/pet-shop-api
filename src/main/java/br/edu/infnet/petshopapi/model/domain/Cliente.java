package br.edu.infnet.petshopapi.model.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Cliente extends Pessoa {

    private LocalDateTime clienteDeste;
    private Boolean statusCliente;

    @Override
    public String toString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return String.format("%s - %s - %s", super.toString(), clienteDeste.format(dateTimeFormatter), statusCliente ? "Ativo" : "Inativo");
    }

    @Override
    public String obterTipo() {
        return "Cliente";
    }

    public LocalDateTime getClienteDeste() {
        return clienteDeste;
    }

    public void setClienteDeste(LocalDateTime clienteDeste) {
        this.clienteDeste = clienteDeste;
    }

    public Boolean getStatusCliente() {
        return statusCliente;
    }

    public void setStatusCliente(Boolean statusCliente) {
        this.statusCliente = statusCliente;
    }
}
