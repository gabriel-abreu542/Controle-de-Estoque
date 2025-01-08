package service;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.RegularExpression;
import dao.ConexaoDB;
import dao.UsuarioDAO;
import model.Usuario;

import java.sql.SQLException;

public class CadastroUsuarios extends Cadastro<Usuario>{
    private static int contadorId;

    public CadastroUsuarios() throws SQLException{
        super();
        dao.criarTabela();
        contadorId = Integer.parseInt(dao.ultimoId().substring(7));
        System.out.println(dao.ultimoId().substring(7));
    }

    @Override
    public void setDAO() throws SQLException {
        this.dao = new UsuarioDAO(ConexaoDB.getConnection());
    }

    public String nextId() {
        contadorId++;
        String id = contadorId > 99 ? String.format("%03d", contadorId) : String.valueOf(contadorId);;
        return "Usuario" + String.format("%03d", contadorId);
    }

    public Usuario criarUsuario(String nome, String senha, boolean adm){
        String id = nextId();
        Usuario novoUsuario = new Usuario(id, nome, senha, adm);
        cadastrar(novoUsuario);

        return novoUsuario;
    }

    public Usuario Login(String nome, String senha){
        return ((UsuarioDAO) dao).buscarNomeSenha(nome,senha);
    }

    @Override
    public boolean regraInsercao(Usuario item) throws IllegalArgumentException {
        String nome = item.getNome();
        String senha = item.getSenha();
        if(nome == null || nome.length() < 3){
            throw new IllegalArgumentException("Nome do usuario deve ter no mínimo 3 caracteres");
        }

        //Aceita apenas senhas de no mínimo 4 caracteres
        RegularExpression regEx = new RegularExpression("^[a-zA-z\\d]{4,}$");
        if(!regEx.matches(senha)){
            throw new IllegalArgumentException("Senha deve ter no mínimo 4 caracteres e possuir letras e números");
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
