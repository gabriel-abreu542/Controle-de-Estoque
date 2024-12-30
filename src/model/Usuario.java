package model;

public class Usuario implements Cadastravel {
    private final String id;
    private String nome;
    private String senha;
    private boolean adm;

    public Usuario(String i, String n, String s, boolean adm){
        id = i;
        nome = n;
        senha = s;
        this.adm = adm;
    }

    public String getNome() {
        return nome;
    }

    public String getId() {
        return id;
    }

    public String getSenha() {
        return senha;
    }

    public boolean isAdm() {
        return adm;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setAdm(boolean adm) {
        this.adm = adm;
    }
}
