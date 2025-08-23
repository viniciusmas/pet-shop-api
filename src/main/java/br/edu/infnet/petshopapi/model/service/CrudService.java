package br.edu.infnet.petshopapi.model.service;

import java.util.List;

public interface CrudService <T,ID> {

    T salvar(T entity);
    T obterPorId(ID id);
    void excluir(ID id);
    List<T> obterLista();
}
