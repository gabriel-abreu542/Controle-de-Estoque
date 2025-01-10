package service;

import dao.ConexaoDB;
import dao.VendaDAO;

import java.sql.SQLException;

public class CadastroVenda extends CadastroTransacao{
    private static int contadorId;
    public CadastroVenda(VendaDAO vendaDAO) throws SQLException {
        super();
        dao.criarTabela();
        contadorId = Integer.parseInt(dao.ultimoId().substring(6)); // pega apenas os 3 ultimos caracteres do maior id
    }

    @Override
    public void setDAO() throws SQLException {
        this.dao = new VendaDAO(ConexaoDB.getConnection());
    }

    @Override
    public String nextId() throws SQLException {
        contadorId++;
        String id = contadorId > 99 ? String.format("%03d", contadorId) : String.valueOf(contadorId);;
        return "Compra" + String.format("%03d", contadorId);
    }
}
