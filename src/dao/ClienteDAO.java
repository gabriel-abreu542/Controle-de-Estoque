package dao;

import model.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    private final Connection connection;

    public ClienteDAO(Connection conn){
        connection = conn;
    }

    public void criarTabelaClientes(){
        String sql = "CREATE TABLE IF NOT EXISTS clientes ("+
                "cpf TEXT PRIMARY KEY," +
                "nome TEXT NOT NULL," +
                "telefone TEXT NOT NULL," +
                "endividado BOOLEAN NOT NULL," +
                "divida REAL" +
                ");";

        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.execute();
            System.out.println("Tabela 'clientes' criada ou ja existe");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void inserirCliente(Cliente novoCliente){
        String sql = "INSERT INTO clientes (cpf,nome,telefone,endividado,divida) VALUES (?,?,?,?,?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, novoCliente.getId());
            stmt.setString(2, novoCliente.getNome());
            stmt.setString(3, novoCliente.getTelefone());
            stmt.setBoolean( 4, novoCliente.temDivida());
            stmt.setFloat(5, novoCliente.getDivida());
            stmt.executeUpdate();
            System.out.println("Cliente inserido com sucesso.");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<Cliente> listarClientes(){
        String sql = "SELECT * FROM clientes";
        Cliente cliente = null;
        ArrayList<Cliente> lista = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){

                cliente = new Cliente(
                        rs.getString("cpf"),
                        rs.getString("nome"),
                        rs.getString("telefone")
                );
                boolean endividado = rs.getBoolean("endividado");
                float divida = rs.getFloat("divida");

                if(endividado && divida > 0){
                    cliente.setDivida(divida);
                }

                lista.add(cliente);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return lista;
    }

    public Cliente buscarClienteCPF(String cpf){
        String sql = "SELECT * FROM clientes WHERE cpf = ?";
        Cliente cliente = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                cliente = new Cliente(
                        rs.getString("cpf"),
                        rs.getString("nome"),
                        rs.getString("telefone")
                );
                boolean endividado = rs.getBoolean("endividado");
                float divida = rs.getFloat("divida");

                if(endividado && divida > 0){
                    cliente.setDivida(divida);
                }

            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return cliente;
    }

    public void atualizarCliente(Cliente cliente){
        String sql = "UPDATE clientes SET nome = ?, telefone = ?, endividado = ?, divida = ? WHERE cpf = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getTelefone());
            stmt.setBoolean(3, cliente.temDivida());
            stmt.setFloat(4, cliente.getDivida());
            stmt.setString(5, cliente.getId());
            stmt.executeUpdate();
            System.out.println("Cliente atualizado com sucesso.");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void removerCliente(String cpf){
        String sql = "DELETE FROM clientes WHERE cpf = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            stmt.executeUpdate();
            System.out.println("Cliente removido com sucesso.");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void deletarTabelaClientes(){
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("DROP TABLE clientes");
            System.out.println("Tabela 'clientes' deletada");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
