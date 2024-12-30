package model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Transacao implements Cadastravel{
    protected String id;
    protected Map<Produto, Integer> itens;
    protected String formaPagamento;
    protected LocalDate dataTransacao;
    protected float valorTotal;

    public Transacao(String id, String f){
        this.id = id;
        itens = new HashMap<>();
        dataTransacao = LocalDate.now();
        valorTotal = 0;
        formaPagamento = f;
    }

    @Override
    public String getId() {
        return id;
    }

    public void adicionarItem(Produto p, int quantidade){
        if (quantidade <= 0){
            throw new IllegalArgumentException("Quantidade deve ser maior que zero");
        }
        itens.put(p, itens.getOrDefault(p, 0) + quantidade);

        valorTotal += itens.get(p) * p.getPrecoVenda(); //atualiza valor da transacao
    }

    public void removerItem(Produto p, int quantidade){
        int q = itens.getOrDefault(p, 0);
        if(q == 0) {
            throw new IllegalArgumentException("Item a ser removido não está na venda");
        }
        if (quantidade > q){
            throw new IllegalArgumentException("Quantidade removida deve ser menor que a quantidade da venda");
        } else if (quantidade < q) {
            itens.put(p, q - quantidade);
            valorTotal = valorTotal - quantidade * p.getPrecoVenda(); //atualiza valor da compra
        }
        else{
            valorTotal -= itens.get(p) * p.getPrecoVenda(); //atualiza valor da transacao
            itens.remove(p);
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

    public Map<Produto, Integer> getItens() {
        return itens;
    }
}
