package dao;

import model.Transacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class TransacaoDAO extends ObjetoDAO<Transacao>{
    protected String tabelaItens;
    protected String sqlCriarItens;
    protected String sqlDeletarItens;

    public TransacaoDAO(Connection conn) {
        super(conn);
    }

    @Override
    public ArrayList<Transacao> listarTodos() {
        return super.listarTodos();
    }

    @Override
    public void configurarParametrosAtualizacao(PreparedStatement stmt, Transacao objeto) throws SQLException {

    }

    @Override
    public void configurarParametrosInsercao(PreparedStatement stmt, Transacao objeto) throws SQLException {

    }

    @Override
    public Transacao buscarNaTabela(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    public Transacao buscarPorId(String id) {
        return super.buscarPorId(id);
    }

    @Override
    public void atualizar(Transacao objeto) {
        super.atualizar(objeto);
    }

    @Override
    public void deletarTabela() {
        String sql = "DROP TABLE " + tabela;
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.execute();
            System.out.println("Tabela '" + tabela + "' deletada");
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("Erro ao deletar a tabela '" + tabela + "'");
        }
    }
}
