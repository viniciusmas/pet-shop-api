package br.edu.infnet.petshopapi.model.domain;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Funcionario extends Pessoa {

    private String cargo;

    private Double salario;

    private Double bonus;

    @Override
    public String toString() {
        return String.format("Funcionario { %s Cargo = %s, Salário = %.2f, Bônus = %.2f }", super.toString(), cargo, salario, bonus);
    }

    @Override
    public String obterTipo() {
        return "";
    }

}
