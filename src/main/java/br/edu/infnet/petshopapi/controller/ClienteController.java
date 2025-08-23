package br.edu.infnet.petshopapi.controller;

import br.edu.infnet.petshopapi.model.domain.Cliente;
import br.edu.infnet.petshopapi.model.service.ClienteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<Cliente> obterLista() {
        return clienteService.obterLista();
    }

    @GetMapping(value = "/{id}")
    public Cliente obterPorId(@PathVariable Integer id) {
        return clienteService.obterPorId(id);
    }
}