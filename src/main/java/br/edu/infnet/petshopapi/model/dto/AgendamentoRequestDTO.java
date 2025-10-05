package br.edu.infnet.petshopapi.model.dto;

import br.edu.infnet.petshopapi.model.domain.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AgendamentoRequestDTO {

    private Cliente cliente;

    private Pet pet;

    private Funcionario funcionario;

    private TipoServico servico;

    private LocalDateTime dataHora;

    private StatusAgendamento status;

}