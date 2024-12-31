package dao;

import model.Fornecedor;
import model.Compra;
import model.Produto;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CompraDAO {
    private final Connection connection;

    public CompraDAO(Connection conn){
        connection = conn;
    }

    public void criarTabelasCompras(){
        String sqlCompras = "CREATE TABLE IF NOT EXISTS compras ("+
                "id TEXT PRIMARY KEY," +
                "formaPagamento TEXT CHECK( formaPagamento IN ('DINHEIRO','DEBITO','PIX','CREDITO') ) NOT NULL," +
                "data DATE NOT NULL," +
                "total REAL NOT NULL," +
                "cnpjFornecedor TEXT," +
                "FOREIGN KEY (cnpjFornecedor) REFERENCES fornecedores(id)" +
                ");";
        // necessaria uma tabela itensCompra para identificar os produtos comprados por quantidade em cada compra
        String sqlItens = "CREATE TABLE IF NOT EXISTS itensCompra ("+
                "idCompra TEXT," +
                "idProduto TEXT," +
                "quantidade INT NOT NULL," +
                //  "total parcial REAL NOT NULL" +      // decidir se total parcial será calculado ou armazenado
                "PRIMARY KEY (idCompra, idProduto)," +
                "FOREIGN KEY (idCompra) REFERENCES compras(id)," +
                "FOREIGN KEY (idProduto) REFERENCES produtos(id)" +
                ");";

        try (PreparedStatement stmt = connection.prepareStatement(sqlCompras)){
            stmt.execute();
            System.out.println("Tabela 'compras' criada ou ja existe");
        }catch (SQLException e){
            e.printStackTrace();
        }

        try(PreparedStatement stmt = connection.prepareStatement(sqlItens)){
            stmt.execute();
            System.out.println("Tabela 'itensCompra' criada ou ja existe");
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void inserirCompra(Compra novaCompra){
        String sqlCompras = "INSERT INTO compras (id, formaPagamento, data, total, cnpjFornecedor) VALUES (?,?,?,?,?)";
        String sqlItens = "INSERT into itensCompra (idCompra,idProduto,quantidade) VALUES (?,?,?)";

        try (PreparedStatement stmt = connection.prepareStatement(sqlCompras)){
            stmt.setString(1, novaCompra.getId());
            stmt.setString(2, novaCompra.getFormaPagamento());
            stmt.setString(3, novaCompra.getDataTransacao().toString());
            stmt.setFloat( 4, novaCompra.getValorTotal());
            stmt.setString(5, novaCompra.getFornecedor().getId());
            stmt.executeUpdate();
            System.out.println("Cadastro na tabela 'compras' concluido.");
        }catch (SQLException e){
            e.printStackTrace();
        }

        String idCompra = novaCompra.getId();
        for (Map.Entry<Produto, Integer> entry : novaCompra.getItens().entrySet()){
            String idProduto = entry.getKey().getId();
            int quant = entry.getValue();

            try (PreparedStatement stmt = connection.prepareStatement(sqlItens)){
                stmt.setString(1, idCompra);
                stmt.setObject(2, idProduto);
                stmt.setInt(3, quant);

                stmt.executeUpdate();
                System.out.println("Cadastro na tabela 'ItensCompra' concluido.");
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

    }

    public Compra buscarCompraId(String id){
        String sqlCompras = "SELECT * FROM compras WHERE id = ?";
        Compra compra = null;
        try (PreparedStatement stmt = connection.prepareStatement(sqlCompras)){
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            FornecedorDAO fornecedorDAO = new FornecedorDAO(connection);
            if(rs.next()){
                Fornecedor fornecedor = fornecedorDAO.buscarPorId(rs.getString("cnpjFornecedor"));
                if (fornecedor != null) {
                    compra = new Compra(
                            rs.getString("id"),
                            fornecedor,
                            rs.getString("formaPagamento")
                    );
                    compra.setDataTransacao(LocalDate.parse(rs.getString("data")));
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        String sqlItens = "SELECT * FROM itensCompra WHERE idCompra = ?";

        try(PreparedStatement stmt = connection.prepareStatement(sqlItens)){
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            ProdutoDAO produtoDAO = new ProdutoDAO(connection);
            while(rs.next()){
                Produto produto = produtoDAO.buscarPorId(rs.getString("idProduto"));
                if(produto != null){
                    assert compra != null;
                    compra.adicionarItem(produto, rs.getInt("quantidade"));
                }
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }


        return compra;
    }

    public List<Compra> listarCompras(){
        String sqlCompras = "SELECT * FROM compras";
        String sqlItens = "SELECT * FROM itensCompra WHERE idCompra = ?";
        ArrayList<Compra> lista = new ArrayList<>();
        Compra compra = null;
        FornecedorDAO fornecedorDAO = new FornecedorDAO(connection);

        try(PreparedStatement stmt = connection.prepareStatement(sqlCompras)){
            ResultSet rs = stmt.executeQuery();
            Fornecedor fornecedor = null;
            while (rs.next()){
                fornecedor = fornecedorDAO.buscarPorId(rs.getString("cnpjFornecedor"));
                compra = new Compra(
                        rs.getString("id"),
                        fornecedor,
                        rs.getString("formaPagamento")
                );
                compra.setDataTransacao(LocalDate.parse(rs.getString("data")));

                try(PreparedStatement stmt2 = connection.prepareStatement(sqlItens)){
                    stmt2.setString(1, compra.getId());
                    ResultSet rs2 = stmt2.executeQuery();
                    ProdutoDAO produtoDAO = new ProdutoDAO(connection);
                    while(rs2.next()){
                        Produto produto = produtoDAO.buscarPorId(rs2.getString("idProduto"));
                        if(produto != null){
                            compra.adicionarItem(produto, rs2.getInt("quantidade"));
                        }
                    }

                }catch (SQLException e) {
                    e.printStackTrace();
                }

                lista.add(compra);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }


        return lista;
    }

    public void removerCompra(String id){
        String sqlItens = "DELETE FROM itensCompra WHERE idCompra = ?";
        Compra compra = null;

        try(PreparedStatement stmt = connection.prepareStatement(sqlItens)){
            stmt.setString(1, id);
            stmt.executeUpdate();
            System.out.println("Registros da compra removidos da tabela 'ItensCompra'");

        }catch (SQLException e) {
            e.printStackTrace();
        }

        String sqlCompras = "DELETE FROM compras WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sqlCompras)){
            stmt.setString(1, id);
            stmt.executeUpdate();
            System.out.println("Compra removida da tabela 'compras' com sucesso");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void deletarTabelasCompras(){
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("DROP TABLE compras");
            System.out.println("Tabela 'compras' deletada");
            stmt.execute("DROP TABLE itensCompra");
            System.out.println("Tabela 'itensCompra' deletada");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
