package br.edu.infnet.petshopapi.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AgendamentoResponseDTO {

    private Integer idCliente;

    private String nomeCliente;

    private String pet;

    private String servico;

    private Integer idFuncionario;

    private String nomeFuncionario;

    private LocalDateTime dataHora;

    private String status;

    private String googleEventId;

    private String linkGoogleCalendar;

}