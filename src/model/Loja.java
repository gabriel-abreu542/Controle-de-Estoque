package model;

import java.util.Map;

public class Loja {
    private String nome;
    private String endereco;
    private boolean matriz;
    private final Estoque estoque;
    private final HistoricoTransacoes historico;

    public Loja(String n, String end, boolean ehMatriz){
        nome = n;
        endereco = end;
        matriz = ehMatriz;
        estoque = new Estoque();
        historico = new HistoricoTransacoes();
    }

    // Objeto Compra será "cadastrado" em uma tela antes de ser passado para essa função
    public boolean realizarCompra(Compra compra){
        if(compra.getItens().isEmpty()){
            System.out.println("Compra não possui nenhum item");
            return false;
        }

        for (Map.Entry<Produto, Integer> entry : compra.getItens().entrySet()){
            estoque.adicionarProduto(entry.getKey(), entry.getValue());
        }

        historico.adicionarCompra(compra);
        System.out.println("Compra realizada com sucesso");
        return true;
    }

    // Objeto Venda será "cadastrado" em uma tela antes de ser passado para essa função
    public boolean realizarVenda(Venda venda){
        if(venda.getItens().isEmpty()){
            System.out.println("Venda não possui nenhum item");
            return false;
        }

        for (Map.Entry<Produto, Integer> entry: venda.getItens().entrySet()){
            Produto produto = entry.getKey();
            Integer quantidade = entry.getValue();

            try{
                boolean removido = estoque.removerProduto(produto, quantidade);
                if(!removido){
                    System.out.println("Produto " + produto.getNome() + " não encontrado no estoque");
                    return false;
                }

            }catch (RuntimeException e){
                System.out.println("Falha ao realizar a Venda: " + e.getMessage());
                return false;
            }

        }

        historico.adicionarVenda(venda);
        System.out.println("Venda realizada com sucesso");
        return true;
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

    public HistoricoTransacoes getHistorico() {
        return historico;
    }
}
