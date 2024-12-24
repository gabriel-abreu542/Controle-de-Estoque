import java.time.LocalDate;
import java.util.Map;
import java.util.HashMap;

public class Venda extends Transacao{

    private Cliente cliente;


    public Venda(Cliente cli, Pagamento formaP){
        super(formaP);
        cliente = cli;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public float getValorTotal(){
        return valorTotal;
    }

    @Override
    public String toString() {
        StringBuilder detalhes = new StringBuilder("Venda realizada:\n");
        detalhes.append("Cliente: ").append(cliente.toString());
        detalhes.append("Data: ").append(dataTransacao).append("\n");
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
