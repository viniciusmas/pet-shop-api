package br.edu.infnet.petshopapi.controller;

import br.edu.infnet.petshopapi.model.domain.Funcionario;
import br.edu.infnet.petshopapi.model.service.FuncionarioService;
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
    public Funcionario incluir(@RequestBody Funcionario funcionario) {
        return funcionarioService.incluir(funcionario);
    }

    @PutMapping(value = "/{id}")
    public Funcionario alterar(@PathVariable Integer id, @RequestBody Funcionario funcionario) {
        return funcionarioService.alterar(id, funcionario);
    }

    @DeleteMapping(value = "/{id}")
    public void excluir(@PathVariable Integer id) {
        funcionarioService.excluir(id);
    }

    @GetMapping
    public List<Funcionario> obterLista() {
        return funcionarioService.obterLista();
    }

    @GetMapping(value = "/{id}")
    public Funcionario obterPorId(@PathVariable Integer id) {
        return funcionarioService.obterPorId(id);
    }

}
