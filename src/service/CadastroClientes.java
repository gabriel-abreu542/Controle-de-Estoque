package service;

import dao.ClienteDAO;
import dao.ConexaoDB;
import dao.ObjetoDAO;
import model.Cadastravel;
import model.Cliente;
import model.Fornecedor;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class CadastroClientes extends Cadastro<Cliente> {

    public CadastroClientes() throws SQLException{
        super();
        dao.criarTabela();
    }

    @Override
    public void setDAO() throws SQLException {
        this.dao = new ClienteDAO(ConexaoDB.getConnection());
    }

    public Cliente criarCliente(String cpf, String nome, String telefone){
        Cliente novo = new Cliente(cpf, nome, telefone);
        cadastrar(novo);

        return novo;
    }

    @Override
    public boolean regraInsercao(Cliente item) throws IllegalArgumentException {
        if(item.getNome() == null || item.getNome().length() < 3){
            throw new IllegalArgumentException("Nome do cliente deve ter no mínimo 3 caracteres");
        }
        Cliente c = dao.buscarPorId(item.getId());
        if (c != null){
            throw new IllegalArgumentException("Já existe um cliente com esse CPF");
        }
        return true;
    }

    @Override
    public boolean regraAtualizacao(Cliente item) throws IllegalArgumentException {
        return false;
    }
}
