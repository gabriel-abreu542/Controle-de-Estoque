//package test.model;
//
//import model.*;
//import org.junit.jupiter.api.*;
//
//import java.time.LocalDate;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class ProjetoLojaTest {
//
//   @Test
//   void testProduto() {
//      Produto produto = new Produto("1","Camiseta", 20.0f, 40.0f);
//      assertEquals("1", produto.getId());
//      assertEquals("Camiseta", produto.getNome());
//      assertEquals(20.0f, produto.getPrecoCompra());
//      assertEquals(40.0f, produto.getPrecoVenda());
//      produto.setNome("Camiseta Atualizada");
//      produto.setDesc("Camiseta branca");
//      assertEquals("Camiseta Atualizada", produto.getNome());
//      assertEquals("Camiseta branca", produto.getDesc());
//   }
//
//   @Test
//   void testCliente() {
//      Cliente cliente = new Cliente("1","João", "123456789");
//      assertEquals("João", cliente.toString().contains("João") ? "João" : "");
//      cliente.setDivida(100.0f);
//      assertTrue(cliente.temDivida());
//      cliente.setDivida(200);
//      System.out.println(cliente);
//      assertThrows(IllegalArgumentException.class, () -> cliente.setDivida(-50.0f));
//
//   }
//
//   @Test
//   void testFornecedor() {
//      Fornecedor fornecedor = new Fornecedor("1234567890001", "Fornecedor 1", "999999999", "email@fornecedor.com", "Rua 1");
//      assertEquals("Fornecedor 1", fornecedor.getNome());
//      assertEquals("1234567890001", fornecedor.getId());
//   }
//
//   @Test
//   void testEstoque() {
//      Estoque estoque = new Estoque();
//      Produto produto = new Produto("1","Camiseta", 20.0f, 40.0f);
//      estoque.adicionarProduto(produto, 10);
//      assertEquals(10, estoque.getEstoque().get(produto));
//      estoque.removerProduto(produto, 5);
//      assertEquals(5, estoque.getEstoque().get(produto));
//      assertThrows(RuntimeException.class, () -> estoque.removerProduto(produto, 10));
//   }
//
//   @Test
//   void testCompra() {
//      Produto produto = new Produto("1","Camiseta", 20.0f, 40.0f);
//      Fornecedor fornecedor = new Fornecedor("1234567890001", "Fornecedor 1", "999999999", "email@fornecedor.com", "Rua 1");
//
//      Compra compra = new Compra("1", fornecedor, "PIX");
//      compra.adicionarItem(produto, 5);
//      assertEquals(200.0f, compra.getValorTotal());
//      assertEquals(fornecedor, compra.getFornecedor());
//   }
//
//   @Test
//   void testVenda() {
//      Cliente cliente = new Cliente("1", "João", "123456789");
//      Produto produto = new Produto("2","Camiseta", 20.0f, 40.0f);
//      Venda venda = new Venda("1",cliente, "CREDITO");
//      venda.adicionarItem(produto, 3);
//      assertEquals(120.0f, venda.getValorTotal());
//   }
//
//   @Test
//   void testLoja() {
//      Loja loja = new Loja("1" , "Loja 1", "Rua Principal", true);
//      Produto produto1 = new Produto("1","Camiseta", 20.0f, 40.0f);
//      Produto produto2 = new Produto("2","Bota", 16.0f, 30.0f);
//      Cliente cliente = new Cliente("1","João", "123456789");
//      Fornecedor fornecedor = new Fornecedor("1234567890001", "Fornecedor 1", "999999999", "email@fornecedor.com", "Rua 1");
//      Compra compra = new Compra("1", fornecedor, "PIX");
//      compra.adicionarItem(produto1, 10);
//      compra.adicionarItem(produto2, 20);
//      assertTrue(loja.realizarCompra(compra));
//      assertEquals(10, loja.getEstoque().getEstoque().get(produto1));
//      assertEquals(20, loja.getEstoque().getEstoque().get(produto2));
//
//      Venda venda = new Venda("1", cliente, "CREDITO");
//      venda.adicionarItem(produto1, 5);
//      assertTrue(loja.realizarVenda(venda));
//      assertEquals(5, loja.getEstoque().getEstoque().get(produto1));
//
//      System.out.println("Historico de compras:");
//      System.out.println(loja.getHistorico().getCompras());
//      System.out.println("Historico de vendas:");
//      System.out.println(loja.getHistorico().getVendas());
//
//   }
//
//   @Test
//   public void testeFiltros() {
//      Loja loja = new Loja("1","Minha Loja", "Rua Principal, 123", true);
//
//      Cliente cliente1 = new Cliente("1", "Gabriel", "9999-9999");
//      Cliente cliente2 = new Cliente("2", "Maria", "8888-8888");
//
//      Fornecedor fornecedor1 = new Fornecedor("11111111/0001-11", "Fornecedor A", "1234-5678", "email@a.com", "Rua A");
//      Fornecedor fornecedor2 = new Fornecedor("22222222/0001-22", "Fornecedor B", "5678-1234", "email@b.com", "Rua B");
//
//      Produto produto1 = new Produto("1","Parafuso", 0.50f, 1.00f);
//      Produto produto2 = new Produto("2","Cimento", 15.00f, 30.00f);
//
//      Compra compra1 = new Compra("1",fornecedor1, "PIX");
//      compra1.adicionarItem(produto1, 100);
//      compra1.setDataTransacao(LocalDate.of(2024, 12, 24));
//      assertTrue(loja.realizarCompra(compra1));
//      System.out.println(compra1);
//
//      Compra compra2 = new Compra("2",fornecedor2, "DINHEIRO");
//      compra2.adicionarItem(produto2, 50);
//      compra2.setDataTransacao(LocalDate.of(2024, 12, 25));
//      assertTrue(loja.realizarCompra(compra2));
//      System.out.println(compra2);
//
//      // Realizar vendas
//      Venda venda1 = new Venda("1",cliente1, "CREDITO");
//      venda1.adicionarItem(produto1, 10);
//      venda1.setDataTransacao(LocalDate.of(2024, 12, 26));
//      assertTrue(loja.realizarVenda(venda1));
//      System.out.println(venda1);
//
//      Venda venda2 = new Venda("2",cliente2, "DEBITO");
//      venda2.adicionarItem(produto2, 5);
//      venda2.setDataTransacao(LocalDate.of(2024, 12, 27));
//      assertTrue(loja.realizarVenda(venda2));
//      System.out.println(venda2);
//
//      // Testar filtro por fornecedor
//      List<Compra> comprasFornecedor1 = loja.getHistorico().getComprasFornecedor(fornecedor1);
//      assertEquals(1, comprasFornecedor1.size());
//      assertEquals(compra1, comprasFornecedor1.get(0));
//      System.out.println("Compras feitas com o Fornecedor " + fornecedor1.getNome() + ": ");
//      System.out.println(comprasFornecedor1);
//
//      List<Compra> comprasFornecedor2 = loja.getHistorico().getComprasFornecedor(fornecedor2);
//      assertEquals(1, comprasFornecedor2.size());
//      assertEquals(compra2, comprasFornecedor2.get(0));
//      System.out.println("Compras feitas com o Fornecedor " + fornecedor2.getNome() + ": ");
//      System.out.println(comprasFornecedor2);
//
//      // Testar filtro por cliente
//      List<Venda> vendasCliente1 = loja.getHistorico().getVendasCliente(cliente1);
//      assertEquals(1, vendasCliente1.size());
//      assertEquals(venda1, vendasCliente1.get(0));
//      System.out.println("Vendas feitas para o cliente " + cliente1.getNome() + ": ");
//      System.out.println(vendasCliente1);
//
//      List<Venda> vendasCliente2 = loja.getHistorico().getVendasCliente(cliente2);
//      assertEquals(1, vendasCliente2.size());
//      assertEquals(venda2, vendasCliente2.get(0));
//      System.out.println("Vendas feitas para o cliente " + cliente2.getNome() + ": ");
//      System.out.println(vendasCliente2);
//
//      // Testar filtro por data
//      List<Compra> comprasDia24 = loja.getHistorico().getComprasData(LocalDate.of(2024, 12, 24));
//      assertEquals(1, comprasDia24.size());
//      assertEquals(compra1, comprasDia24.get(0));
//      System.out.println("Compras feitas no dia 24 de Dezembro de 2024: ");
//      System.out.println(comprasDia24);
//
//      List<Venda> vendasDia26 = loja.getHistorico().getVendasData(LocalDate.of(2024, 12, 26));
//      assertEquals(1, vendasDia26.size());
//      assertEquals(venda1, vendasDia26.get(0));
//      System.out.println("Vendas feitas no dia 26 de Dezembro de 2024: ");
//      System.out.println(vendasDia26);
//   }
//}
