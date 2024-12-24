import java.util.HashMap;

public class Estoque {
    private HashMap<Produto, Integer> estoque;

    Estoque(){
        estoque = new HashMap<>();
    }

    public HashMap<Produto, Integer> getEstoque() {
        return estoque;
    }

    public Produto buscarProduto(String nome){
        for (Produto p : estoque.keySet()){
            if(nome.equalsIgnoreCase(p.getNome())){
                return p;
            }
        }
        return null;
    }

    public void adicionarProduto(Produto p, int quantidade) {
        if (quantidade < 0) {
            throw new IllegalArgumentException("Quantidade não pode ser negativa");
        }
        // Se o produto já existe no estoque, soma a quantidade
        estoque.put(p, estoque.getOrDefault(p, 0) + quantidade);
    }


    public boolean removerProduto(Produto p, int quantidade) throws RuntimeException {
        if(!estoque.containsKey(p)){
            return false;
        }
        int q = estoque.get(p);
        if(q < quantidade){
            throw new RuntimeException("Quantidade insuficiente do produto: " + p.getNome());
        }
        else if(q == quantidade){ //Removendo a quantidade exata do produto do estoque
            System.out.println("Alerta: Após essa remoção, não há mais estoque do produto: " + p.getNome());
            estoque.remove(p);
        }
        else{
            estoque.put(p, estoque.get(p) - quantidade);
        }

        return true;
    }
}
