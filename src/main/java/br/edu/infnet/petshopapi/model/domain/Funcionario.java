package br.edu.infnet.petshopapi.model.domain;

public class Funcionario extends Pessoa {

    private String cargo;
    private double salario;
    private double bonus;
    private Endereco endereco;

    @Override
    public String toString() {
        return String.format("%s - %s - %.2f - %.2f", super.toString(), cargo, salario, bonus);
    }

    @Override
    public String obterTipo() {
        return "Funcionario";
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    @Override
    public Endereco getEndereco() {
        return endereco;
    }

    @Override
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
