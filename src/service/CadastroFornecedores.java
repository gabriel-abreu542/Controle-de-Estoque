package service;

import dao.ClienteDAO;
import dao.FornecedorDAO;
import model.Fornecedor;
import dao.ConexaoDB;

import java.sql.SQLException;

public class CadastroFornecedores extends Cadastro<Fornecedor>{

    public CadastroFornecedores() throws SQLException{
        super();
    }

    @Override
    public void setDAO() throws SQLException {
        this.dao = new FornecedorDAO(ConexaoDB.getConnection());
    }

    @Override
    public boolean regraInsercao(Fornecedor item) throws IllegalArgumentException {
        return false;
    }

    @Override
    public boolean regraAtualizacao(Fornecedor item) throws IllegalArgumentException {
        return false;
    }
}
