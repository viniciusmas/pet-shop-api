package br.edu.infnet.petshopapi.model.domain;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Setter
@Getter
public abstract class Pessoa {

    private Integer id;

    private String nome;

    private Integer cpf;

    private Integer rg;

    private LocalDateTime dataNascimento;

    private String sexo;

    private String estadoCivil;

    private Integer telefone;

    private String email;

    private Endereco endereco;

    @Override
    public String toString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return String.format("ID = %d, Nome = %s, CPF = %d, RG = %d, Data de Nascimento = %s, Sexo = %s, Estado Civil = %s, Telefone = %d, Email = %s, Endereço = %s,",
                id, nome, cpf, rg, dataNascimento.format(dateTimeFormatter), sexo, estadoCivil, telefone, email, endereco);
    }

    public abstract String obterTipo();

}
