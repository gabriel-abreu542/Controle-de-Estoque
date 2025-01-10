package service;

import dao.ObjetoDAO;
import model.Cadastravel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class Cadastro<T extends Cadastravel> {
    ObjetoDAO<T> dao;

    public abstract boolean regraInsercao(T item) throws IllegalArgumentException;

    public abstract boolean regraAtualizacao(T item) throws IllegalArgumentException;

    public Cadastro() throws SQLException {
        setDAO();
        dao.criarTabela(); // cria a tabela que a classe de cadastros usa, se ela não existir ainda
    }

    public abstract void setDAO() throws SQLException;

    public void cadastrar(T item) throws IllegalArgumentException{
        try {
            if(regraInsercao(item))
                dao.inserir(item);
            else throw new IllegalArgumentException("");
        }
        catch (IllegalArgumentException e){
            System.out.println("Erro ao cadastrar: " + e.getMessage());
        }
    }

    public ArrayList<String> listarNomes(){
        return new ArrayList<>(dao.ListarPorParametro("nome"));
    }

    public T buscarPorId(String id){
        return dao.buscarPorId(id);
    }

    public T buscarPorNome(String nome){
        return dao.buscarPorNome(nome);
    }

    public List<T> listarTodos() {
        return dao.listarTodos();
    }

    public void atualizar(T item){
        try {
            if(regraAtualizacao(item))
                dao.atualizar(item);
        }
        catch (IllegalArgumentException e){
            System.out.println("Erro ao atualizar: " + e.getMessage());
        }
    }

    public void deletar(String id){
        T item = dao.buscarPorId(id);

        if (item == null){
            System.out.println("id " + id + " não foi encontrado");
        }
        else{
            dao.remover(id);
        }
    }

}
