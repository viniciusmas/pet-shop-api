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
import java.util.List;

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

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<AgendamentoResponseDTO>> listarAgendamentos() {
        List<AgendamentoResponseDTO> agendamentos = agendamentoService.listarAgendamentos();

        if (agendamentos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(agendamentos);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        agendamentoService.excluir(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}