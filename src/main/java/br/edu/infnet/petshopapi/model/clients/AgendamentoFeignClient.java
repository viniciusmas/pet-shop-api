package br.edu.infnet.petshopapi.model.clients;

import br.edu.infnet.petshopapi.model.dto.AgendamentoRequestDTO;
import br.edu.infnet.petshopapi.model.dto.AgendamentoResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "agendamentoAPI", url = "${api-agendamento.url}")
public interface AgendamentoFeignClient {

    @PostMapping("/agendamentos")
    AgendamentoResponseDTO criarAgendamento(@RequestBody AgendamentoRequestDTO agendamentoRequestDTO);
}