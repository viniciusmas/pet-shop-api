package br.edu.infnet.petshopapi.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class EnderecoRequestDTO {

    @NotBlank(message = "O CEP é obrigatório.")
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "CEP inválido. Use o formato XXXXX-XXX.")
    private String cep;

    @NotBlank(message = "O logradouro é obrigatório.")
    @Size(min = 3, max = 100, message = "Logradouro deve ter entre 3 e 100 caracteres.")
    private String logradouro;

    @NotBlank(message = "O bairro é obrigatório.")
    @Size(min = 3, max = 50, message = "Bairro deve ter entre 3 e 50 caracteres.")
    private String bairro;

    @NotBlank(message = "A localidade é obrigatório.")
    @Size(min = 3, max = 50, message = "Cidade deve ter entre 3 e 50 caracteres.")
    private String localidade;

    @NotBlank(message = "O estado é obrigatório.")
    @Size(min = 2, max = 2, message = "UF deve ter 2 caracteres.")
    private String uf;
}