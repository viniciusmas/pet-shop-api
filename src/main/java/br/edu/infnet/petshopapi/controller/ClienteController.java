package br.edu.infnet.petshopapi.controller;

import br.edu.infnet.petshopapi.model.domain.Cliente;
import br.edu.infnet.petshopapi.model.service.ClienteService;
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
    public Cliente incluir(@RequestBody Cliente cliente) {
        return clienteService.incluir(cliente);
    }

    @PutMapping(value = "/{id}")
    public Cliente alterar(@PathVariable Integer id, @RequestBody Cliente cliente) {
        return clienteService.alterar(id, cliente);
    }

    @DeleteMapping(value = "/{id}")
    public void excluir(@PathVariable Integer id) {
        clienteService.excluir(id);
    }

    @GetMapping
    public List<Cliente> obterLista() {
        return clienteService.obterLista();
    }

    @GetMapping(value = "/{id}")
    public Cliente obterPorId(@PathVariable Integer id) {
        return clienteService.obterPorId(id);
    }

    @PatchMapping(value = "/{id}/inativar")
    public Cliente inativar(@PathVariable Integer id) {
        return clienteService.inativar(id);
    }

}