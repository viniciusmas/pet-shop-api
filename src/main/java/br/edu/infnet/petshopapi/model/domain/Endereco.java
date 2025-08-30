package br.edu.infnet.petshopapi.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    @NotBlank(message = "O número é obrigatório.")
    @Size(min = 1, max = 10, message = "Número deve ter entre 1 e 10 caracteres.")
    private String numero;

    @NotBlank(message = "O bairro é obrigatório.")
    @Size(min = 3, max = 50, message = "Bairro deve ter entre 3 e 50 caracteres.")
    private String bairro;

    @NotBlank(message = "O cidade é obrigatório.")
    @Size(min = 3, max = 50, message = "Cidade deve ter entre 3 e 50 caracteres.")
    private String cidade;

    @NotBlank(message = "O estado é obrigatório.")
    @Size(min = 2, max = 2, message = "UF deve ter 2 caracteres.")
    private String estado;

    @NotBlank(message = "O CEP é obrigatório.")
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "CEP inválido. Use o formato XXXXX-XXX.")
    private String cep;

    @Override
    public String toString() {
        return String.format("{Rua = %s, Número = %s, Bairro = %s, Cidade = %s, Estado = %s, CEP = %s}", logradouro, numero, bairro, cidade, estado, cep);
    }

}
