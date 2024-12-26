package service;

import model.Usuario;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CadastroUsuários implements Cadastro<Usuario> {
    private List<Usuario> registros = new ArrayList<>(); //substituir por chamada ao Banco de Dados

    @Override
    public void criar(Usuario usuario) {
        registros.add(usuario);
    }

    @Override
    public Usuario buscarPorId(String id) {
        return registros.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Usuario> listarTodos() {
        return new ArrayList<>(registros);
    }

    @Override
    public boolean atualizar(Usuario item) {
        return false;
    }

    @Override
    public void deletar(String id) {
        registros.removeIf(item -> item.getId().equals(id));
    }
}
