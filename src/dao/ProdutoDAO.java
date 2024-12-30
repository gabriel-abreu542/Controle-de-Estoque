package dao;

import model.Produto;
import test.dao.ConexaoDBTest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    private final Connection connection;

    public ProdutoDAO(Connection conn){
        connection = conn;
    }

    public void criarTabelaProdutos(){
        String sql = "CREATE TABLE IF NOT EXISTS produtos ("+
                "id TEXT PRIMARY KEY," +
                "nome TEXT NOT NULL," +
                "desc TEXT," +
                "tipo TEXT," +
                "precoCompra REAL NOT NULL," +
                "precoVenda REAL NOT NULL" +
                ");";

        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.execute();
            System.out.println("Tabela 'produtos' criada ou já existe");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void inserirProduto(Produto novoProduto){
        String sql = "INSERT INTO produtos (id,nome,desc,tipo,precoCompra,precoVenda) VALUES (?,?,?,?,?,?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, novoProduto.getId());
            stmt.setString(2, novoProduto.getNome());
            stmt.setString(3, novoProduto.getDesc());
            stmt.setString(4, novoProduto.getTipo());
            stmt.setFloat( 5, novoProduto.getPrecoCompra());
            stmt.setFloat( 6, novoProduto.getPrecoVenda());
            stmt.executeUpdate();
            System.out.println("Produto inserido com sucesso.");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<Produto> listarProdutos(){
        String sql = "SELECT * FROM produtos";
        Produto p = null;
        List<Produto> lista = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){

                p = new Produto(
                        rs.getString("id"),
                        rs.getString("nome"),
                        rs.getFloat("precoCompra"),
                        rs.getFloat("precoVenda")
                );
                String desc = rs.getString("desc");
                String tipo = rs.getString("tipo");
                if(!desc.equals("")){
                     p.setDesc(desc);
                }
                if(!tipo.equals("")){
                    p.setDesc(tipo);
                }

                lista.add(p);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return lista;
    }

    public Produto buscarProdutoId(String id){
        String sql = "SELECT * FROM produtos WHERE id = ?";
        Produto produto = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, id);

            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                produto = new Produto(
                        rs.getString("id"),
                        rs.getString("nome"),
                        rs.getFloat("precoCompra"),
                        rs.getFloat("precoVenda")
                );
                String desc = rs.getString("desc");
                String tipo = rs.getString("tipo");
                if(!desc.equals("")){
                    produto.setDesc(desc);
                }
                if(!tipo.equals("")){
                    produto.setTipo(tipo);
                }

            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return produto;
    }

    public void atualizarProduto(Produto produto){
        String sql = "UPDATE produtos SET nome = ?, desc = ?, tipo = ?, precoCompra = ?,precoVenda = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDesc());
            stmt.setString(3, produto.getTipo());
            stmt.setFloat( 4, produto.getPrecoCompra());
            stmt.setFloat( 5, produto.getPrecoVenda());
            stmt.setString(6, produto.getId());
            stmt.executeUpdate();
            System.out.println("Produto atualizado com sucesso.");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void removerProduto(String id){
        String sql = "DELETE FROM produtos WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
            System.out.println("Produto removido com sucesso.");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void deletarTabelaProdutos(){
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("DROP TABLE produtos");
            System.out.println("Tabela 'produtos' deletada");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
