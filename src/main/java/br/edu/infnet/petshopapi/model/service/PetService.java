package br.edu.infnet.petshopapi.model.service;

import br.edu.infnet.petshopapi.model.domain.Cliente;
import br.edu.infnet.petshopapi.model.domain.Pet;
import br.edu.infnet.petshopapi.model.domain.exceptions.PetInvalidoException;
import br.edu.infnet.petshopapi.model.domain.exceptions.PetNaoEncontradoException;
import br.edu.infnet.petshopapi.model.dto.ClienteResponseDTO;
import br.edu.infnet.petshopapi.model.dto.PetResponseDTO;
import br.edu.infnet.petshopapi.model.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;

    private final ClienteService clienteService;

    @Transactional
    public PetResponseDTO incluir(Pet pet) {

        validar(pet);

        if (pet.getId() != null && pet.getId() != 0) {
            throw new IllegalArgumentException("Um novo pet não pode ter um ID na inclusão!");
        }

        ClienteResponseDTO tutor = clienteService.obterPorId(pet.getTutor().getId());

        pet.setTutor(new Cliente(tutor));

        return new PetResponseDTO(petRepository.save(pet));
    }

    @Transactional
    public PetResponseDTO alterar(Integer id, Pet petNovo) {

        if (id == null || id == 0) {
            throw new IllegalArgumentException("O ID para alteração não pode ser nulo/zero!");
        }

        validar(petNovo);

        PetResponseDTO petAntigo = obterPorId(id);

        petNovo.setId(id);

        petNovo.setTutor(petAntigo.getTutor());

        return new PetResponseDTO(petRepository.save(petNovo));
    }

    @Transactional
    public void excluir(Integer id) {
        PetResponseDTO pet = obterPorId(id);
        petRepository.delete(new Pet(pet));
    }

    public PetResponseDTO obterPorId(Integer id) {

        if (id == null || id <= 0) {
            throw new IllegalArgumentException("O ID para exclusão não pode ser nulo/zero!");
        }

        Pet pet = petRepository.findById(id).orElseThrow(() -> new PetNaoEncontradoException("O pet com ID " + id + " não foi encontrado!"));

        return new PetResponseDTO(pet);
    }

    public List<PetResponseDTO> obterPorIdCliente(Integer id) {

        if (id == null){
            throw new IllegalArgumentException("O nome do Pet não pode ser nulo!");
        }

        List<Pet> pets = petRepository.findByTutor_Id(id).orElseThrow(() -> new PetNaoEncontradoException("Nenhum pet não foi encontrado!"));

        List<PetResponseDTO> petsDTO = new ArrayList<>();

        for (Pet pet : pets) {
            petsDTO.add(new PetResponseDTO(pet));
        }
        return petsDTO;
    }

    public List<PetResponseDTO> obterLista() {

        List<Pet> pets = petRepository.findAll();

        List<PetResponseDTO> petsDTO = new ArrayList<>();

        for (Pet pet : pets) {
            petsDTO.add(new PetResponseDTO(pet));
        }

        return petsDTO;
    }

    public List<PetResponseDTO> obterPorNomeContem(String nome) {

        if (nome == null){
            throw new IllegalArgumentException("O nome do Pet não pode ser nulo!");
        }

        List<Pet> pets = petRepository.findByNomeContaining(nome).orElseThrow(() -> new PetNaoEncontradoException("Nenhum pet não foi encontrado!"));

        List<PetResponseDTO> petsDTO = new ArrayList<>();

        for (Pet pet : pets) {
            petsDTO.add(new PetResponseDTO(pet));
        }

        return petsDTO;
    }

    public List<PetResponseDTO> obterPorIdades(Integer min, Integer max) {

        if ((min == null || max == null) || (min >= max)) {
            throw new IllegalArgumentException("Idade inválida!");
        }

        List<Pet> pets = petRepository.findByIdadeBetween(min, max).orElseThrow(() -> new PetNaoEncontradoException("Nenhum pet não foi encontrado!"));

        List<PetResponseDTO> petsDTO = new ArrayList<>();

        for (Pet pet : pets) {
            petsDTO.add(new PetResponseDTO(pet));
        }

        return petsDTO;
    }

    private void validar(Pet pet) {

        if (pet == null){
            throw new IllegalArgumentException("O Pet não pode ser nulo!");
        }

        if (pet.getNome() == null || pet.getNome().trim().isEmpty()) {
            throw new PetInvalidoException("O nome do pet é uma informação obrigatória!");
        }
    }
}
