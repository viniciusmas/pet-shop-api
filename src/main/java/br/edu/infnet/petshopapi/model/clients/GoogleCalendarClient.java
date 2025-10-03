package br.edu.infnet.petshopapi.model.clients;

import br.edu.infnet.petshopapi.config.GoogleFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@FeignClient(name = "googleCalendarClient", url = "https://www.googleapis.com/calendar/v3/calendars/primary/events", configuration = GoogleFeignConfig.class)
public interface GoogleCalendarClient {

    @PostMapping
    Map<String, Object> criarEvento(@RequestBody Map<String, Object> evento);

    @PatchMapping("/{eventId}")
    Map<String, Object> atualizarEvento(@PathVariable("eventId") String eventId, @RequestBody Map<String, Object> evento);

    @DeleteMapping("/{eventId}")
    void deletarEvento(@PathVariable("eventId") String eventId);

    @GetMapping("/{eventId}")
    Map<String, Object> buscarEvento(@PathVariable("eventId") String eventId);
}