package br.edu.infnet.petshopapi.model.domain.exceptions;

public class FuncionarioInvalidoException extends RuntimeException {

    public FuncionarioInvalidoException(String message) {
        super(message);
    }
}
