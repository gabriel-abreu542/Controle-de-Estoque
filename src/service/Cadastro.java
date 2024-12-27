package service;

import model.Cadastravel;

import java.util.List;

public abstract interface Cadastro<T extends Cadastravel> {
    public abstract void criar(T item);
    public abstract T buscarPorId(String id);
    public abstract List<T> listarTodos();
    public abstract void atualizar(T item);
    public abstract void deletar(String id);

}
