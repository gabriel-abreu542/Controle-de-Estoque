package service;

import dao.ObjetoDAO;
import model.Cadastravel;
import model.Cliente;

import java.util.Collections;
import java.util.List;

public class CadastroClientes extends Cadastro<Cliente> {

    public CadastroClientes(ObjetoDAO dao) {
        super(dao);
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
