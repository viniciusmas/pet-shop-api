package br.edu.infnet.petshopapi.model.service;

import br.edu.infnet.petshopapi.model.clients.GoogleCalendarClient;
import br.edu.infnet.petshopapi.model.domain.Agendamento;
import br.edu.infnet.petshopapi.model.dto.AgendamentoResponseDTO;
import br.edu.infnet.petshopapi.model.repository.AgendamentoRepository;
import org.springframework.stereotype.Service;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Service
public class AgendamentoService {

    private final GoogleCalendarClient googleCalendarClient;
    private final AgendamentoRepository agendamentoRepository;

    public AgendamentoService(GoogleCalendarClient googleCalendarClient, AgendamentoRepository agendamentoRepository) {
        this.googleCalendarClient = googleCalendarClient;
        this.agendamentoRepository = agendamentoRepository;
    }

    public AgendamentoResponseDTO criarAgendamento(Agendamento agendamento) {

        ZonedDateTime start = agendamento.getDataHora().atZone(java.time.ZoneId.of("America/Sao_Paulo"));
        ZonedDateTime end = start.plusHours(agendamento.getServico().getTempo());

        Map<String, Object> evento = Map.of(
                "summary", agendamento.getServico().getDescricao() + " - " + agendamento.getPet(),
                "description", "Cliente: " + agendamento.getNomeCliente(),
                "start", Map.of("dateTime", start.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME), "timeZone", "America/Sao_Paulo"),
                "end", Map.of("dateTime", end.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME), "timeZone", "America/Sao_Paulo")
        );

        Map<String, Object> resposta = googleCalendarClient.criarEvento(evento);

        agendamento.setGoogleEventId((String) resposta.get("id"));
        agendamento.setLinkGoogleCalendar((String) resposta.get("htmlLink"));

        agendamentoRepository.save(agendamento);

        return new AgendamentoResponseDTO(agendamento);
    }
}