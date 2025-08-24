package br.edu.infnet.petshopapi.model.service;

import br.edu.infnet.petshopapi.model.domain.Funcionario;
import br.edu.infnet.petshopapi.model.domain.exceptions.FuncionarioInvalidoException;
import br.edu.infnet.petshopapi.model.domain.exceptions.FuncionarioNaoEncontradoException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class FuncionarioService implements CrudService<Funcionario, Integer> {

    private final Map<Integer, Funcionario> funcionarioMap = new ConcurrentHashMap<>();

    private final AtomicInteger funcionarioId = new AtomicInteger(1);

    private void validar(Funcionario funcionario) {

        if (funcionario == null){
            throw new IllegalArgumentException("O Funcionário não pode ser nulo!");
        }

        if (funcionario.getNome() == null || funcionario.getNome().trim().isEmpty()) {
            throw new FuncionarioInvalidoException("O nome do funcionário é uma informação obrigatória!");
        }
    }

    @Override
    public Funcionario incluir(Funcionario funcionario) {

        validar(funcionario);

        if (funcionario.getId() != null && funcionario.getId() != 0){
            throw new IllegalArgumentException("Um novo funcionário não pode ter um ID na inclusão!");
        }

        funcionario.setId(funcionarioId.getAndIncrement());

        funcionarioMap.put(funcionario.getId(), funcionario);

        return funcionario;
    }

    @Override
    public Funcionario alterar(Integer id, Funcionario funcionario) {

        if (id == null || id == 0) {
            throw new IllegalArgumentException("O ID para alteração não pode ser nulo/zero!");
        }

        validar(funcionario);

        obterPorId(id);

        funcionario.setId(id);

        funcionarioMap.put(funcionario.getId(), funcionario);

        return funcionario;
    }

    @Override
    public void excluir(Integer id) {

        if (id == null || id == 0) {
            throw new IllegalArgumentException("O ID para exclusão não pode ser nulo/zero!");
        }

        if (!funcionarioMap.containsKey(id)) {
            throw new FuncionarioNaoEncontradoException("O funcionário com ID " + id + " não foi encontrado!");
        }

        funcionarioMap.remove(id);
    }

    @Override
    public Funcionario obterPorId(Integer id) {

        Funcionario funcionario = funcionarioMap.get(id);

        if(funcionario == null) {
            throw new IllegalArgumentException("Impossível obter o funcionário pelo ID " + id);
        }

        return funcionario;
    }

    @Override
    public List<Funcionario> obterLista() {
        return new ArrayList<Funcionario>(funcionarioMap.values());
    }
}
