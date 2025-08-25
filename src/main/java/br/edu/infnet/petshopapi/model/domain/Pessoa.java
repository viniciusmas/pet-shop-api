package br.edu.infnet.petshopapi.model.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Setter
@Getter
@MappedSuperclass
public abstract class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    private Integer cpf;

    private Integer rg;

    private LocalDateTime dataNascimento;

    private String sexo;

    private String estadoCivil;

    private Integer telefone;

    private String email;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    @Override
    public String toString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return String.format("ID = %d, Nome = %s, CPF = %d, RG = %d, Data de Nascimento = %s, Sexo = %s, Estado Civil = %s, Telefone = %d, Email = %s, Endereço = %s,",
                id, nome, cpf, rg, dataNascimento.format(dateTimeFormatter), sexo, estadoCivil, telefone, email, endereco);
    }

    public abstract String obterTipo();

}
