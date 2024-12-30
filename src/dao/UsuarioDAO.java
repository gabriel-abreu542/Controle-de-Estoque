package dao;

import model.Usuario;
import test.dao.ConexaoDBTest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private final Connection connection;

    public UsuarioDAO(Connection conn){
        connection = conn;
    }

    public void criarTabelaUsuarios(){
        String sql = "CREATE TABLE IF NOT EXISTS usuarios ("+
                "id TEXT PRIMARY KEY," +
                "nome TEXT NOT NULL," +
                "senha TEXT NOT NULL," +
                "adm BOOLEAN NOT NULL" +
                ");";

        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.execute();
            System.out.println("Tabela 'usuarios' criada ou ja existe");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void inserirUsuario(Usuario novoUsuario){
        String sql = "INSERT INTO usuarios (id,nome,senha,adm) VALUES (?,?,?,?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, novoUsuario.getId());
            stmt.setString(2, novoUsuario.getNome());
            stmt.setString(3, novoUsuario.getSenha());
            stmt.setBoolean( 4, novoUsuario.isAdm());
            stmt.executeUpdate();
            System.out.println("Usuario inserido com sucesso.");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<Usuario> listarUsuarios(){
        String sql = "SELECT * FROM usuarios";
        Usuario usuario = null;
        ArrayList<Usuario> lista = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){

                usuario = new Usuario(
                        rs.getString("id"),
                        rs.getString("nome"),
                        rs.getString("senha"),
                        rs.getBoolean("adm")
                );
                lista.add(usuario);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return lista;
    }

    public Usuario buscarUsuarioId(String id){
        String sql = "SELECT * FROM usuarios WHERE id = ?";
        Usuario usuario = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                usuario = new Usuario(
                        rs.getString("id"),
                        rs.getString("nome"),
                        rs.getString("senha"),
                        rs.getBoolean("adm")
                );

            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return usuario;
    }

    public void atualizarUsuario(Usuario usuario){
        String sql = "UPDATE usuarios SET nome = ?, senha = ?, adm = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getSenha());
            stmt.setBoolean(3, usuario.isAdm());
            stmt.setString(4, usuario.getId());
            stmt.executeUpdate();
            System.out.println("Usuario atualizado com sucesso.");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void removerUsuario(String id){
        String sql = "DELETE FROM usuarios WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
            System.out.println("Usuario removido com sucesso.");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void deletarTabelaUsuarios(){
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("DROP TABLE usuarios");
            System.out.println("Tabela 'usuarios' deletada");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
