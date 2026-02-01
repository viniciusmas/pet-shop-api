package br.edu.infnet.petshopapi.model.dto;

import br.edu.infnet.petshopapi.model.domain.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AgendamentoResponseDTO {

    private Integer id;

    private String cliente;

    private String pet;

    private String servico;

    private String funcionario;

    private LocalDateTime dataHora;

    private String status;

    private String googleEventId;

    private String linkGoogleCalendar;

    public AgendamentoResponseDTO(Agendamento agendamento) {
        Pet pet = Pet.getPet(agendamento);
        this.setId(agendamento.getId());
        this.setCliente(agendamento.getCliente().getNome());
        this.setPet(pet.getNome());
        this.setServico(agendamento.getServico().getDescricao());
        this.setFuncionario(agendamento.getFuncionario().getNome());
        this.setDataHora(agendamento.getDataHora());
        this.setStatus(agendamento.getStatus().getDescricao());
        this.setGoogleEventId(agendamento.getGoogleEventId());
        this.setLinkGoogleCalendar(agendamento.getLinkGoogleCalendar());
    }
}