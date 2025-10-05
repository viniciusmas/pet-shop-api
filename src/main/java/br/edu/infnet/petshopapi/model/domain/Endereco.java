package br.edu.infnet.petshopapi.model.domain;

import br.edu.infnet.petshopapi.model.dto.ClienteResponseDTO;
import br.edu.infnet.petshopapi.model.dto.EnderecoRequestDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String cep;

    private String logradouro;

    private String bairro;

    private String localidade;

    private String uf;

    @Override
    public String toString() {
        return String.format("{Logradouro = %s, Bairro = %s, Cidade = %s, Estado = %s, CEP = %s}", logradouro, bairro, localidade, uf, cep);
    }

    public Endereco() {}

    public Endereco (EnderecoRequestDTO enderecoRequestDTO, ClienteResponseDTO clienteAntigo) {
        if (clienteAntigo != null) this.setId(clienteAntigo.getEndereco().getId());
        this.setCep(enderecoRequestDTO.getCep());
        this.setLogradouro(enderecoRequestDTO.getLogradouro());
        this.setBairro(enderecoRequestDTO.getBairro());
        this.setLocalidade(enderecoRequestDTO.getLocalidade());
        this.setUf(enderecoRequestDTO.getUf());
    }

}