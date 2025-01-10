package model;

public class Produto implements Cadastravel{
    public String id;
    private String nome;
    private String desc;
    private String tipo;
    private float precoCompra;
    private float precoVenda;

    public Produto(String id, String n, float pCompra, float pVenda){
        this.id = id;
        nome = n;
        desc = "";
        tipo = "";
        precoCompra = pCompra;
        precoVenda = pVenda;
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

    public float getPrecoCompra(){
        return this.precoCompra;
    }

    public float getPrecoVenda(){
        return this.precoVenda;
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

    public void setPrecoCompra(float p){
        precoCompra = p;
    }

    public void setPrecoVenda(float p){
        precoVenda = p;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id= " + id + '\'' +
                "nome='" + nome + '\'' +
                ", desc='" + desc + '\'' +
                ", tipo='" + tipo + '\'' +
                ", precoCompra=" + precoCompra +
                ", precoVenda=" + precoVenda +
                '}';
    }
}
