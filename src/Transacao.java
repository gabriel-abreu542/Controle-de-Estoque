import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Transacao {
    protected Map<Produto, Integer> itens;
    protected Pagamento formaPagamento;
    protected LocalDate dataTransacao;
    protected float valorTotal;

    public Transacao(Pagamento f){
        itens = new HashMap<>();
        dataTransacao = LocalDate.now();
        valorTotal = 0;
        formaPagamento = f;
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

    public Pagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(Pagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Map<Produto, Integer> getItens() {
        return itens;
    }
}
