package br.edu.infnet.petshopapi.model.domain;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.Valid;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.CascadeType;

@Setter
@Getter
@MappedSuperclass
public abstract class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 0, max = 50, message = "O nome deve estar entre 3 e 50 caracteres")
    private String nome;

    @NotBlank(message = "O CPF é obrigatório.")
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "CPF inválido. Use o formato XXX.XXX.XXX-XX.")
    private String cpf;

    @NotBlank(message = "O RG é obrigatório.")
    private String rg;

    private LocalDateTime dataNascimento;

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

    @Valid
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    @Override
    public String toString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return String.format("ID = %d, Nome = %s, CPF = %s, RG = %s, Data de Nascimento = %s, Sexo = %s, Estado Civil = %s, Telefone = %s, Email = %s, Endereço = %s,",
                id, nome, cpf, rg, dataNascimento.format(dateTimeFormatter), sexo, estadoCivil, telefone, email, endereco);
    }

    public abstract String obterTipo();

}
