package model;

import java.util.Map;

public class Venda extends Transacao{
    private Cliente cliente;

    public Venda(String id, Cliente cli, String formaP){
        super(id, formaP);
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
        StringBuilder detalhes = new StringBuilder("Venda:\n");
        detalhes.append(cliente.toString());
        detalhes.append("Data: ").append(dataTransacao).append("\n");
        detalhes.append("Itens:\n");
        for (ItemTransacao i : itens) {
            detalhes.append(i.getProduto().getNome())
                    .append(" - Quantidade: ").append(i.getQuantidade())
                    .append(" - Soma Parcial: ").append(i.getSomaParcial())
                    .append("\n");
        }
        detalhes.append("Forma de pagamento: ").append(formaPagamento).append("\n");
        detalhes.append("Valor total: ").append(valorTotal).append("\n");
        return detalhes.toString();
    }
}
