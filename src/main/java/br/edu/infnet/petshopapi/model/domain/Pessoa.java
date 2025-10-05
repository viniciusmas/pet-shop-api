package br.edu.infnet.petshopapi.model.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import jakarta.validation.Valid;

@Setter
@Getter
@MappedSuperclass
public abstract class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    private String cpf;

    private String rg;

    private LocalDateTime dataNascimento;

    private String sexo;

    private String estadoCivil;

    private String telefone;

    private String email;

    @Valid
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    @Transient
    private String cepConsulta;

    @Override
    public String toString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return String.format("ID = %d, Nome = %s, CPF = %s, RG = %s, Data de Nascimento = %s, Sexo = %s, Estado Civil = %s, Telefone = %s, Email = %s, Endereço = %s,",
                id, nome, cpf, rg, dataNascimento.format(dateTimeFormatter), sexo, estadoCivil, telefone, email, endereco);
    }

    public abstract String obterTipo();

}
