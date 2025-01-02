package service;

import dao.TransacaoDAO;
import model.Transacao;

public class CadastroTransacao extends Cadastro<Transacao>{
    public CadastroTransacao(TransacaoDAO dao) {
        super(dao);
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
