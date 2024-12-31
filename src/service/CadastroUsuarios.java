package service;

import dao.UsuarioDAO;
import model.Usuario;

import java.util.List;

public class CadastroUsuarios implements Cadastro<Usuario> {
    private final UsuarioDAO usuarioDAO;

    public CadastroUsuarios(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    @Override
    public void adicionar(Usuario usuario) {
        if(usuario.getNome() == null || usuario.getNome().length() < 3){
                throw new IllegalArgumentException("Nome do usuario deve ter no mínimo 3 caracteres");
        }

        // talvez incluir regra de negocio para senha (incluir letras e numeros, minimo de caracteres, etc)
        // usar regex na implementacao

        Usuario u = usuarioDAO.buscarPorId(usuario.getId());
        if (u == null){
            throw new IllegalArgumentException("Já existe um usuário com esse Id");
        }


        usuarioDAO.inserir(usuario);
    }

    @Override
    public Usuario buscarPorId(String id) {
        return usuarioDAO.buscarPorId(id);
    }

    @Override
    public List<Usuario> listarTodos() {

        return usuarioDAO.listarTodos();
    }

    @Override
    public void atualizar(Usuario usuario) {
        String id = usuario.getId();
        Usuario u = usuarioDAO.buscarPorId(id);

        if (u == null){
            System.out.println("Nenhum usuario encontrado com o id: " + id);
        }
        else{
            usuarioDAO.atualizar(usuario);
        }

    }

    @Override
    public void deletar(String id) {
        Usuario u = usuarioDAO.buscarPorId(id);

        if (u == null){
            System.out.println("Nenhum usuario encontrado com o id: " + id);
        }
        else{
            usuarioDAO.remover(id);
        }

    }
}
