package br.edu.infnet.petshopapi.model.dto;

import br.edu.infnet.petshopapi.model.domain.Agendamento;
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

    public AgendamentoResponseDTO(Agendamento agendamento) {
        this.setIdCliente(agendamento.getIdCliente());
        this.setNomeCliente(agendamento.getNomeCliente());
        this.setPet(agendamento.getPet());
        this.setServico(agendamento.getServico().getDescricao());
        this.setIdFuncionario(agendamento.getIdFuncionario());
        this.setNomeFuncionario(agendamento.getNomeFuncionario());
        this.setDataHora(agendamento.getDataHora());
        this.setStatus(agendamento.getStatus().getDescricao());
        this.setGoogleEventId(agendamento.getGoogleEventId());
        this.setLinkGoogleCalendar(agendamento.getLinkGoogleCalendar());
    }

}