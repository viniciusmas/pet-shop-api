package br.edu.infnet.petshopapi.model.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Endereco {

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
