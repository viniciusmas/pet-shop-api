package br.edu.infnet.petshopapi.model.service;

import br.edu.infnet.petshopapi.model.domain.Cliente;
import br.edu.infnet.petshopapi.model.domain.Endereco;
import br.edu.infnet.petshopapi.model.domain.exceptions.ClienteInvalidoException;
import br.edu.infnet.petshopapi.model.domain.exceptions.ClienteNaoEncontradoException;
import br.edu.infnet.petshopapi.model.dto.ClienteResponseDTO;
import br.edu.infnet.petshopapi.model.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    private final ViaCepService viaCepService;

    @Transactional
    public ClienteResponseDTO incluir(Cliente cliente) {

        validar(cliente);

        if (cliente.getId() != null && cliente.getId() != 0) {
            throw new IllegalArgumentException("Um novo cliente não pode ter um ID na inclusão!");
        }

        cliente.setEndereco(new Endereco(viaCepService.getEnderecoViaCep(cliente.getCepConsulta()), new ClienteResponseDTO()));

        return new ClienteResponseDTO(clienteRepository.save(cliente));
    }

    @Transactional
    public ClienteResponseDTO alterar(Integer id, Cliente clienteNovo) {

        if (id == null || id == 0) {
            throw new IllegalArgumentException("O ID para alteração não pode ser nulo/zero!");
        }

        validar(clienteNovo);

        ClienteResponseDTO clienteAntigo = obterPorId(id);

        clienteNovo.setId(id);

        clienteNovo.setEndereco(new Endereco(viaCepService.getEnderecoViaCep(clienteNovo.getCepConsulta()), clienteAntigo));

        return new ClienteResponseDTO(clienteRepository.save(clienteNovo));
    }

    @Transactional
    public void excluir(Integer id) {

        ClienteResponseDTO clienteResponseDTO = obterPorId(id);

        clienteRepository.delete(new Cliente(clienteResponseDTO));
    }

    public ClienteResponseDTO obterPorId(Integer id) {

        if (id == null || id <= 0) {
            throw new IllegalArgumentException("O ID para exclusão não pode ser nulo/zero!");
        }

        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new ClienteNaoEncontradoException("O cliente com ID " + id + " não foi encontrado!"));

        return new ClienteResponseDTO(cliente);
    }
    
    public List<ClienteResponseDTO> obterLista() {

        List<Cliente> clientes = clienteRepository.findAll();

        List<ClienteResponseDTO> clienteResponseDTOs = new ArrayList<>();

        for (Cliente cliente : clientes) {
            clienteResponseDTOs.add(new ClienteResponseDTO(cliente));
        }

        return clienteResponseDTOs;
    }

    @Transactional
    public ClienteResponseDTO inativar(Integer id) {

        if (id == null || id == 0) {
            throw new IllegalArgumentException("O ID para inativação não pode ser nulo/zero!");
        }

        ClienteResponseDTO cliente = obterPorId(id);

        if (!cliente.getStatus()) {
            System.out.println("Cliente " + cliente.getNome() + " já está inativo!");
            return cliente;
        }

        cliente.setStatus(false);

        return new ClienteResponseDTO(clienteRepository.save(new Cliente(cliente)));
    }

    public ClienteResponseDTO obterPorCpf(String cpf) {
        Cliente cliente = clienteRepository.findByCpf(cpf).orElseThrow(() -> new ClienteNaoEncontradoException("O cliente com CPF " + cpf + " não foi encontrado!"));
        return new ClienteResponseDTO(cliente);
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
}