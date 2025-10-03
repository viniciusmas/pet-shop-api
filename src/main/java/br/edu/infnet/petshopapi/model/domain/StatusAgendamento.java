package br.edu.infnet.petshopapi.model.domain;

import lombok.Getter;

@Getter
public enum StatusAgendamento {

    AGENDADO("Agendado"),
    CONCLUIDO("Concluído"),
    CANCELADO("Cancelado"),
    DISPONIVEL("Disponível");

    private final String descricao;

    StatusAgendamento(String descricao) {
        this.descricao = descricao;
    }
}