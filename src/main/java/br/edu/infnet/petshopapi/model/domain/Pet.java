package br.edu.infnet.petshopapi.model.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Pet {

    private Integer id;

    private String name;

    private String especie;

    private String raca;

    private Integer idade;

    private Double peso;

    private Cliente dono;

}
