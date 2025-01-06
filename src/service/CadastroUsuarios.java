package service;

import dao.ConexaoDB;
import dao.UsuarioDAO;
import model.Usuario;

import java.sql.SQLException;

public class CadastroUsuarios extends Cadastro<Usuario> {
    int idCounter;

    public CadastroUsuarios() throws SQLException{
        super();
        idCounter = 0;
    }

    @Override
    public void setDAO() throws SQLException {
        this.dao = new UsuarioDAO(ConexaoDB.getConnection());
    }

    public String gerarNovoID() {
        idCounter++;
        return String.valueOf(idCounter);
    }

    public Usuario Login(String nome, String senha){
        this.dao = (UsuarioDAO)dao;

        return ((UsuarioDAO) dao).buscarNomeSenha(nome,senha);
    }

    @Override
    public boolean regraInsercao(Usuario item) throws IllegalArgumentException {
        if(item.getNome() == null || item.getNome().length() < 3){
            throw new IllegalArgumentException("Nome do usuario deve ter no mínimo 3 caracteres");
        }
        Usuario u = dao.buscarPorId(item.getId());
        if (u != null){
            throw new IllegalArgumentException("Já existe um usuário com esse Id");
        }

        return true;
    }

    @Override
    public boolean regraAtualizacao(Usuario item) throws IllegalArgumentException {
        if(item.getNome() == null || item.getNome().length() < 3){
            throw new IllegalArgumentException("Nome do usuario deve ter no mínimo 3 caracteres");
        }

        String id = item.getId();

        Usuario anterior = dao.buscarPorId(id);
        if(anterior == null){
            System.out.println("Nenhum usuario encontrado com o id " + id);
            return false;
        }

        return true;
    }
}
