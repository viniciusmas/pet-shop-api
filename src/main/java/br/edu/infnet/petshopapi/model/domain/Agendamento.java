package br.edu.infnet.petshopapi.model.domain;

import br.edu.infnet.petshopapi.model.dto.AgendamentoRequestDTO;
import jakarta.persistence.*;
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pet_id", nullable = false)
    private Pet pet;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "funcionario_id", nullable = false)
    private Funcionario funcionario;

    @Enumerated(EnumType.STRING)
    private TipoServico servico;

    @Enumerated(EnumType.STRING)
    private StatusAgendamento status = StatusAgendamento.DISPONIVEL;

    private LocalDateTime dataHora;

    private String googleEventId;

    private String linkGoogleCalendar;

    public Agendamento() {
        this.setCliente(null);
        this.setPet(null);
        this.setServico(null);
        this.setFuncionario(null);
        this.setDataHora(LocalDateTime.now());
        this.setStatus(StatusAgendamento.DISPONIVEL);
    }

    public Agendamento(AgendamentoRequestDTO agendamentoRequestDTO) {
        this.setCliente(agendamentoRequestDTO.getCliente());
        this.setPet(agendamentoRequestDTO.getPet());
        this.setServico(agendamentoRequestDTO.getServico());
        this.setFuncionario(agendamentoRequestDTO.getFuncionario());
        this.setDataHora(agendamentoRequestDTO.getDataHora());
        this.setStatus(agendamentoRequestDTO.getStatus());
    }
}