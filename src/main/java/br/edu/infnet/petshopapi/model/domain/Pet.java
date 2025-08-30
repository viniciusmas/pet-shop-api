package br.edu.infnet.petshopapi.model.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres.")
    @NotBlank(message = "O nome é obrigatório.")
    private String nome;

    @NotNull(message = "A especie é obrigatório.")
    @Enumerated(EnumType.STRING)
    private TipoEspecie tipoEspecie;

    @Size(min = 5, max = 100, message = "Raça deve ter entre 5 e 100 caracteres.")
    @NotBlank(message = "A raça é obrigatório.")
    private String raca;

    @NotNull(message = "A idade é obrigatório.")
    private Integer idade;

    @NotNull(message = "O peso é obrigatório.")
    private Double peso;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente tutor;

    @Override
    public String toString() {
        return String.format("Pet {id = %d, Nome = %s, Tipo da Espécie = %s, Raça = %s, Idade = %d, Peso = %.2f, Tutor = %s}",
                id, nome, tipoEspecie != null ? tipoEspecie.name() : "N/A", raca, idade, peso,
                tutor != null ? String.format("%d - %s", tutor.getId(), tutor.getNome()) : "N/A");
    }
}
