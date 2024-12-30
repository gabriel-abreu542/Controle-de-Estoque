package dao;

import model.Fornecedor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FornecedorDAO {
    private final Connection connection;

    public FornecedorDAO(Connection conn){
        connection = conn;
    }

    public void criarTabelaFornecedores(){
        String sql = "CREATE TABLE IF NOT EXISTS fornecedores ("+
                "cnpj TEXT PRIMARY KEY," +
                "nome TEXT NOT NULL," +
                "telefone TEXT NOT NULL," +
                "email TEXT NOT NULL," +
                "endereco TEXT NOT NULL" +
                ");";

        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.execute();
            System.out.println("Tabela 'fornecedores' criada ou ja existe");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void inserirFornecedor(Fornecedor novoFornecedor){
        String sql = "INSERT INTO fornecedores (cnpj,nome,telefone,email,endereco) VALUES (?,?,?,?,?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, novoFornecedor.getId());
            stmt.setString(2, novoFornecedor.getNome());
            stmt.setString(3, novoFornecedor.getTelefone());
            stmt.setString( 4, novoFornecedor.getEmail());
            stmt.setString(5, novoFornecedor.getEndereco());
            stmt.executeUpdate();
            System.out.println("Fornecedor inserido com sucesso.");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<Fornecedor> listarFornecedores(){
        String sql = "SELECT * FROM fornecedores";
        Fornecedor fornecedor = null;
        ArrayList<Fornecedor> lista = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){

                fornecedor = new Fornecedor(
                        rs.getString("nome"), rs.getString("cnpj"),
                        rs.getString("telefone"),
                        rs.getString("email"),
                        rs.getString("endereco")
                );

                lista.add(fornecedor);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return lista;
    }

    public Fornecedor buscarFornecedorCNPJ(String cnpj){
        String sql = "SELECT * FROM fornecedores WHERE cnpj = ?";
        Fornecedor fornecedor = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, cnpj);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                fornecedor = new Fornecedor(
                        rs.getString("cnpj"),
                        rs.getString("nome"),
                        rs.getString("telefone"),
                        rs.getString("email"),
                        rs.getString("endereco")
                );

            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return fornecedor;
    }

    public void atualizarFornecedor(Fornecedor fornecedor){
        String sql = "UPDATE fornecedores SET nome = ?, telefone = ?, email = ?, endereco = ? WHERE cnpj = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getTelefone());
            stmt.setString(3, fornecedor.getEmail());
            stmt.setString(4, fornecedor.getEndereco());
            stmt.setString(5, fornecedor.getId());
            stmt.executeUpdate();
            System.out.println("Fornecedor atualizado com sucesso.");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void removerFornecedor(String cnpj){
        String sql = "DELETE FROM fornecedores WHERE cnpj = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cnpj);
            stmt.executeUpdate();
            System.out.println("Fornecedor removido com sucesso.");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void deletarTabelaFornecedores(){
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("DROP TABLE fornecedores");
            System.out.println("Tabela 'fornecedores' deletada");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
