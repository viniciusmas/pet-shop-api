package br.edu.infnet.petshopapi.model.domain;

import br.edu.infnet.petshopapi.model.dto.EnderecoDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "O logradouro é obrigatório.")
    @Size(min = 3, max = 100, message = "Logradouro deve ter entre 3 e 100 caracteres.")
    private String logradouro;

    @NotBlank(message = "O bairro é obrigatório.")
    @Size(min = 3, max = 50, message = "Bairro deve ter entre 3 e 50 caracteres.")
    private String bairro;

    @NotBlank(message = "O cidade é obrigatório.")
    @Size(min = 3, max = 50, message = "Cidade deve ter entre 3 e 50 caracteres.")
    private String cidade;

    @NotBlank(message = "O estado é obrigatório.")
    @Size(min = 2, max = 2, message = "UF deve ter 2 caracteres.")
    @Transient
    private String estado;

    @NotBlank(message = "O CEP é obrigatório.")
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "CEP inválido. Use o formato XXXXX-XXX.")
    private String cep;

    @Override
    public String toString() {
        return String.format("{Logradouro = %s, Bairro = %s, Cidade = %s, Estado = %s, CEP = %s}", logradouro, bairro, cidade, estado, cep);
    }

    public Endereco() {}

    public Endereco (EnderecoDTO enderecoDTO) {
        this.setCep(enderecoDTO.getCep());
        this.setLogradouro(enderecoDTO.getLogradouro());
        this.setBairro(enderecoDTO.getBairro());
        this.setCidade(enderecoDTO.getLocalidade());
        this.setEstado(enderecoDTO.getUf());
    }
}