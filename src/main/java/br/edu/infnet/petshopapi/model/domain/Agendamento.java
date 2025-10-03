package br.edu.infnet.petshopapi.model.domain;

import br.edu.infnet.petshopapi.model.dto.AgendamentoRequestDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer idCliente;

    private String nomeCliente;

    private String pet;

    private TipoServico servico;

    private Integer idFuncionario;

    private String nomeFuncionario;

    private LocalDateTime dataHora;

    private StatusAgendamento status = StatusAgendamento.DISPONIVEL;

    private String googleEventId;

    private String linkGoogleCalendar;

    public Agendamento() {
        this.setIdCliente(null);
        this.setNomeCliente(null);
        this.setPet(null);
        this.setServico(null);
        this.setIdFuncionario(null);
        this.setNomeFuncionario(null);
        this.setDataHora(LocalDateTime.now());
        this.setStatus(StatusAgendamento.DISPONIVEL);
    }

    public Agendamento(AgendamentoRequestDTO agendamentoRequestDTO) {
        this.setIdCliente(agendamentoRequestDTO.getIdCliente());
        this.setNomeCliente(agendamentoRequestDTO.getNomeCliente());
        this.setPet(agendamentoRequestDTO.getPet());
        this.setServico(TipoServico.valueOf(agendamentoRequestDTO.getServico()));
        this.setIdFuncionario(agendamentoRequestDTO.getIdFuncionario());
        this.setNomeFuncionario(agendamentoRequestDTO.getNomeFuncionario());
        this.setDataHora(agendamentoRequestDTO.getDataHora());
        this.setStatus(StatusAgendamento.valueOf(agendamentoRequestDTO.getStatus()));
    }
}