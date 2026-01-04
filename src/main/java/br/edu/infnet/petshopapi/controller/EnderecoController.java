package br.edu.infnet.petshopapi.controller;

import br.edu.infnet.petshopapi.model.dto.EnderecoRequestDTO;
import br.edu.infnet.petshopapi.model.service.ViaCepService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/enderecos")
public class EnderecoController {

    private final ViaCepService viaCepService;

    @GetMapping(value = "/{cep}")
    public ResponseEntity<EnderecoRequestDTO> obterEndereco(@PathVariable String cep) {
        EnderecoRequestDTO enderecoRequestDTO = viaCepService.getEnderecoViaCep(cep);
        return ResponseEntity.ok(enderecoRequestDTO);
    }
}