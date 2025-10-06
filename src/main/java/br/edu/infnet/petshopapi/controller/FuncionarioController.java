package br.edu.infnet.petshopapi.controller;

import br.edu.infnet.petshopapi.model.domain.Funcionario;
import br.edu.infnet.petshopapi.model.dto.FuncionarioRequestDTO;
import br.edu.infnet.petshopapi.model.dto.FuncionarioResponseDTO;
import br.edu.infnet.petshopapi.model.service.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioController {
    
    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @PostMapping
    public ResponseEntity<FuncionarioResponseDTO> incluir(@Valid @RequestBody FuncionarioRequestDTO funcionarioRequestDTO) {
        FuncionarioResponseDTO funcionarioNovo = funcionarioService.incluir(new Funcionario(funcionarioRequestDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioNovo);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<FuncionarioResponseDTO> alterar(@PathVariable Integer id, @Valid @RequestBody FuncionarioRequestDTO funcionarioRequestDTO) {
        FuncionarioResponseDTO funcionarioAlterado = funcionarioService.alterar(id, new Funcionario(funcionarioRequestDTO));
        return ResponseEntity.ok(funcionarioAlterado);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        funcionarioService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<FuncionarioResponseDTO>> obterLista() {
        List<FuncionarioResponseDTO> funcionarios = funcionarioService.obterLista();
        if (funcionarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(funcionarios);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<FuncionarioResponseDTO> obterPorId(@PathVariable Integer id) {
        FuncionarioResponseDTO funcionario = funcionarioService.obterPorId(id);
        return ResponseEntity.ok(funcionario);
    }

}
