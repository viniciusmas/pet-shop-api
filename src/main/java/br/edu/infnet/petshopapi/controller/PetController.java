package br.edu.infnet.petshopapi.controller;

import br.edu.infnet.petshopapi.model.dto.IdadeDTO;
import br.edu.infnet.petshopapi.model.domain.Pet;
import br.edu.infnet.petshopapi.model.service.PetService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pets")
public class PetController {

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }


    @PostMapping
    public ResponseEntity<Pet> incluir(@Valid @RequestBody Pet pet) {

        Pet petNovo = petService.incluir(pet);

        return ResponseEntity.status(HttpStatus.CREATED).body(petNovo);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Pet> alterar(@PathVariable Integer id, @Valid @RequestBody Pet pet) {

        Pet petAlterado = petService.alterar(id, pet);

        return ResponseEntity.ok(petAlterado);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {

        petService.excluir(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Pet>> obterLista() {

        List<Pet> pets = petService.obterLista();

        if (pets.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(pets);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Pet> obterPorId(@PathVariable Integer id) {

        Pet pet = petService.obterPorId(id);

        return ResponseEntity.ok(pet);
    }

    @GetMapping(value = "/idades")
    public ResponseEntity<List<Pet>> obterPorIdades(@RequestBody IdadeDTO idadeDTO) {

        List<Pet> pets = petService.obterPorIdades(idadeDTO.getIdadeMin(), idadeDTO.getIdadeMax());

        return ResponseEntity.ok(pets);
    }

    @GetMapping(value = "/contem/{nome}")
    public ResponseEntity<List<Pet>> obterPorNomeContem(@PathVariable String nome) {

        List<Pet> pet = petService.obterPorNomeContem(nome);

        return ResponseEntity.ok(pet);
    }
}
