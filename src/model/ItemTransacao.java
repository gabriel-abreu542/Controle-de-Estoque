package model;

public class ItemTransacao {
    private Produto produto;
    private int quantidade;
    private float precoUnitario;

    public ItemTransacao(Produto produto, int quantidade, float precoUnitario) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario%.2f;
    }

    public Produto getProduto() { return produto; }
    public int getQuantidade() { return quantidade; }
    public float getPrecoUnitario() { return precoUnitario; }
    public float getSomaParcial() { return precoUnitario * quantidade; }

    public void setPrecoUnitario(float precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
