package br.edu.infnet.petshopapi.controller;

import br.edu.infnet.petshopapi.model.domain.Cliente;
import br.edu.infnet.petshopapi.model.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Cliente> incluir(@Valid @RequestBody Cliente cliente) {

        Cliente clienteNovo = clienteService.incluir(cliente);

        return ResponseEntity.status(HttpStatus.CREATED).body(clienteNovo);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Cliente> alterar(@Valid @PathVariable Integer id, @Valid @RequestBody Cliente cliente) {

        Cliente clienteAlterado = clienteService.alterar(id, cliente);

        return ResponseEntity.ok(clienteAlterado);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {

        clienteService.excluir(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> obterLista() {

        List<Cliente> clientes = clienteService.obterLista();

        if (clientes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(clientes);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cliente> obterPorId(@PathVariable Integer id) {

        Cliente cliente = clienteService.obterPorId(id);

        return ResponseEntity.ok(cliente);
    }

    @PatchMapping(value = "/{id}/inativar")
    public ResponseEntity<Cliente> inativar(@PathVariable Integer id) {

        Cliente cliente = clienteService.inativar(id);

        return ResponseEntity.ok(cliente);
    }

    @GetMapping(value = "/cpf/{cpf}")
    public ResponseEntity<Cliente> obterPorCpf(@PathVariable String cpf) {

        Cliente cliente = clienteService.obterPorCpf(cpf);

        return ResponseEntity.ok(cliente);
    }

}