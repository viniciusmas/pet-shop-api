package br.edu.infnet.petshopapi.model.domain.exceptions;

public class PetInvalidoException extends RuntimeException {

    public PetInvalidoException(String mensagem) {
        super(mensagem);
    }
}
