package service;

import dao.ClienteDAO;
import dao.FornecedorDAO;
import model.Fornecedor;
import dao.ConexaoDB;
import model.Produto;

import java.sql.SQLException;

public class CadastroFornecedores extends Cadastro<Fornecedor>{

    public CadastroFornecedores() throws SQLException{
        super();
        dao.criarTabela();
    }

    @Override
    public void setDAO() throws SQLException {
        this.dao = new FornecedorDAO(ConexaoDB.getConnection());
    }

    public Fornecedor criarFornecedor(String nome, String cnpj, String tel, String email, String end){
        Fornecedor novo = new Fornecedor(cnpj, nome, tel, email, end);
        cadastrar(novo);

        return novo;
    }

    @Override
    public boolean regraInsercao(Fornecedor item) throws IllegalArgumentException {
        if(item.getNome() == null || item.getNome().length() < 3){
            throw new IllegalArgumentException("Nome do fornecedor deve ter no mínimo 3 caracteres");
        }
        Fornecedor f = dao.buscarPorId(item.getId());
        if (f != null){
            throw new IllegalArgumentException("Já existe um fornecedor com esse Id");
        }
        return true;
    }

    @Override
    public boolean regraAtualizacao(Fornecedor item) throws IllegalArgumentException {
        if(item.getNome() == null || item.getNome().length() < 3){
            throw new IllegalArgumentException("Nome do fornecedor deve ter no mínimo 3 caracteres");
        }
        return true;
    }
}
