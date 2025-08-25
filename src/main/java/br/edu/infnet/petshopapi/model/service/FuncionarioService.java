package br.edu.infnet.petshopapi.model.service;

import br.edu.infnet.petshopapi.model.domain.Funcionario;
import br.edu.infnet.petshopapi.model.domain.exceptions.FuncionarioInvalidoException;
import br.edu.infnet.petshopapi.model.domain.exceptions.FuncionarioNaoEncontradoException;
import br.edu.infnet.petshopapi.model.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FuncionarioService implements CrudService<Funcionario, Integer> {

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

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

        return funcionarioRepository.save(funcionario);
    }

    @Override
    public Funcionario alterar(Integer id, Funcionario funcionario) {

        if (id == null || id == 0) {
            throw new IllegalArgumentException("O ID para alteração não pode ser nulo/zero!");
        }

        validar(funcionario);

        obterPorId(id);

        funcionario.setId(id);

        return funcionarioRepository.save(funcionario);
    }

    @Override
    public void excluir(Integer id) {

        Funcionario funcionario = obterPorId(id);

        funcionarioRepository.delete(funcionario);
    }

    @Override
    public Funcionario obterPorId(Integer id) {

        if (id == null || id == 0) {
            throw new IllegalArgumentException("O ID para exclusão não pode ser nulo/zero!");
        }

        return funcionarioRepository.findById(id).orElseThrow(() -> new FuncionarioNaoEncontradoException("O funcionário com ID " + id + " não foi encontrado!"));
    }

    @Override
    public List<Funcionario> obterLista() {
        return funcionarioRepository.findAll();
    }
}
