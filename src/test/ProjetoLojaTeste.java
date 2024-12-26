import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class ProjetoLojaTeste {

   @Test
   void testProduto() {
      Produto produto = new Produto("Camiseta", 20.0f, 40.0f);
      assertEquals("Camiseta", produto.getNome());
      assertEquals(20.0f, produto.getPrecoCompra());
      assertEquals(40.0f, produto.getPrecoVenda());
      produto.setNome("Camiseta Atualizada");
      produto.setDesc("Camiseta branca");
      assertEquals("Camiseta Atualizada", produto.getNome());
      assertEquals("Camiseta branca", produto.getDesc());
   }

   @Test
   void testCliente() {
      Cliente cliente = new Cliente("João", "123456789");
      assertEquals("João", cliente.toString().contains("João") ? "João" : "");
      cliente.setDivida(100.0f);
      assertTrue(cliente.temDivida());
      cliente.setDivida(200);
      System.out.println(cliente);
      assertThrows(IllegalArgumentException.class, () -> cliente.setDivida(-50.0f));

   }

   @Test
   void testFornecedor() {
      Fornecedor fornecedor = new Fornecedor("Fornecedor 1", "1234567890001", "999999999", "email@fornecedor.com", "Rua 1");
      assertEquals("Fornecedor 1", fornecedor.getNome());
      assertEquals("1234567890001", fornecedor.getCnpj());
   }

   @Test
   void testEstoque() {
      Estoque estoque = new Estoque();
      Produto produto = new Produto("Camiseta", 20.0f, 40.0f);
      estoque.adicionarProduto(produto, 10);
      assertEquals(10, estoque.getEstoque().get(produto));
      estoque.removerProduto(produto, 5);
      assertEquals(5, estoque.getEstoque().get(produto));
      assertThrows(RuntimeException.class, () -> estoque.removerProduto(produto, 10));
   }

   @Test
   void testTransacao() {
      Produto produto = new Produto("Camiseta", 20.0f, 40.0f);
      Transacao transacao = new Transacao(Pagamento.DINHEIRO);
      transacao.adicionarItem(produto, 2);
      assertEquals(80.0f, transacao.valorTotal);
      transacao.removerItem(produto, 1);
      assertEquals(40.0f, transacao.valorTotal);
   }

   @Test
   void testCompra() {
      Produto produto = new Produto("Camiseta", 20.0f, 40.0f);
      Fornecedor fornecedor = new Fornecedor("Fornecedor 1", "1234567890001", "999999999", "email@fornecedor.com", "Rua 1");

      Compra compra = new Compra(fornecedor, Pagamento.PIX);
      compra.adicionarItem(produto, 5);
      assertEquals(200.0f, compra.valorTotal);
      assertEquals(fornecedor, compra.getFornecedor());
   }

   @Test
   void testVenda() {
      Cliente cliente = new Cliente("João", "123456789");
      Produto produto = new Produto("Camiseta", 20.0f, 40.0f);
      Venda venda = new Venda(cliente, Pagamento.CREDITO);
      venda.adicionarItem(produto, 3);
      assertEquals(120.0f, venda.getValorTotal());
   }

   @Test
   void testLoja() {
      Loja loja = new Loja("Loja 1", "Rua Principal", true);
      Produto produto1 = new Produto("Camiseta", 20.0f, 40.0f);
      Produto produto2 = new Produto("Bota", 16.0f, 30.0f);
      Cliente cliente = new Cliente("João", "123456789");
      Fornecedor fornecedor = new Fornecedor("Fornecedor 1", "1234567890001", "999999999", "email@fornecedor.com", "Rua 1");
      Compra compra = new Compra(fornecedor, Pagamento.PIX);
      compra.adicionarItem(produto1, 10);
      compra.adicionarItem(produto2, 20);
      assertTrue(loja.realizarCompra(compra));
      assertEquals(10, loja.getEstoque().getEstoque().get(produto1));
      assertEquals(20, loja.getEstoque().getEstoque().get(produto2));

      Venda venda = new Venda(cliente, Pagamento.CREDITO);
      venda.adicionarItem(produto1, 5);
      assertTrue(loja.realizarVenda(venda));
      assertEquals(5, loja.getEstoque().getEstoque().get(produto1));

      System.out.println("Historico de compras:");
      System.out.println(loja.getHistorico().getCompras());
      System.out.println("Historico de vendas:");
      System.out.println(loja.getHistorico().getVendas());

   }
}
