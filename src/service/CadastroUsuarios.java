package service;

import dao.UsuarioDAO;
import model.Usuario;

import java.util.List;

public class CadastroUsuarios extends Cadastro<Usuario> {

    public CadastroUsuarios(UsuarioDAO usuarioDAO) {
        super(usuarioDAO);
    }

    @Override
    public boolean regraInsercao(Usuario item) throws IllegalArgumentException {
        if(item.getNome() == null || item.getNome().length() < 3){
            throw new IllegalArgumentException("Nome do usuario deve ter no mínimo 3 caracteres");
        }
        Usuario u = dao.buscarPorId(item.getId());
        if (u == null){
            throw new IllegalArgumentException("Já existe um usuário com esse Id");
        }

        return true;
    }

    @Override
    public boolean regraAtualizacao(Usuario item) throws IllegalArgumentException {
        if(item.getNome() == null || item.getNome().length() < 3){
            throw new IllegalArgumentException("Nome do usuario deve ter no mínimo 3 caracteres");
        }

        return true;
    }
}
