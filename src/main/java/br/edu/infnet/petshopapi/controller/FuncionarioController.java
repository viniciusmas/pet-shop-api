package br.edu.infnet.petshopapi.controller;

import br.edu.infnet.petshopapi.model.domain.Funcionario;
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
    public ResponseEntity<Funcionario> incluir(@Valid @RequestBody Funcionario funcionario) {

        Funcionario funcionarioNovo = funcionarioService.incluir(funcionario);

        return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioNovo);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Funcionario> alterar(@PathVariable Integer id, @Valid @RequestBody Funcionario funcionario) {

        Funcionario funcionarioAlterado = funcionarioService.alterar(id, funcionario);

        return ResponseEntity.ok(funcionarioAlterado);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {

        funcionarioService.excluir(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Funcionario>> obterLista() {

        List<Funcionario> funcionarios = funcionarioService.obterLista();

        if (funcionarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(funcionarios);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Funcionario> obterPorId(@PathVariable Integer id) {

        Funcionario funcionario = funcionarioService.obterPorId(id);

        return ResponseEntity.ok(funcionario);
    }

}
