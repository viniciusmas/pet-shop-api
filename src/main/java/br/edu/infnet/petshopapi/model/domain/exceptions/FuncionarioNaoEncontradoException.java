package br.edu.infnet.petshopapi.model.domain.exceptions;

public class FuncionarioNaoEncontradoException extends RuntimeException {

    public FuncionarioNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
