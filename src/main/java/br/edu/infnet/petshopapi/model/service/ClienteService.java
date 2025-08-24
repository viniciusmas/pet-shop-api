package br.edu.infnet.petshopapi.model.service;

import br.edu.infnet.petshopapi.model.domain.Cliente;
import br.edu.infnet.petshopapi.model.domain.exceptions.ClienteInvalidoException;
import br.edu.infnet.petshopapi.model.domain.exceptions.ClienteNaoEncontradoException;
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

    private void validar(Cliente cliente) {

        if (cliente == null){
            throw new IllegalArgumentException("O Cliente não pode ser nulo!");
        }

        if (cliente.getNome() == null || cliente.getNome().trim().isEmpty()) {
            throw new ClienteInvalidoException("O nome do cliente é uma informação obrigatória!");
        }
    }

    @Override
    public Cliente incluir(Cliente cliente) {

        validar(cliente);

        if (cliente.getId() != null && cliente.getId() != 0){
            throw new IllegalArgumentException("Um novo cliente não pode ter um ID na inclusão!");
        }

        cliente.setId(clienteId.getAndIncrement());

        clienteMap.put(cliente.getId(), cliente);

        return cliente;
    }

    @Override
    public Cliente alterar(Integer id, Cliente cliente) {

        if (id == null || id == 0) {
            throw new IllegalArgumentException("O ID para alteração não pode ser nulo/zero!");
        }

        validar(cliente);

        obterPorId(id);

        cliente.setId(id);

        clienteMap.put(cliente.getId(), cliente);

        return cliente;
    }

    @Override
    public void excluir(Integer id) {

        if (id == null || id == 0) {
            throw new IllegalArgumentException("O ID para exclusão não pode ser nulo/zero!");
        }

        if (!clienteMap.containsKey(id)) {
            throw new ClienteNaoEncontradoException("O cliente com ID " + id + " não foi encontrado!");
        }

        clienteMap.remove(id);
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
    public List<Cliente> obterLista() {

        return new ArrayList<Cliente>(clienteMap.values());
    }

    public Cliente inativar(Integer id) {

        if (id == null || id == 0) {
            throw new IllegalArgumentException("O ID para inativação não pode ser nulo/zero!");
        }

        Cliente cliente = obterPorId(id);

        if (!cliente.getAtivo()) {
            System.out.println("Cliente " + cliente.getNome() + " já está inativo!");
            return cliente;
        }

        cliente.setAtivo(false);

        clienteMap.put(cliente.getId(), cliente);

        return cliente;
    }
}
