package br.edu.infnet.petshopapi.model.dto;

import br.edu.infnet.petshopapi.model.domain.Cliente;
import br.edu.infnet.petshopapi.model.domain.Endereco;
import br.edu.infnet.petshopapi.model.domain.Pet;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
public class ClienteResponseDTO {

    private Integer id;

    private String nome;

    private String cpf;

    private String rg;

    private LocalDateTime dataNascimento;

    private String sexo;

    private String estadoCivil;

    private String telefone;

    private String email;

    private LocalDateTime clienteDeste;

    private Boolean status;

    private Endereco endereco;

    private String cepConsulta;

    private List<Pet> pets;

    public ClienteResponseDTO(Cliente cliente) {
        this.setId(cliente.getId());
        this.setNome(cliente.getNome());
        this.setCpf(cliente.getCpf());
        this.setRg(cliente.getRg());
        this.setDataNascimento(cliente.getDataNascimento());
        this.setSexo(cliente.getSexo());
        this.setEstadoCivil(cliente.getEstadoCivil());
        this.setTelefone(cliente.getTelefone());
        this.setEmail(cliente.getEmail());
        this.setEndereco(cliente.getEndereco());
        this.setClienteDeste(cliente.getClienteDeste());
        this.setStatus(cliente.getStatus());
        this.setCepConsulta(cliente.getCepConsulta());
        this.setPets(cliente.getPets());
    }
}
