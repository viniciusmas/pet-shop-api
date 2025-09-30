package br.edu.infnet.petshopapi.model.service;

import br.edu.infnet.petshopapi.model.clients.AgendamentoFeignClient;
import br.edu.infnet.petshopapi.model.dto.AgendamentoRequestDTO;
import br.edu.infnet.petshopapi.model.dto.AgendamentoResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class AgendamentoService {

    private final AgendamentoFeignClient agendamentoFeignClient;

    public AgendamentoService(AgendamentoFeignClient agendamentoFeignClient) {
        this.agendamentoFeignClient = agendamentoFeignClient;
    }

    public AgendamentoResponseDTO criarAgendamento(AgendamentoRequestDTO agendamentoRequestDTO) {
        return agendamentoFeignClient.criarAgendamento(agendamentoRequestDTO);
    }
}