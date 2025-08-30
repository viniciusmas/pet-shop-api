package br.edu.infnet.petshopapi.model.service;

import br.edu.infnet.petshopapi.model.domain.Pet;
import br.edu.infnet.petshopapi.model.domain.exceptions.PetInvalidoException;
import br.edu.infnet.petshopapi.model.domain.exceptions.PetNaoEncontradoException;
import br.edu.infnet.petshopapi.model.repository.PetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PetService implements CrudService<Pet, Integer> {

    private final PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    private void validar(Pet pet) {

        if (pet == null){
            throw new IllegalArgumentException("O Pet não pode ser nulo!");
        }

        if (pet.getNome() == null || pet.getNome().trim().isEmpty()) {
            throw new PetInvalidoException("O nome do pet é uma informação obrigatória!");
        }
    }

    @Override
    @Transactional
    public Pet incluir(Pet pet) {

        validar(pet);

        if (pet.getId() != null && pet.getId() != 0) {
            throw new IllegalArgumentException("Um novo pet não pode ter um ID na inclusão!");
        }

        return petRepository.save(pet);
    }

    @Override
    @Transactional
    public Pet alterar(Integer id, Pet pet) {

        if (id == null || id == 0) {
            throw new IllegalArgumentException("O ID para alteração não pode ser nulo/zero!");
        }

        validar(pet);

        obterPorId(id);

        pet.setId(id);

        return petRepository.save(pet);
    }

    @Override
    @Transactional
    public void excluir(Integer id) {

        Pet pet = obterPorId(id);

        petRepository.delete(pet);

    }

    @Override
    public Pet obterPorId(Integer id) {

        if (id == null || id <= 0) {
            throw new IllegalArgumentException("O ID para exclusão não pode ser nulo/zero!");
        }

        return petRepository.findById(id).orElseThrow(() -> new PetNaoEncontradoException("O pet com ID " + id + " não foi encontrado!"));
    }

    @Override
    public List<Pet> obterLista() {
        return petRepository.findAll();
    }

    public List<Pet> obterPorNomeContem(String nome) {

        if (nome == null){
            throw new IllegalArgumentException("O nome do Pet não pode ser nulo!");
        }

        return petRepository.findByNomeContaining(nome).orElseThrow(() -> new PetNaoEncontradoException("Nenhum pet não foi encontrado!"));
    }

    public List<Pet> obterPorIdades(Integer min, Integer max) {

        if ((min == null || max == null) || (min >= max)) {
            throw new IllegalArgumentException("Idade inválida!");
        }

        return petRepository.findByIdadeBetween(min, max).orElseThrow(() -> new PetNaoEncontradoException("Nenhum pet não foi encontrado!"));
    }
}
