package service;

import dao.FornecedorDAO;

import model.Fornecedor;

public class CadastroFornecedores extends Cadastro<Fornecedor>{

    public CadastroFornecedores(FornecedorDAO fornecedorDAO) {
        super(fornecedorDAO);
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
