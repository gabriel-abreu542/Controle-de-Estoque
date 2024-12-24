import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HistoricoTransacoes {
    private final List<Venda> vendas;
    private final List<Compra> compras;

    HistoricoTransacoes(){
        vendas = new ArrayList<>();
        compras = new ArrayList<>();
    }

    public void adicionarVenda(Venda v){
        vendas.add(v);
    }

    public void adicionarCompra(Compra c){
        compras.add(c);
    }

    // public List<Venda> getVendasCliente(Cliente cli){ }

    // public List<Compra> getComprasFornecedor(Fornecedor f){ }

    // public List<Venda> getVendasData(DateTime d){ }

    // public List<Compra> getComprasData(DateTime d){ }

    public List<Venda> getVendas() {
        return new ArrayList<Venda>(vendas); // retorna uma cópia da lista de vendas
    }

    public List<Compra> getCompras() {
        return new ArrayList<Compra>(compras); // retorna uma cópia da lista de compras
    }

}
