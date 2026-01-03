package br.edu.infnet.petshopapi.controller;

import br.edu.infnet.petshopapi.model.domain.Cliente;
import br.edu.infnet.petshopapi.model.dto.ClienteRequestDTO;
import br.edu.infnet.petshopapi.model.dto.ClienteResponseDTO;
import br.edu.infnet.petshopapi.model.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ClienteResponseDTO> incluir(@Valid @RequestBody ClienteRequestDTO clienteRequestDTO) {
        ClienteResponseDTO clienteNovo = clienteService.incluir(new Cliente(clienteRequestDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteNovo);
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ClienteResponseDTO> alterar(@Valid @PathVariable Integer id, @Valid @RequestBody ClienteRequestDTO clienteRequestDTO) {
        ClienteResponseDTO clienteAlterado = clienteService.alterar(id, new Cliente(clienteRequestDTO));
        return ResponseEntity.status(HttpStatus.OK).body(clienteAlterado);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        clienteService.excluir(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<ClienteResponseDTO>> obterLista() {
        List<ClienteResponseDTO> clientes = clienteService.obterLista();
        if (clientes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(clientes);
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<ClienteResponseDTO> obterPorId(@PathVariable Integer id) {
        ClienteResponseDTO cliente = clienteService.obterPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(cliente);
    }

    @PatchMapping(value = "/{id}/inativar")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ClienteResponseDTO> inativar(@PathVariable Integer id) {
        ClienteResponseDTO cliente = clienteService.inativar(id);
        return ResponseEntity.status(HttpStatus.OK).body(cliente);
    }

    @GetMapping(value = "/cpf/{cpf}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<ClienteResponseDTO> obterPorCpf(@PathVariable String cpf) {
        ClienteResponseDTO cliente = clienteService.obterPorCpf(cpf);
        return ResponseEntity.status(HttpStatus.OK).body(cliente);
    }

}