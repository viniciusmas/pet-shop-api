package br.edu.infnet.petshopapi.model.domain;

import br.edu.infnet.petshopapi.model.dto.ClienteResponseDTO;
import br.edu.infnet.petshopapi.model.dto.EnderecoRequestDTO;
import br.edu.infnet.petshopapi.model.dto.FuncionarioResponseDTO;
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
        if (clienteAntigo.getEndereco() != null) this.setId(clienteAntigo.getEndereco().getId());
        setEndereco(enderecoRequestDTO);
    }

    public Endereco (EnderecoRequestDTO enderecoRequestDTO, FuncionarioResponseDTO funcionarioAntigo) {
        if (funcionarioAntigo.getEndereco() != null) this.setId(funcionarioAntigo.getEndereco().getId());
        setEndereco(enderecoRequestDTO);
    }

    private void setEndereco(EnderecoRequestDTO enderecoRequestDTO) {
        this.setCep(enderecoRequestDTO.getCep());
        this.setLogradouro(enderecoRequestDTO.getLogradouro());
        this.setBairro(enderecoRequestDTO.getBairro());
        this.setLocalidade(enderecoRequestDTO.getLocalidade());
        this.setUf(enderecoRequestDTO.getUf());
    }

}