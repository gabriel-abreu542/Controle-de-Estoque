import java.util.Map;

public class Compra extends Transacao{
    private Fornecedor fornecedor;


    public Compra(Fornecedor f, Pagamento formaP){
        super(formaP);
        fornecedor = f;
    }

    public Fornecedor getFornecedor(){
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    @Override
    public String toString() {
        StringBuilder detalhes = new StringBuilder("Venda realizada:\n");
        detalhes.append("Fornecedor: ").append(fornecedor).append("\n");
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
