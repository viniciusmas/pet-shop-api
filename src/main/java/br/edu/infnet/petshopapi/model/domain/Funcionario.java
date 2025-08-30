package br.edu.infnet.petshopapi.model.domain;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class Funcionario extends Pessoa {

    @NotBlank(message = "O Cargo é obrigatório.")
    private String cargo;

    @NotNull(message = "O salário do funcionário é obrigatório.")
    @DecimalMin(value = "0.01", message = "O salário do funcionário deve ser maior que zero.")
    @Digits(integer = 10, fraction = 2, message = "O salário do funcionário deve ter no máximo 10 digitos inteiros e 2 decimais.")
    private BigDecimal salario;

    @NotNull(message = "O bônus do funcionário é obrigatório.")
    @DecimalMin(value = "0.01", message = "O bônus do funcionário deve ser maior que zero.")
    @Digits(integer = 10, fraction = 2, message = "O bônus do funcionário deve ter no máximo 10 digitos inteiros e 2 decimais.")
    private BigDecimal bonus;

    @Override
    public String toString() {
        return String.format("Funcionario { %s Cargo = %s, Salário = %.2f, Bônus = %.2f }", super.toString(), cargo, salario, bonus);
    }

    @Override
    public String obterTipo() {
        return "Funcionario";
    }

}
