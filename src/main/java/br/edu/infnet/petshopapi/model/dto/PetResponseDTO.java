package br.edu.infnet.petshopapi.model.dto;

import br.edu.infnet.petshopapi.model.domain.Cliente;
import br.edu.infnet.petshopapi.model.domain.Pet;
import br.edu.infnet.petshopapi.model.domain.TipoEspecie;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PetResponseDTO {

    private Integer id;

    private String nome;

    private TipoEspecie tipoEspecie;

    private String raca;

    private Integer idade;

    private Double peso;

    private Cliente tutor;

    public PetResponseDTO(Pet pet) {
        this.setId(pet.getId());
        this.setNome(pet.getNome());
        this.setTipoEspecie(pet.getTipoEspecie());
        this.setRaca(pet.getRaca());
        this.setIdade(pet.getIdade());
        this.setPeso(pet.getPeso());
        this.setTutor(pet.getTutor());
    }
}
