package br.edu.infnet.petshopapi.model.domain.exceptions;

public class ClienteInvalidoException extends RuntimeException {

    public ClienteInvalidoException(String mensagem) {
        super(mensagem);
    }
}
