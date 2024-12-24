import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProjetoLojaTeste {
   private Produto produto;
   private Cliente cliente;
   private Estoque estoque;
   private Loja loja;
   private Fornecedor fornecedor;

   @BeforeEach
    void setUp(){
       produto = new Produto("Martelo", 10.0f, 15.0f);
       cliente = new Cliente("João", "(34)9 1234-5678");
       fornecedor = new Fornecedor("Empresa XYZ" ,"12.345.678/9876-54", "(34) 9 4321-1234", "contato@xyz.com.br", "Rua B, 321, Cidade X");
       estoque = new Estoque();
       loja = new Loja("Loja Teste", "Rua A, 123, Cidade X", true);

   }

   @Test
    void TestProduto(){
       assertEquals("Martelo", produto.getNome());
       assertEquals(10.0f, produto.getPrecoCompra());
       assertEquals(15.0f, produto.getPrecoVenda());

       produto.setPrecoVenda(20.0f);
       assertEquals(20.0f, produto.getPrecoVenda());
       produto.setDesc("Martelo Carpinteiro com Cabo em Fibra de Vidro 450g");

       System.out.println(produto);
   }

   @Test
   void TestCliente(){
      System.out.println(cliente);
   }

   @Test
   void TestFornecedor(){
      System.out.println(fornecedor);
      System.out.println(cliente);
   }



}
