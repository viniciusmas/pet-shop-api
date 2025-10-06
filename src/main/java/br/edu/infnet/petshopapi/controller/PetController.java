package br.edu.infnet.petshopapi.controller;

import br.edu.infnet.petshopapi.model.dto.IdadeDTO;
import br.edu.infnet.petshopapi.model.domain.Pet;
import br.edu.infnet.petshopapi.model.dto.PetRequestDTO;
import br.edu.infnet.petshopapi.model.dto.PetResponseDTO;
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
    public ResponseEntity<PetResponseDTO> incluir(@Valid @RequestBody PetRequestDTO petRequestDTO) {
        PetResponseDTO petNovo = petService.incluir(new Pet(petRequestDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(petNovo);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PetResponseDTO> alterar(@PathVariable Integer id, @Valid @RequestBody PetRequestDTO petRequestDTO) {
        PetResponseDTO petAlterado = petService.alterar(id, new Pet(petRequestDTO));
        return ResponseEntity.ok(petAlterado);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        petService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<PetResponseDTO>> obterLista() {
        List<PetResponseDTO> pets = petService.obterLista();
        if (pets.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pets);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PetResponseDTO> obterPorId(@PathVariable Integer id) {
        PetResponseDTO pet = petService.obterPorId(id);
        return ResponseEntity.ok(pet);
    }

    @GetMapping(value = "/idades")
    public ResponseEntity<List<PetResponseDTO>> obterPorIdades(@RequestBody IdadeDTO idadeDTO) {
        List<PetResponseDTO> pets = petService.obterPorIdades(idadeDTO.getIdadeMin(), idadeDTO.getIdadeMax());
        return ResponseEntity.ok(pets);
    }

    @GetMapping(value = "/contem/{nome}")
    public ResponseEntity<List<PetResponseDTO>> obterPorNomeContem(@PathVariable String nome) {
        List<PetResponseDTO> pet = petService.obterPorNomeContem(nome);
        return ResponseEntity.ok(pet);
    }
}