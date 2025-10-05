package br.edu.infnet.petshopapi.model.service;

import br.edu.infnet.petshopapi.model.domain.Endereco;
import br.edu.infnet.petshopapi.model.domain.Funcionario;
import br.edu.infnet.petshopapi.model.domain.exceptions.FuncionarioInvalidoException;
import br.edu.infnet.petshopapi.model.domain.exceptions.FuncionarioNaoEncontradoException;
import br.edu.infnet.petshopapi.model.dto.EnderecoRequestDTO;
import br.edu.infnet.petshopapi.model.repository.FuncionarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class FuncionarioService implements CrudService<Funcionario, Integer> {

    private final FuncionarioRepository funcionarioRepository;
    private final ViaCepService viaCepService;

    public FuncionarioService(FuncionarioRepository funcionarioRepository, ViaCepService viaCepService) {
        this.funcionarioRepository = funcionarioRepository;
        this.viaCepService = viaCepService;
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
    @Transactional
    public Funcionario incluir(Funcionario funcionario) {

        validar(funcionario);

        if (funcionario.getId() != null && funcionario.getId() != 0){
            throw new IllegalArgumentException("Um novo funcionário não pode ter um ID na inclusão!");
        }

        EnderecoRequestDTO enderecoRequestDTO = viaCepService.getEndereco(funcionario.getCepConsulta().replace("-", ""));

        if (enderecoRequestDTO == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CEP inválido ou não encontrado.");
        }

        funcionario.setEndereco(new Endereco(enderecoRequestDTO, null));

        return funcionarioRepository.save(funcionario);
    }

    @Override
    @Transactional
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
    @Transactional
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
