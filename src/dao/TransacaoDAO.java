package dao;

import model.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

public abstract class TransacaoDAO extends ObjetoDAO<Transacao>{
    protected String tabelaItens;
    protected String sqlCriarItens;
    protected String sqlBuscarItens;
    protected String sqlInserirItem;
    protected String sqlRemoverDeItens;
    protected ObjetoDAO objetoDAO;


    public TransacaoDAO(Connection conn) {
        super(conn);
        setSQLItens();
    }

    public abstract void setSQLItens();

    @Override
    public abstract Transacao buscarNaTabela(ResultSet rs) throws SQLException;

    public void criarTabela(){
        try (PreparedStatement stmt = connection.prepareStatement(sqlCriar)){
            stmt.execute();
            System.out.println("Tabela '" + tabela + "' criada ou ja existe");
        }catch (SQLException e){
            System.out.println("Erro ao criar a tabela '" + tabela + "'");
            e.printStackTrace();
        }
        try (PreparedStatement stmt = connection.prepareStatement(sqlCriarItens)){
            stmt.execute();
            System.out.println("Tabela '" + tabelaItens + "' criada ou ja existe");
        }catch (SQLException e){
            System.out.println("Erro ao criar a tabela '" + tabelaItens + "'");
            e.printStackTrace();
        }

    }


    public void inserir(Transacao objeto){
        //insere nova Transacao na tabela ('vendas' ou 'compras')
        try (PreparedStatement stmt = connection.prepareStatement(sqlInserir)){
            configurarParametrosInsercao(stmt, objeto);
            stmt.executeUpdate();
            System.out.println("Inserção na tabela '" + tabela + "' feita com sucesso");
        }catch (SQLException e){
            System.out.println("Erro ao inserir na tabela'" + tabela + "'");
            e.printStackTrace();
        }

        String idTransacao = objeto.getId();

        //insere informações dos itens da Transacao com o id da Transacao na tabela de itens
        for (Map.Entry<Produto, Integer> entry : objeto.getItens().entrySet()){
            String idProduto = entry.getKey().getId();
            int quant = entry.getValue();

            try (PreparedStatement stmt = connection.prepareStatement(sqlInserirItem)){
                stmt.setString(1, idTransacao);
                stmt.setObject(2, idProduto);
                stmt.setInt(3, quant);

                stmt.executeUpdate();
                System.out.println("Cadastro na tabela " + tabelaItens + "' concluido.");
            }catch (SQLException e){
                e.printStackTrace();
                System.out.println("Erro ao inserir na tabela '" + tabelaItens + "'");
            }
        }
    }



    @Override
    public Transacao buscarPorId(String id) {
        Transacao T = null;
        try (PreparedStatement stmt = connection.prepareStatement(sqlBuscar)){
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next())
                T = buscarNaTabela(rs);
        }catch(SQLException e){
            e.printStackTrace();
        }

        try(PreparedStatement stmt = connection.prepareStatement(sqlBuscarItens)){
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            ProdutoDAO produtoDAO = new ProdutoDAO(connection);
            while(rs.next()){
                Produto produto = produtoDAO.buscarPorId(rs.getString("idProduto"));
                if(produto != null){
                    assert T != null;
                    T.adicionarItem(produto, rs.getInt("quantidade"));
                }
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }


        return T;
    }

    @Override
    public ArrayList<Transacao> listarTodos() {
        String sqlVendas = "SELECT * FROM " + tabela;
        ArrayList<Transacao> lista = new ArrayList<>();
        Transacao T = null;

        try(PreparedStatement stmt = connection.prepareStatement(sqlVendas)){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                T = buscarNaTabela(rs);
                T.setDataTransacao(LocalDate.parse(rs.getString("data")));
                String idTransacao = T.getId();

                try(PreparedStatement stmt2 = connection.prepareStatement(sqlBuscarItens)){
                    stmt2.setString(1, idTransacao);
                    ResultSet rs2 = stmt2.executeQuery();
                    ProdutoDAO produtoDAO = new ProdutoDAO(connection);
                    while(rs2.next()){
                        Produto produto = produtoDAO.buscarPorId(rs2.getString("idProduto"));
                        if(produto != null){
                            T.adicionarItem(produto, rs2.getInt("quantidade"));
                        }
                    }

                }catch (SQLException e) {
                    e.printStackTrace();
                }

                lista.add(T);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }


        return lista;
    }

    @Override
    public void atualizar(Transacao objeto) {
        String sqlUpdate = "UPDATE vendas SET formaPagamento = ?, data = ?, total = ?, cpfCliente = ?, WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sqlUpdate)) {
            configurarParametrosAtualizacao(stmt, objeto);
            stmt.executeUpdate();
            System.out.println("Instância em '" + tabela + "' atualizada com sucesso.");
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("Erro ao atualizar registro na tabela '" + tabela + "'");
        }
    }

    @Override
    public void remover(String id) {
        try(PreparedStatement stmt = connection.prepareStatement(sqlRemoverDeItens)){
            stmt.setString(1, id);
            stmt.execute();
            System.out.println("Registros da transacao removidos da tabela '" + tabelaItens + "'");
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro na remoção de registro em '" + tabelaItens + "'");
        }

        try (PreparedStatement stmt = connection.prepareStatement(sqlRemover)){
            stmt.setString(1, id);
            stmt.execute();
            System.out.println("Transacao removida da tabela '" + tabela +"' com sucesso");
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Erro na remoção de registro em '" + tabela + "'");
        }
    }

    @Override
    public void deletarTabela() {
        String sqlTabela = "DROP TABLE " + tabela;
        String sqlItens = "DROP TABLE " + tabelaItens;
        try (PreparedStatement stmt = connection.prepareStatement(sqlItens)) {
            stmt.execute();
            System.out.println("Tabela " + tabela + " deletada");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao deletar a tabela '" + tabela + "'");
        }

        try (PreparedStatement stmt = connection.prepareStatement(sqlTabela)){
            stmt.execute();
            System.out.println("Tabela " + tabelaItens + " deletada");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao deletar a tabela '" + tabelaItens + "'");
        }
    }
}
