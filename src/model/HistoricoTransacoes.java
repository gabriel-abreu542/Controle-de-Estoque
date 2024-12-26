package model;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

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

    public List<Venda> getVendasCliente(Cliente cli){
        ArrayList<Venda> filtrado = new ArrayList<>();

        for (Venda v : vendas){
            if(v.getCliente().equals(cli)){
                filtrado.add(v);
            }
        }

        return filtrado;
    }

    public List<Compra> getComprasFornecedor(Fornecedor f){
        ArrayList<Compra> filtrado = new ArrayList<>();

        for (Compra v : compras){
            if(v.getFornecedor().equals(f)){
                filtrado.add(v);
            }
        }

        return filtrado;
    }

    public List<Venda> getVendasData(LocalDate d){
        ArrayList<Venda> filtrado = new ArrayList<>();

        for (Venda v : vendas){
            if(v.getDataTransacao().equals(d)){
                filtrado.add(v);
            }
        }

        return filtrado;
    }

    public List<Compra> getComprasData(LocalDate d){
        ArrayList<Compra> filtrado = new ArrayList<>();

        for (Compra c : compras){
            if(c.getDataTransacao().equals(d)){
                filtrado.add(c);
            }
        }

        return filtrado;
    }

    public List<Venda> getVendas() {
        return new ArrayList<Venda>(vendas); // retorna uma cópia da lista de vendas
    }

    public List<Compra> getCompras() {
        return new ArrayList<Compra>(compras); // retorna uma cópia da lista de compras
    }

}
