package service;

import dao.ConexaoDB;
import dao.VendaDAO;
import model.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class CadastroVenda extends CadastroTransacao{
    private static int contadorId;
    public CadastroVenda() throws SQLException {
        super();
        dao.criarTabela();
        contadorId = Integer.parseInt(dao.ultimoId().substring(6)); // pega apenas os 3 ultimos caracteres do maior id
    }

    @Override
    public void setDAO() throws SQLException {
        this.dao = new VendaDAO(ConexaoDB.getConnection());
    }

    public Venda criarVenda(String nomeF, String formaP, ArrayList<ItemTransacao> itens) throws SQLException {
        Venda novaVenda = null;
        String id = nextId();
        CadastroClientes cadastroCliente = new CadastroClientes();
        Cliente cliente = cadastroCliente.buscarPorNome(nomeF);
        novaVenda = new Venda(id, cliente, formaP);
        for (ItemTransacao i : itens){
            novaVenda.adicionarItem(i);
        }
        cadastrar(novaVenda);

        return novaVenda;
    }

    @Override
    public boolean regraInsercao(Transacao item) throws IllegalArgumentException {
        Venda novaVenda = (Venda) item;
        try {
            if (transacaoOK(item) && novaVenda.getCliente() == null) {
                throw new IllegalArgumentException("Erro ao cadastrar compra: Cliente inválido");
            }
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Erro ao cadastrar compra: " + e.getMessage());
        }

        return true;
    }

    @Override
    public String nextId() throws SQLException {
        contadorId++;
        String id = contadorId > 99 ? String.format("%03d", contadorId) : String.valueOf(contadorId);;
        return "Venda" + String.format("%03d", contadorId);
    }
}
