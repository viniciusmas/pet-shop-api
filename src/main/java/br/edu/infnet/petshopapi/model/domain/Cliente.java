package br.edu.infnet.petshopapi.model.domain;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Setter
@Getter
public class Cliente extends Pessoa {

    private LocalDateTime clienteDeste;

    private Boolean ativo;

    @Override
    public String toString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return String.format("%s Cliente Deste = %s, Status = %s", super.toString(), clienteDeste.format(dateTimeFormatter), ativo ? "Ativo" : "Inativo");
    }

    @Override
    public String obterTipo() {
        return "Cliente";
    }



}
