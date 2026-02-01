package br.edu.infnet.petshopapi.model.domain.exceptions;

public class ClienteInvalidoException extends RuntimeException {

    public ClienteInvalidoException(String message) {
        super(message);
    }
}
