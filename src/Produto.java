public class Produto {
    private String nome;
    private String desc;
    private String tipo;
    private float precoCompra;
    private float precoVenda;
    private int quantidade;

    public Produto(String n, float pCompra, float pVenda, int quant){
        nome = n;
        desc = "Item sem descrição";
        precoCompra = pCompra;
        precoVenda = pVenda;
        quantidade = quant;
    }

    public void adicionarItem(int quant){
        if(quant < 0){
            throw new RuntimeException("Adição de quantidade negativa");
        }
        else{
            quantidade = quantidade + quant;
        }
    }

    public void removerItem(int quant){
        if(quant > quantidade){
            throw new RuntimeException("Quantidade não disponível");
        }
        else{
            quantidade = quantidade - quant;
        }
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

    public int getQuantidade(){
        return this.quantidade;
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

    public void setQuantidade(int q){
        quantidade = q;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "nome='" + nome + '\'' +
                ", desc='" + desc + '\'' +
                ", tipo='" + tipo + '\'' +
                ", precoCompra=" + precoCompra +
                ", precoVenda=" + precoVenda +
                ", quantidade=" + quantidade +
                '}';
    }
}
