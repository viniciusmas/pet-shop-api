package br.edu.infnet.petshopapi.controller;

import br.edu.infnet.petshopapi.model.domain.Agendamento;
import br.edu.infnet.petshopapi.model.dto.AgendamentoRequestDTO;
import br.edu.infnet.petshopapi.model.dto.AgendamentoResponseDTO;
import br.edu.infnet.petshopapi.model.service.AgendamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/agendamentos")
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AgendamentoResponseDTO> criarAgendamento(@RequestBody AgendamentoRequestDTO agendamentoRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(agendamentoService.criarAgendamento(new Agendamento(agendamentoRequestDTO)));
    }
}