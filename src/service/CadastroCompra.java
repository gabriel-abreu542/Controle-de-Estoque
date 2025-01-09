package service;

import dao.CompraDAO;
import dao.ConexaoDB;
import model.Compra;
import model.Fornecedor;
import model.Transacao;

import java.sql.SQLException;

public class CadastroCompra extends CadastroTransacao{
    private static int contadorId;
    public CadastroCompra() throws SQLException {
        super();
        dao.criarTabela();
        contadorId = Integer.parseInt(dao.ultimoId().substring(6)); // pega apenas os 3 ultimos caracteres do maior id
        System.out.println(dao.ultimoId().substring(6));
    }

    @Override
    public void setDAO() throws SQLException {
        this.dao = new CompraDAO(ConexaoDB.getConnection());
    }

    @Override
    public String nextId() throws SQLException {
        contadorId++;
        String id = contadorId > 99 ? String.format("%03d", contadorId) : String.valueOf(contadorId);;
        return "Compra" + String.format("%03d", contadorId);
    }

    public Compra criarCompra(){
        Compra novaCompra = null;



        return novaCompra;
    }

    @Override
    public boolean regraInsercao(Transacao item) throws IllegalArgumentException {
        Compra novaCompra = (Compra) item;
        try {
            if (transacaoOK(item) && novaCompra.getFornecedor() == null) {
                throw new IllegalArgumentException("Erro ao cadastrar compra: Fornecedor inválido");
            }
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Erro ao cadastrar compra: " + e.getMessage());
        }

        return true;
    }

    @Override
    public boolean regraAtualizacao(Transacao item) throws IllegalArgumentException {
        Compra novaCompra = (Compra) item;
        try {
            if (transacaoOK(item) && novaCompra.getFornecedor() == null) {
                throw new IllegalArgumentException("Erro ao cadastrar compra: Fornecedor inválido");
            }
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Erro ao cadastrar compra: " + e.getMessage());
        }

        String id = novaCompra.getId();

        try {
            if (dao.buscarPorId(id) == null) {
                return false;
            }
        }catch(IllegalArgumentException e){
            throw new IllegalArgumentException("Erro ao cadastrar compra: " + e.getMessage());
        }

        return true;
    }


}
