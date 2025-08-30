package br.edu.infnet.petshopapi.model.domain.exceptions;

public class PetNaoEncontradoException extends RuntimeException {

    public PetNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
