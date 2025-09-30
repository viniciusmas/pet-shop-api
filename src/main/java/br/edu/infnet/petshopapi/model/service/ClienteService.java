package br.edu.infnet.petshopapi.model.service;

import br.edu.infnet.petshopapi.model.domain.Cliente;
import br.edu.infnet.petshopapi.model.domain.Endereco;
import br.edu.infnet.petshopapi.model.domain.exceptions.ClienteInvalidoException;
import br.edu.infnet.petshopapi.model.domain.exceptions.ClienteNaoEncontradoException;
import br.edu.infnet.petshopapi.model.dto.EnderecoDTO;
import br.edu.infnet.petshopapi.model.repository.ClienteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ClienteService implements CrudService<Cliente,Integer> {

    private final ClienteRepository clienteRepository;
    private final ViaCepService viaCepService;

    public ClienteService(ClienteRepository clienteRepository, ViaCepService viaCepService) {
        this.clienteRepository = clienteRepository;
        this.viaCepService = viaCepService;
    }

    private void validar(Cliente cliente) {

        if (cliente == null){
            throw new IllegalArgumentException("O Cliente não pode ser nulo!");
        }

        if (cliente.getNome() == null || cliente.getNome().trim().isEmpty()) {
            throw new ClienteInvalidoException("O nome do cliente é uma informação obrigatória!");
        }

        clienteRepository.findByCpf(cliente.getCpf()).ifPresent(c -> { throw new ResponseStatusException(HttpStatus.CONFLICT, "CPF já cadastrado."); });
    }

    @Override
    @Transactional
    public Cliente incluir(Cliente cliente) {

        validar(cliente);

        if (cliente.getId() != null && cliente.getId() != 0) {
            throw new IllegalArgumentException("Um novo cliente não pode ter um ID na inclusão!");
        }

        EnderecoDTO enderecoDTO = viaCepService.getEndereco(cliente.getCepConsulta().replace("-", ""));

        if (enderecoDTO == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CEP inválido ou não encontrado.");
        }

        cliente.setEndereco(new Endereco(enderecoDTO));

        return clienteRepository.save(cliente);
    }

    @Override
    @Transactional
    public Cliente alterar(Integer id, Cliente cliente) {

        if (id == null || id == 0) {
            throw new IllegalArgumentException("O ID para alteração não pode ser nulo/zero!");
        }

        validar(cliente);

        obterPorId(id);

        cliente.setId(id);

        return clienteRepository.save(cliente);
    }

    @Override
    @Transactional
    public void excluir(Integer id) {

        Cliente cliente = obterPorId(id);

        clienteRepository.delete(cliente);
    }

    @Override
    public Cliente obterPorId(Integer id) {

        if (id == null || id <= 0) {
            throw new IllegalArgumentException("O ID para exclusão não pode ser nulo/zero!");
        }

        return clienteRepository.findById(id).orElseThrow(() -> new ClienteNaoEncontradoException("O cliente com ID " + id + " não foi encontrado!"));
    }

    @Override
    public List<Cliente> obterLista() {
        return clienteRepository.findAll();
    }

    @Transactional
    public Cliente inativar(Integer id) {

        if (id == null || id == 0) {
            throw new IllegalArgumentException("O ID para inativação não pode ser nulo/zero!");
        }

        Cliente cliente = obterPorId(id);

        if (!cliente.getStatus()) {
            System.out.println("Cliente " + cliente.getNome() + " já está inativo!");
            return cliente;
        }

        cliente.setStatus(false);

        return clienteRepository.save(cliente);
    }

    public Cliente obterPorCpf(String cpf) {

        return clienteRepository.findByCpf(cpf).orElseThrow(() -> new ClienteNaoEncontradoException("O cliente com CPF " + cpf + " não foi encontrado!"));
    }
}