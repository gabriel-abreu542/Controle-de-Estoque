import java.util.HashMap;

public class Loja {
    private String nome;
    private String endereco;
    private boolean matriz;
    private final Estoque estoque;

    public Loja(String n, String end, boolean ehMatriz){
        nome = n;
        endereco = end;
        matriz = ehMatriz;
        estoque = new Estoque();
    }

    public void Compra(Fornecedor f, HashMap<Produto, Integer> itens){
        // realizar compra de itens e adicionar no estoque da loja
    }

    public String getNome(){
        return this.nome;
    }

    public String getEndereco(){
        return this.endereco;
    }

    public boolean ehMatriz() {
        return matriz;
    }

    public Estoque getEstoque() {
        return estoque;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setMatriz(boolean matriz) {
        this.matriz = matriz;
    }

}
