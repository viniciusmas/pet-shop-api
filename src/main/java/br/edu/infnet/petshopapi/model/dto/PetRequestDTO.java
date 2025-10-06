package br.edu.infnet.petshopapi.model.dto;

import br.edu.infnet.petshopapi.model.domain.Cliente;
import br.edu.infnet.petshopapi.model.domain.TipoEspecie;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PetRequestDTO {

    @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres.")
    @NotBlank(message = "O nome é obrigatório.")
    private String nome;

    @NotNull(message = "A especie é obrigatório.")
    private TipoEspecie tipoEspecie;

    @Size(min = 5, max = 100, message = "Raça deve ter entre 5 e 100 caracteres.")
    @NotBlank(message = "A raça é obrigatório.")
    private String raca;

    @NotNull(message = "A idade é obrigatório.")
    private Integer idade;

    @NotNull(message = "O peso é obrigatório.")
    private Double peso;

    @NotNull(message = "O tutor é obrigatório.")
    private Cliente tutor;
}
