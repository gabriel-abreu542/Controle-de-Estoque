package dao;

import model.Cliente;
import model.Produto;
import model.Venda;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VendaDAO {
    private final Connection connection;

    public VendaDAO(Connection conn){
        connection = conn;
    }

    public void criarTabelasVendas(){
        String sqlVendas = "CREATE TABLE IF NOT EXISTS vendas ("+
                "id TEXT PRIMARY KEY," +
                "formaPagamento TEXT CHECK( formaPagamento IN ('DINHEIRO','DEBITO','PIX','CREDITO') ) NOT NULL," +
                "data DATE NOT NULL," +
                "total REAL NOT NULL," +
                "cpfCliente TEXT," +
                "FOREIGN KEY (cpfCliente) REFERENCES clientes(id)" +
                ");";
        // necessaria uma tabela itensVenda para identificar os produtos vendidos por quantidade em cada venda
        String sqlItens = "CREATE TABLE IF NOT EXISTS itensVenda ("+
                "idVenda TEXT," +
                "idProduto TEXT," +
                "quantidade INT NOT NULL," +
            //  "total parcial REAL NOT NULL" +      // decidir se total parcial será calculado ou armazenado
                "PRIMARY KEY (idVenda, idProduto)," +
                "FOREIGN KEY (idVenda) REFERENCES vendas(id)," +
                "FOREIGN KEY (idProduto) REFERENCES produtos(id)" +
                ");";

        try (PreparedStatement stmt = connection.prepareStatement(sqlVendas)){
            stmt.execute();
            System.out.println("Tabela 'vendas' criada ou ja existe");
        }catch (SQLException e){
            e.printStackTrace();
        }

        try(PreparedStatement stmt = connection.prepareStatement(sqlItens)){
            stmt.execute();
            System.out.println("Tabela 'itensVenda' criada ou ja existe");
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void inserirVenda(Venda novaVenda){
        String sqlVendas = "INSERT INTO vendas (id, formaPagamento, data, total, cpfCliente) VALUES (?,?,?,?,?)";
        String sqlItens = "INSERT into itensVenda (idVenda,idProduto,quantidade) VALUES (?,?,?)";

        try (PreparedStatement stmt = connection.prepareStatement(sqlVendas)){
            stmt.setString(1, novaVenda.getId());
            stmt.setString(2, novaVenda.getFormaPagamento());
            stmt.setString(3, novaVenda.getDataTransacao().toString());
            stmt.setFloat( 4, novaVenda.getValorTotal());
            stmt.setString(5, novaVenda.getCliente().getId());
            stmt.executeUpdate();
            System.out.println("Cadastro na tabela 'vendas' concluido.");
        }catch (SQLException e){
            e.printStackTrace();
        }

        String idVenda = novaVenda.getId();
        for (Map.Entry<Produto, Integer> entry : novaVenda.getItens().entrySet()){
            String idProduto = entry.getKey().getId();
            int quant = entry.getValue();

            try (PreparedStatement stmt = connection.prepareStatement(sqlItens)){
                stmt.setString(1, idVenda);
                stmt.setObject(2, idProduto);
                stmt.setInt(3, quant);

                stmt.executeUpdate();
                System.out.println("Cadastro na tabela 'ItensVenda' concluido.");
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

    }

    public Venda buscarVendaId(String id){
        String sqlVendas = "SELECT * FROM vendas WHERE id = ?";
        Venda venda = null;
        try (PreparedStatement stmt = connection.prepareStatement(sqlVendas)){
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            ClienteDAO clienteDAO = new ClienteDAO(connection);
            if(rs.next()){
                Cliente cliente = clienteDAO.buscarClienteCPF(rs.getString("cpfCliente"));
                if (cliente != null) {
                    venda = new Venda(
                            rs.getString("id"),
                            cliente,
                            rs.getString("formaPagamento")
                    );
                    venda.setDataTransacao(LocalDate.parse(rs.getString("data")));
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        String sqlItens = "SELECT * FROM itensVenda WHERE idVenda = ?";

        try(PreparedStatement stmt = connection.prepareStatement(sqlItens)){
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            ProdutoDAO produtoDAO= new ProdutoDAO(connection);
            while(rs.next()){
                Produto produto = produtoDAO.buscarProdutoId(rs.getString("idProduto"));
                if(produto != null){
                    assert venda != null;
                    venda.adicionarItem(produto, rs.getInt("quantidade"));
                }
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }


        return venda;
    }

    public List<Venda> listarVendas(){
        String sqlVendas = "SELECT * FROM vendas";
        String sqlItens = "SELECT * FROM itensVenda WHERE idVenda = ?";
        ArrayList<Venda> lista = new ArrayList<>();
        Venda venda = null;
        ClienteDAO clienteDAO = new ClienteDAO(connection);

        try(PreparedStatement stmt = connection.prepareStatement(sqlVendas)){
            ResultSet rs = stmt.executeQuery();
            Cliente cliente = null;
            while (rs.next()){
                cliente = clienteDAO.buscarClienteCPF(rs.getString("cpfCliente"));
                venda = new Venda(
                        rs.getString("id"),
                        cliente,
                        rs.getString("formaPagamento")
                );
                venda.setDataTransacao(LocalDate.parse(rs.getString("data")));

                try(PreparedStatement stmt2 = connection.prepareStatement(sqlItens)){
                    stmt2.setString(1, venda.getId());
                    ResultSet rs2 = stmt2.executeQuery();
                    ProdutoDAO produtoDAO= new ProdutoDAO(connection);
                    while(rs2.next()){
                        Produto produto = produtoDAO.buscarProdutoId(rs2.getString("idProduto"));
                        if(produto != null){
                            venda.adicionarItem(produto, rs2.getInt("quantidade"));
                        }
                    }

                }catch (SQLException e) {
                    e.printStackTrace();
                }

                lista.add(venda);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }


        return lista;
    }

    public void removerVenda(String id){
        String sqlItens = "DELETE FROM itensVenda WHERE idVenda = ?";
        Venda venda = null;

        try(PreparedStatement stmt = connection.prepareStatement(sqlItens)){
            stmt.setString(1, id);
            stmt.executeUpdate();
            System.out.println("Registros da venda removidos da tabela 'ItensVenda'");

        }catch (SQLException e) {
            e.printStackTrace();
        }

        String sqlVendas = "DELETE FROM vendas WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sqlVendas)){
            stmt.setString(1, id);
            stmt.executeUpdate();
            System.out.println("Venda removida da tabela 'vendas' com sucesso");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void deletarTabelasVendas(){
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("DROP TABLE vendas");
            System.out.println("Tabela 'vendas' deletada");
            stmt.execute("DROP TABLE itensVenda");
            System.out.println("Tabela 'itensVenda' deletada");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
