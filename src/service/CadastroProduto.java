package service;

import dao.ClienteDAO;
import dao.ConexaoDB;
import dao.ProdutoDAO;
import model.Produto;

import java.sql.SQLException;

public class CadastroProduto extends Cadastro<Produto>{
    private static int contadorId;

    public CadastroProduto() throws SQLException{
        super();
        dao.criarTabela();
        String ultimoId = dao.ultimoId();
        contadorId = Integer.parseInt(ultimoId.substring(ultimoId.length() - 3));
        System.out.println("contadorId: " + contadorId);
    }

    public String nextId() {
        contadorId++;
        System.out.println("Contador id: " + contadorId);
        String id = contadorId > 99 ? String.valueOf(contadorId): String.format("%03d", contadorId);
        return "Produto" + id;
    }

    @Override
    public void setDAO() throws SQLException {
        this.dao = new ProdutoDAO(ConexaoDB.getConnection());
    }

    public Produto criarProduto(String nome, String desc, String tipo){
        String id = nextId();
        Produto novoProduto = new Produto(id, nome);
        if(!desc.isEmpty()){
            novoProduto.setDesc(desc);
        }
        if(!tipo.isEmpty()) {
            novoProduto.setTipo(tipo);
        }
        cadastrar(novoProduto);

        return novoProduto;
    }

    public Produto buscarPorNome(String nome){
        return dao.buscarPorNome(nome);
    }

    @Override
    public boolean regraInsercao(Produto item) throws IllegalArgumentException {
        if(item.getNome() == null || item.getNome().length() < 3){
            throw new IllegalArgumentException("Nome do produto deve ter no mínimo 3 caracteres");
        }
        Produto p = dao.buscarPorId(item.getId());
        if (p != null){
            throw new IllegalArgumentException("Já existe um produto com esse Id");
        }

        return true;
    }

    @Override
    public boolean regraAtualizacao(Produto item) throws IllegalArgumentException {
        if(item.getNome() == null || item.getNome().length() < 3){
            throw new IllegalArgumentException("Nome do produto deve ter no mínimo 3 caracteres");
        }

        return true;
    }
}
