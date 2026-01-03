package br.edu.infnet.petshopapi.model.service;

import br.edu.infnet.petshopapi.model.clients.GoogleCalendarClient;
import br.edu.infnet.petshopapi.model.domain.Agendamento;
import br.edu.infnet.petshopapi.model.domain.Cliente;
import br.edu.infnet.petshopapi.model.domain.Funcionario;
import br.edu.infnet.petshopapi.model.domain.Pet;
import br.edu.infnet.petshopapi.model.dto.AgendamentoResponseDTO;
import br.edu.infnet.petshopapi.model.dto.ClienteResponseDTO;
import br.edu.infnet.petshopapi.model.dto.FuncionarioResponseDTO;
import br.edu.infnet.petshopapi.model.repository.AgendamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AgendamentoService {

    private final GoogleCalendarClient googleCalendarClient;

    private final AgendamentoRepository agendamentoRepository;

    private final ClienteService clienteService;

    private final FuncionarioService funcionarioService;

    public AgendamentoResponseDTO criarAgendamento(Agendamento agendamento) {

        abterDados(agendamento);

        ZonedDateTime start = agendamento.getDataHora().atZone(java.time.ZoneId.of("America/Sao_Paulo"));
        ZonedDateTime end = start.plusHours(agendamento.getServico().getTempo());

        Pet pet = Pet.getPet(agendamento);

        Map<String, Object> evento = Map.of(
                "summary", "Serviço de " + agendamento.getServico().getDescricao() + " - Pet " + pet.getNome(),
                "description", "Sr.(a) " + agendamento.getCliente().getNome() + ", serviço de " + agendamento.getServico().getDescricao() + " agendado para o pet " + pet.getNome(),
                "start", Map.of("dateTime", start.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME), "timeZone", "America/Sao_Paulo"),
                "end", Map.of("dateTime", end.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME), "timeZone", "America/Sao_Paulo")
        );

        Map<String, Object> resposta = googleCalendarClient.criarEvento(evento);

        agendamento.setGoogleEventId((String) resposta.get("id"));
        agendamento.setLinkGoogleCalendar((String) resposta.get("htmlLink"));

        agendamentoRepository.save(agendamento);

        return new AgendamentoResponseDTO(agendamento);
    }

    private void abterDados(Agendamento agendamento) {
        ClienteResponseDTO clienteResponseDTO = clienteService.obterPorId(agendamento.getCliente().getId());
        FuncionarioResponseDTO funcionarioResponseDTO = funcionarioService.obterPorId(agendamento.getFuncionario().getId());
        agendamento.setCliente(new Cliente(clienteResponseDTO));
        agendamento.setFuncionario(new Funcionario(funcionarioResponseDTO));
    }
}