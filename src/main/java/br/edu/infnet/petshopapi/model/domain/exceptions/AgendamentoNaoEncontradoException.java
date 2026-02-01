package br.edu.infnet.petshopapi.model.domain.exceptions;

public class AgendamentoNaoEncontradoException extends RuntimeException {

    public AgendamentoNaoEncontradoException(String message) {
        super(message);
    }
}
