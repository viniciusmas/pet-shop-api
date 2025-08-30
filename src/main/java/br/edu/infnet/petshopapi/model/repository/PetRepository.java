package br.edu.infnet.petshopapi.model.repository;

import br.edu.infnet.petshopapi.model.domain.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PetRepository extends JpaRepository<Pet, Integer> {

    Optional<List<Pet>> findByNomeContaining(String nome);

    Optional<List<Pet>> findByIdadeBetween(Integer min, Integer max);
}
