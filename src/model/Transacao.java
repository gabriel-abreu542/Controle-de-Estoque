package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Transacao implements Cadastravel{
    protected String id;
    ArrayList<ItemTransacao> itens;
    protected String formaPagamento;
    protected LocalDate dataTransacao;
    protected float valorTotal;

    public Transacao(String id, String f){
        this.id = id;
        itens = new ArrayList<>();
        dataTransacao = LocalDate.now();
        valorTotal = 0;
        formaPagamento = f;
    }

    @Override
    public String getId() {
        return id;
    }

    public void adicionarItem(ItemTransacao item){
        if (item.getQuantidade() <= 0){
            throw new IllegalArgumentException("Quantidade deve ser maior que zero");
        }
        itens.add(item);
    }

    public void removerItem(Produto p, int quantidade){
        boolean naLista = false;
        ItemTransacao removido = null;
        for(ItemTransacao i : itens){
            if(i.getProduto() == p){
                naLista = true;
                removido = i;
            }
        }
        if(!naLista) {
            throw new IllegalArgumentException("Item a ser removido não está na lista");
        }
        int disponivel = removido.getQuantidade();
        if (quantidade > disponivel){
            throw new IllegalArgumentException("Quantidade removida deve ser menor que a quantidade na lista");
        } else if (quantidade < disponivel) {
            removido.setQuantidade(disponivel - quantidade);
            valorTotal = valorTotal - quantidade * removido.getPrecoUnitario(); //atualiza valor da compra
        }
        else{
            valorTotal -= removido.getSomaParcial(); //atualiza valor da transacao
            itens.remove(removido);
        }

    }

    public LocalDate getDataTransacao() {
        return dataTransacao;
    }

    public void setDataTransacao(LocalDate dataTransacao) {
        this.dataTransacao = dataTransacao;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public ArrayList<ItemTransacao> getItens() {
        return itens;
    }

    @Override
    public String toString() {
        StringBuilder detalhes = new StringBuilder("Venda:\n");
        detalhes.append("Data: ").append(dataTransacao).append("\n");
        detalhes.append("Itens:\n");
        for (ItemTransacao i : itens) {
            detalhes.append(i.getProduto().getNome())
                    .append(" - Quantidade: ").append(i.getQuantidade())
                    .append(" - Total: ").append(i.getPrecoUnitario() * i.getSomaParcial())
                    .append("\n");
        }
        detalhes.append("Forma de pagamento: ").append(formaPagamento).append("\n");
        detalhes.append("Valor total: ").append(valorTotal).append("\n");
        return detalhes.toString();
    }
}
