package br.edu.infnet.petshopapi.model.domain.exceptions;

public class PetNaoEncontradoException extends RuntimeException {

    public PetNaoEncontradoException(String message) {
        super(message);
    }
}
