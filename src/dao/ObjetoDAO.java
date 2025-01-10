package dao;

import model.Cadastravel;
import model.Produto;

import java.sql.*;
import java.util.ArrayList;

public abstract class ObjetoDAO<T extends Cadastravel> {
    protected final Connection connection;
    protected String tabela;
    protected String sqlCriar;
    protected String sqlInserir;
    protected String sqlRemover;
    protected String sqlUpdate;
    protected String sqlBuscar;
    protected String sqlBuscarNome;

    public ObjetoDAO(Connection conn){
        connection = conn;
        setSQL();
    }

    public abstract void setSQL();

    public abstract void configurarParametrosInsercao(PreparedStatement stmt, T objeto) throws SQLException;

    public abstract void configurarParametrosAtualizacao(PreparedStatement stmt, T objeto) throws SQLException;

    public String ultimoId(){
        String ultimo = "Objeto000";
        try (PreparedStatement stmt = connection.prepareStatement("SELECT MAX(id) FROM " + tabela)){
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                ultimo = rs.getString(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("Erro ao buscar ultimo id em '" + tabela + "'");
        }
        if(ultimo == null) {
            ultimo = "Objeto000";
        }
        return ultimo;
    }

    public abstract T buscarNaTabela(ResultSet rs) throws SQLException;

    public void criarTabela(){
        try (PreparedStatement stmt = connection.prepareStatement(sqlCriar)){
            stmt.execute();
            System.out.println("Tabela '" + tabela + "' criada ou ja existe");
        }catch (SQLException e){
            System.out.println("sqlCriar: " + sqlCriar);
            System.out.println("Erro ao criar a tabela '" + tabela + "'");
            e.printStackTrace();
        }
    }

    public void inserir(T objeto){
        try (PreparedStatement stmt = connection.prepareStatement(sqlInserir)){
            configurarParametrosInsercao(stmt, objeto);
            stmt.executeUpdate();
            System.out.println("Inserção na tabela '" + tabela + "' feita com sucesso");
        }catch (SQLException e){
            System.out.println("Erro ao inserir na tabela'" + tabela + "'");
            e.printStackTrace();
        }
    }

    public ArrayList<T> listarTodos(){
        String sql = "SELECT * FROM " + tabela;
        ArrayList<T> lista = new ArrayList<>();
        T objeto = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            System.out.println("Stmt: " + stmt.toString());
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                objeto = buscarNaTabela(rs);
                lista.add(objeto);
            }
        }catch (SQLException e){
            System.out.println("Erro ao listar registros da tabela '" + tabela + "'");
            e.printStackTrace();
        }
        return lista;
    }

    public ArrayList<String> ListarPorParametro(String param){
        String sql = "SELECT " + param + " FROM " + tabela + ";";
        ArrayList<String> lista = new ArrayList<>();
        String objeto = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            System.out.println("Stmt: " + stmt.toString());
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                objeto = rs.getString(1);
                lista.add(objeto);
            }
        }catch (SQLException e){
            System.out.println("Erro ao listar registros da tabela '" + tabela + "'");
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
            throw new IllegalArgumentException("Erro ao buscar objeto na tabela '" + tabela + "'");
        }

        return objeto;
    }

    public T buscarPorNome(String id){

        T objeto = null;

        try (PreparedStatement stmt = connection.prepareStatement(sqlBuscarNome)){
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                objeto = buscarNaTabela(rs);
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw new IllegalArgumentException("Erro ao buscar objeto na tabela '" + tabela + "'");
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
            System.out.println("Erro ao atualizar registro na tabela '" + tabela + "'");
        }
    }

    public void remover(String id){
        try (PreparedStatement stmt = connection.prepareStatement(sqlRemover)) {
            stmt.setString(1, id);
            System.out.println("Stmt: " + stmt.toString());
            stmt.executeUpdate();
            System.out.println("Instância em '" + tabela + "' removida com sucesso.");
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("Erro ao remover registro da tabela '" + tabela + "'");
        }
    }

    public void deletarTabela(){
        String sql = "DROP TABLE " + tabela;
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.execute();
            System.out.println("Tabela '" + tabela + "' deletada");
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("Erro ao deletar a tabela '" + tabela + "'");
        }
    }

    public String getTabela() {
        return tabela;
    }
}
