package br.edu.infnet.petshopapi.controller;

import br.edu.infnet.petshopapi.model.dto.IdadeDTO;
import br.edu.infnet.petshopapi.model.domain.Pet;
import br.edu.infnet.petshopapi.model.dto.PetRequestDTO;
import br.edu.infnet.petshopapi.model.dto.PetResponseDTO;
import br.edu.infnet.petshopapi.model.service.PetService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PetResponseDTO> incluir(@Valid @RequestBody PetRequestDTO petRequestDTO) {
        PetResponseDTO petNovo = petService.incluir(new Pet(petRequestDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(petNovo);
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PetResponseDTO> alterar(@PathVariable Integer id, @Valid @RequestBody PetRequestDTO petRequestDTO) {
        PetResponseDTO petAlterado = petService.alterar(id, new Pet(petRequestDTO));
        return ResponseEntity.status(HttpStatus.OK).body(petAlterado);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        petService.excluir(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<PetResponseDTO>> obterLista() {
        List<PetResponseDTO> pets = petService.obterLista();
        if (pets.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(pets);
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<PetResponseDTO> obterPorId(@PathVariable Integer id) {
        PetResponseDTO pet = petService.obterPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(pet);
    }

    @GetMapping(value = "/idades")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<PetResponseDTO>> obterPorIdades(@RequestBody IdadeDTO idadeDTO) {
        List<PetResponseDTO> pets = petService.obterPorIdades(idadeDTO.getIdadeMin(), idadeDTO.getIdadeMax());
        return ResponseEntity.status(HttpStatus.OK).body(pets);
    }

    @GetMapping(value = "/contem/{nome}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<PetResponseDTO>> obterPorNomeContem(@PathVariable String nome) {
        List<PetResponseDTO> pet = petService.obterPorNomeContem(nome);
        return ResponseEntity.status(HttpStatus.OK).body(pet);
    }
}