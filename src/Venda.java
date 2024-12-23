import java.time.LocalDate;
import java.util.Map;
import java.util.HashMap;

public class Venda {

    private Cliente cliente;
    private Map<Produto, Integer> itens;
    private Pagamento formaPagamento;
    private LocalDate dataVenda;
    private float valorTotal;


    public Venda(Cliente cli, Produto p){
        cliente = cli;
        itens = new HashMap<>();
        this.dataVenda = LocalDate.now(); //dia atual
        valorTotal = 0; //Valor calculado após a adição dos itens
    }

    public void adicionarItem(Produto p, int quantidade){
        if (quantidade <= 0){
            throw new IllegalArgumentException("Quantidade deve ser maior que zero");
        }
        itens.put(p, itens.getOrDefault(p, 0) + quantidade);

        valorTotal = valorTotal + itens.get(p) * p.getPrecoVenda(); //atualiza valor da compra
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
        }
        else{
            itens.remove(p);
        }
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Pagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(Pagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    @Override
    public String toString() {
        StringBuilder detalhes = new StringBuilder("Venda realizada:\n");
        detalhes.append("Cliente: ").append(cliente.toString()).append("\n");
        detalhes.append("Data: ").append(dataVenda).append("\n");
        detalhes.append("Itens:\n");
        for (Map.Entry<Produto, Integer> entry : itens.entrySet()) {
            detalhes.append(entry.getKey().getNome())
                    .append(" - Quantidade: ").append(entry.getValue())
                    .append(" - Total: ").append(entry.getKey().getPrecoVenda() * entry.getValue())
                    .append("\n");
        }
        detalhes.append("Forma de pagamento: ").append(formaPagamento).append("\n");
        detalhes.append("Valor total: ").append(valorTotal).append("\n");
        return detalhes.toString();
    }
}
