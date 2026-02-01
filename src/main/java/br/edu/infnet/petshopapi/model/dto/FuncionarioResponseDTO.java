package br.edu.infnet.petshopapi.model.dto;

import br.edu.infnet.petshopapi.model.domain.Endereco;
import br.edu.infnet.petshopapi.model.domain.Funcionario;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class FuncionarioResponseDTO {

    private Integer id;

    private String nome;

    private String cpf;

    private String rg;

    private LocalDate dataNascimento;

    private String sexo;

    private String estadoCivil;

    private String telefone;

    private String email;

    private Endereco endereco;

    private String cargo;

    private BigDecimal salario;

    private BigDecimal bonus;

    private String cepConsulta;

    public FuncionarioResponseDTO() {}

    public FuncionarioResponseDTO(Funcionario funcionario) {
        this.setId(funcionario.getId());
        this.setNome(funcionario.getNome());
        this.setCpf(funcionario.getCpf());
        this.setRg(funcionario.getRg());
        this.setDataNascimento(funcionario.getDataNascimento());
        this.setSexo(funcionario.getSexo());
        this.setEstadoCivil(funcionario.getEstadoCivil());
        this.setTelefone(funcionario.getTelefone());
        this.setEmail(funcionario.getEmail());
        this.setEndereco(funcionario.getEndereco());
        this.setCargo(funcionario.getCargo());
        this.setSalario(funcionario.getSalario());
        this.setBonus(funcionario.getBonus());
        this.setCepConsulta(funcionario.getCepConsulta());

    }
}
