package br.edu.infnet.petshopapi.model.service;

import br.edu.infnet.petshopapi.model.domain.Endereco;
import br.edu.infnet.petshopapi.model.domain.Funcionario;
import br.edu.infnet.petshopapi.model.domain.exceptions.FuncionarioInvalidoException;
import br.edu.infnet.petshopapi.model.domain.exceptions.FuncionarioNaoEncontradoException;
import br.edu.infnet.petshopapi.model.dto.EnderecoRequestDTO;
import br.edu.infnet.petshopapi.model.dto.FuncionarioResponseDTO;
import br.edu.infnet.petshopapi.model.repository.FuncionarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final ViaCepService viaCepService;

    public FuncionarioService(FuncionarioRepository funcionarioRepository, ViaCepService viaCepService) {
        this.funcionarioRepository = funcionarioRepository;
        this.viaCepService = viaCepService;
    }

    @Transactional
    public FuncionarioResponseDTO incluir(Funcionario funcionario) {

        validar(funcionario);

        if (funcionario.getId() != null && funcionario.getId() != 0){
            throw new IllegalArgumentException("Um novo funcionário não pode ter um ID na inclusão!");
        }

        EnderecoRequestDTO enderecoRequestDTO = viaCepService.getEnderecoViaCep(funcionario.getCepConsulta().replace("-", ""));

        if (enderecoRequestDTO == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CEP inválido ou não encontrado.");
        }

        funcionario.setEndereco(new Endereco(enderecoRequestDTO, new FuncionarioResponseDTO()));

        return new FuncionarioResponseDTO(funcionarioRepository.save(funcionario));
    }

    @Transactional
    public FuncionarioResponseDTO alterar(Integer id, Funcionario funcionarioNovo) {

        if (id == null || id == 0) {
            throw new IllegalArgumentException("O ID para alteração não pode ser nulo/zero!");
        }

        validar(funcionarioNovo);

        FuncionarioResponseDTO funcionarioAntigo = obterPorId(id);

        funcionarioNovo.setId(id);

        funcionarioNovo.setEndereco(new Endereco(viaCepService.getEnderecoViaCep(funcionarioNovo.getCepConsulta()), funcionarioAntigo));

        return new FuncionarioResponseDTO(funcionarioRepository.save(funcionarioNovo));
    }

    @Transactional
    public void excluir(Integer id) {

        FuncionarioResponseDTO funcionario = obterPorId(id);

        funcionarioRepository.delete(new Funcionario(funcionario));
    }

    public FuncionarioResponseDTO obterPorId(Integer id) {

        if (id == null || id == 0) {
            throw new IllegalArgumentException("O ID para exclusão não pode ser nulo/zero!");
        }

        Funcionario funcionario = funcionarioRepository.findById(id).orElseThrow(() -> new FuncionarioNaoEncontradoException("O funcionário com ID " + id + " não foi encontrado!"));

        return new FuncionarioResponseDTO(funcionario);
    }

    public List<FuncionarioResponseDTO> obterLista() {

        List<Funcionario> funcionarios = funcionarioRepository.findAll();

        List<FuncionarioResponseDTO> funcionarioResponseDTOs = new ArrayList<>();

        for (Funcionario funcionario : funcionarios) {
            funcionarioResponseDTOs.add(new FuncionarioResponseDTO(funcionario));
        }

        return funcionarioResponseDTOs;
    }

    private void validar(Funcionario funcionario) {

        if (funcionario == null){
            throw new IllegalArgumentException("O Funcionário não pode ser nulo!");
        }

        if (funcionario.getNome() == null || funcionario.getNome().trim().isEmpty()) {
            throw new FuncionarioInvalidoException("O nome do funcionário é uma informação obrigatória!");
        }
    }
}
