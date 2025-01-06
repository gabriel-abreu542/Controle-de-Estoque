package service;

import dao.TransacaoDAO;
import model.Transacao;

import java.sql.SQLException;

public class CadastroTransacao extends Cadastro<Transacao>{
    public CadastroTransacao(TransacaoDAO dao) throws SQLException {
        super();
    }

    @Override
    public void setDAO() throws SQLException {

    }

    @Override
    public boolean regraInsercao(Transacao item) throws IllegalArgumentException {


        return false;
    }

    @Override
    public boolean regraAtualizacao(Transacao item) throws IllegalArgumentException {
        return false;
    }
}
