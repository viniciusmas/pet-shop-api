package br.edu.infnet.petshopapi.model.service;

import br.edu.infnet.petshopapi.model.domain.Cliente;
import br.edu.infnet.petshopapi.model.domain.exceptions.ClienteInvalidoException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ClienteService implements CrudService<Cliente,Integer> {

    private final Map<Integer, Cliente> clienteMap = new ConcurrentHashMap<>();
    private final AtomicInteger clienteId = new AtomicInteger(1);

    @Override
    public Cliente salvar(Cliente cliente) {
        if (cliente.getNome() == null) {
            throw new ClienteInvalidoException("O nome do cliente é uma informação obrigatória!");
        }
        cliente.setId(clienteId.getAndIncrement());
        clienteMap.put(cliente.getId(), cliente);
        return cliente;
    }

    @Override
    public Cliente obterPorId(Integer id) {
        Cliente cliente = clienteMap.get(id);

        if(cliente == null) {
            throw new IllegalArgumentException("Impossível obter o cliente pelo ID " + id);
        }
        return cliente;
    }

    @Override
    public void excluir(Integer id) {
        clienteMap.remove(id);
    }

    @Override
    public List<Cliente> obterLista() {
        return new ArrayList<Cliente>(clienteMap.values());
    }
}
