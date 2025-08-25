package br.edu.infnet.petshopapi.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String logradouro;

    private Integer numero;

    private String bairro;

    private String cidade;

    private String estado;

    private Integer cep;

    @Override
    public String toString() {
        return String.format("{Rua = %s, Número = %d, Bairro = %s, Cidade = %s, Estado = %s, CEP = %d}", logradouro, numero, bairro, cidade, estado, cep);
    }

}
