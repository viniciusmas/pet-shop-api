package br.edu.infnet.petshopapi.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
public class Cliente extends Pessoa {

    private LocalDateTime clienteDeste;

    private Boolean status;

    @JsonIgnore
    @OneToMany(mappedBy = "tutor", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Pet> pets = new ArrayList<>();

    @Override
    public String toString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return String.format("Cliente { %s Cliente Deste = %s, Status = %s }", super.toString(), clienteDeste.format(dateTimeFormatter), status ? "Ativo" : "Inativo");
    }

    @Override
    public String obterTipo() {
        return "Cliente";
    }



}
