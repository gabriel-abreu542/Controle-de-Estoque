package service;

import model.Transacao;

import java.sql.SQLException;
import java.util.ArrayList;

public abstract class CadastroTransacao extends Cadastro<Transacao>{

    public CadastroTransacao() throws SQLException {
        super();
        dao.criarTabela();

    }

    @Override
    public abstract void setDAO() throws SQLException;

    public abstract String nextId() throws SQLException;

    public boolean transacaoOK(Transacao item) throws IllegalArgumentException{
        if(item.getItens().isEmpty()){
            throw new IllegalArgumentException("Nenhum item adicionado");
        }
        ArrayList<String> formasPagamento = new ArrayList<>();
        formasPagamento.add("PIX");
        formasPagamento.add("DINHEIRO");
        formasPagamento.add("CREDITO");
        formasPagamento.add("DEBITO");
        System.out.println("COMPARAÇÃO CONTAINS:");
        System.out.println(item.getFormaPagamento());
        System.out.println(formasPagamento);
        if(!formasPagamento.contains(item.getFormaPagamento())){
            throw new IllegalArgumentException("Forma de pagamento " + item.getFormaPagamento() + " não reconhecida.");
        }

        return true;
    }

    @Override
    public boolean regraInsercao(Transacao item) throws IllegalArgumentException {
        return true;
    }

    @Override
    public boolean regraAtualizacao(Transacao item) throws IllegalArgumentException {
        return false;
    }
}
