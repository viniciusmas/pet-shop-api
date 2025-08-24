package br.edu.infnet.petshopapi.model.service;

import java.util.List;

public interface CrudService <T,ID> {

    T incluir(T entity);
    T alterar(ID id, T entity);
    void excluir(ID id);
    T obterPorId(ID id);
    List<T> obterLista();
}
