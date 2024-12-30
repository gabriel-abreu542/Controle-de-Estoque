package dao;

import model.Cadastravel;
import model.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class ObjetoDAO<T extends Cadastravel> {
    private final Connection connection;
    private final String tabela;
    private final String sqlCriar;
    private final String sqlInserir;
    private final String sqlRemover;
    private final String sqlUpdate;
    private String sqlBuscar;

    public ObjetoDAO(Connection conn, String t, String sqlC, String sqlI, String sqlR, String sqlU){
        // recebe os comandos SQL para inserção, remoção e atualização de objetos cadastraveis na tabela
        connection = conn;
        tabela = t;
        sqlCriar = sqlC;
        sqlInserir = sqlI;
        sqlRemover = sqlR;
        sqlUpdate = sqlU;
        setSqlBuscar();
    }

    public void setSqlBuscar() {
        this.sqlBuscar = "SELECT * FROM " + tabela + " WHERE id = ?";
    }

    public abstract void configurarParametrosInsercao(PreparedStatement stmt, T objeto);

    public abstract void configurarParametrosRemocao(PreparedStatement stmt, T objeto);

    public abstract void configurarParametrosAtualizacao(PreparedStatement stmt, T objeto);

    public abstract T buscarNaTabela(ResultSet rs);

    public void criarTabela(){
        try (PreparedStatement stmt = connection.prepareStatement(sqlCriar)){
            stmt.execute();
            System.out.println("Tabela '" + tabela + "' criada ou ja existe");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void inserir(T objeto){
        try (PreparedStatement stmt = connection.prepareStatement(sqlInserir)){
            configurarParametrosInsercao(stmt, objeto);
            stmt.executeUpdate();
            System.out.println("Inserção na tabela '" + tabela + "' com sucesso");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public ArrayList<T> listarTodos(){
        String sql = "SELECT * FROM " + tabela;
        ArrayList<T> lista = new ArrayList<>();
        T objeto = null;

        try (PreparedStatement stmt = connection.prepareStatement(sqlBuscar)){
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                objeto = buscarNaTabela(rs);
                lista.add(objeto);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return lista;
    }

    public T buscarPorId(String id){

        T objeto = null;

        try (PreparedStatement stmt = connection.prepareStatement(sqlBuscar)){
            stmt.setString(1, id);

            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                objeto = buscarNaTabela(rs);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return objeto;
    }

    public void atualizar(T objeto){
        try (PreparedStatement stmt = connection.prepareStatement(sqlUpdate)) {
            configurarParametrosAtualizacao(stmt, objeto);
            stmt.executeUpdate();
            System.out.println("Instância em '" + tabela + "' atualizada com sucesso.");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void remover(String id){
        try (PreparedStatement stmt = connection.prepareStatement(sqlRemover)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
            System.out.println("Instância em '" + tabela + "' removida com sucesso.");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void deletarTabela(){
        String sql = "DROP TABLE " + tabela;
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.execute();
            System.out.println("Tabela '" + tabela + "' deletada");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
