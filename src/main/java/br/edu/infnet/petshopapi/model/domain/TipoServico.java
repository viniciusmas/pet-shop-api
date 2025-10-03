package br.edu.infnet.petshopapi.model.domain;

import lombok.Getter;

@Getter
public enum TipoServico {

    BANHO("Banho", 1),
    TOSA("Tosa", 1),
    BANHO_TOSA("Banho e Tosa", 2);

    private final String descricao;

    private final Integer tempo;

    TipoServico(String descricao, Integer tempo) {
        this.descricao = descricao;
        this.tempo = tempo;
    }
}