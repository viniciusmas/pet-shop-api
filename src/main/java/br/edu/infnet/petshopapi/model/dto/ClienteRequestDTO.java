package br.edu.infnet.petshopapi.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Setter
@Getter
public class ClienteRequestDTO {

    @NotBlank(message = "O CEP de consulta é obrigatório.")
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "CEP inválido. Use o formato XXXXX-XXX.")
    private String cepConsulta;

    @Size(min = 0, max = 50, message = "O nome deve estar entre 3 e 50 caracteres")
    private String nome;

    @NotBlank(message = "O CPF é obrigatório.")
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "CPF inválido. Use o formato XXX.XXX.XXX-XX.")
    private String cpf;

    @NotBlank(message = "O RG é obrigatório.")
    private String rg;

    private LocalDate dataNascimento;

    @NotBlank(message = "O sexo é obrigatório.")
    private String sexo;

    @NotBlank(message = "O estado civil é obrigatório.")
    private String estadoCivil;

    @NotBlank(message = "O telefone é obrigatório.")
    @Pattern(regexp = "\\(?\\d{2}\\)?\\s?\\d{4,5}-?\\d{4}", message = "Telefone inválido. Use o formato (XX) XXXXX-XXXX ou (XX) XXXX-XXXX.")
    private String telefone;

    @NotBlank(message = "O e-mail é obrigatório.")
    @Email(message = "O e-mail está inválido")
    private String email;

    private LocalDate clienteDeste;

    private Boolean status;
}
