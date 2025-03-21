package model;

import java.util.ArrayList;
import java.util.Map;

public class Compra extends Transacao{
    private Fornecedor fornecedor;

    public Compra(String id, Fornecedor f, String formaP){
        super(id, formaP);
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
        StringBuilder detalhes = new StringBuilder("Compra realizada:\n");
        detalhes.append(fornecedor).append("\n");
        detalhes.append("Data: ").append(dataTransacao).append("\n");
        detalhes.append("Itens:\n");
        for (ItemTransacao i : itens) {
            detalhes.append(i.getProduto().getNome())
                    .append(" - Quantidade: ").append(i.getQuantidade())
                    .append(" - Preço Unitario: ").append(i.getPrecoUnitario())
                    .append(" - Soma Parcial: ").append(i.getSomaParcial())
                    .append("\n");
        }
        detalhes.append("Forma de pagamento: ").append(formaPagamento).append("\n");
        detalhes.append("Valor total: ").append(valorTotal).append("\n");
        return detalhes.toString();
    }
}
