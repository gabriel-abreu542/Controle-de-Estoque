package service;

import dao.ClienteDAO;
import dao.ConexaoDB;
import dao.ObjetoDAO;
import model.Cadastravel;
import model.Cliente;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class CadastroClientes extends Cadastro<Cliente> {

    public CadastroClientes()  throws SQLException{
        super();
    }

    @Override
    public void setDAO() throws SQLException {
        this.dao = new ClienteDAO(ConexaoDB.getConnection());
    }

    @Override
    public boolean regraInsercao(Cliente item) throws IllegalArgumentException {
        return false;
    }

    @Override
    public boolean regraAtualizacao(Cliente item) throws IllegalArgumentException {
        return false;
    }
}
