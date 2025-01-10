package model;

public class Produto implements Cadastravel{
    public String id;
    private String nome;
    private String desc;
    private String tipo;

    public Produto(String id, String n){
        this.id = id;
        nome = n;
        desc = "";
        tipo = "";
    }

    @Override
    public String getId() {
        return id;
    }

    public String getNome(){
        return this.nome;
    }

    public String getDesc(){
        return this.desc;
    }

    public String getTipo(){
        return this.tipo;
    }

    public void setNome(String n){
        nome = n;
    }

    public void setDesc(String d){
        desc = d;
    }

    public void setTipo(String t){
        tipo = t;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id= " + id + '\'' +
                "nome='" + nome + '\'' +
                ", desc='" + desc + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
