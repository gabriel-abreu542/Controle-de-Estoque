import java.util.ArrayList;

public class Estoque {
    ArrayList<Produto> produtos;

    Estoque(){
        produtos = new ArrayList<>();
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    Produto buscarProduto(String nome){
        for(Produto p : produtos){
            if(p.getNome().equalsIgnoreCase(nome)){
                return p;
            }
        }

        return null;
    }

    public void novoProduto(String nome, String desc, float pCompra, float pVenda, int quant){
        Produto p = new Produto(nome,pCompra,pVenda,quant);
        if(!desc.equalsIgnoreCase("")){
            p.setDesc(desc);
        }
        produtos.add(p);
        System.out.println("Novo produto adicionado:\n" + p);
    }

    public void removerProduto(String nome) throws RuntimeException{
        for(Produto p : produtos){
            if(p.getNome().equalsIgnoreCase(nome)){
                produtos.remove(p);
            }
        }

        throw new RuntimeException("Produto '" + nome + "' não encontrado");
    }


}
