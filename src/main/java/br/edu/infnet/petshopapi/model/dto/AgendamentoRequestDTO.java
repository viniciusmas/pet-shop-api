package br.edu.infnet.petshopapi.model.dto;

import br.edu.infnet.petshopapi.model.domain.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AgendamentoRequestDTO {

    @NotNull(message = "O cliente é obrigatório.")
    private Cliente cliente;

    @NotNull(message = "O pet é obrigatório.")
    private Pet pet;

    @NotNull(message = "O funcionário é obrigatório.")
    private Funcionario funcionario;

    @NotNull(message = "O tipo do serviço é obrigatório.")
    private TipoServico servico;

    private LocalDateTime dataHora;

    private StatusAgendamento status;

}