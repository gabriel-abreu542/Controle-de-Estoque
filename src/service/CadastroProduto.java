package service;

import dao.ClienteDAO;
import dao.ConexaoDB;
import dao.ProdutoDAO;
import model.Produto;

import java.sql.SQLException;

public class CadastroProduto extends Cadastro<Produto>{

    public CadastroProduto() throws SQLException {
        super();
    }

    @Override
    public void setDAO() throws SQLException {
        this.dao = new ProdutoDAO(ConexaoDB.getConnection());
    }

    public Produto buscarPorNome(String nome){
        return dao.buscarPorNome(nome);
    }

    @Override
    public boolean regraInsercao(Produto item) throws IllegalArgumentException {
        if(item.getNome() == null || item.getNome().length() < 3){
            throw new IllegalArgumentException("Nome do produto deve ter no mínimo 3 caracteres");
        }
        Produto p = dao.buscarPorId(item.getId());
        if (p == null){
            throw new IllegalArgumentException("Já existe um produto com esse Id");
        }

        return true;
    }

    @Override
    public boolean regraAtualizacao(Produto item) throws IllegalArgumentException {
        if(item.getNome() == null || item.getNome().length() < 3){
            throw new IllegalArgumentException("Nome do produto deve ter no mínimo 3 caracteres");
        }

        return true;
    }
}
