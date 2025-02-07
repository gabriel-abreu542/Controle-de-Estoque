# Controle de Estoque
[![NPM](https://img.shields.io/npm/l/react)](https://github.com/gabriel-abreu542/Controle-de-Estoque/blob/master/LICENSE) 

# Sobre o projeto
Sistema de controle de estoque de loja, permitindo o cadastro de Produtos, Clientes, Fornecedores, Compras (Loja compra produtos de um fornecedor), Vendas (Loja vende produtos para um cliente). 

# Tecnologias usadas
Banco de dados: SQLite
SceneBuilder e JavaFX para UI
Organização dos pacotes do sistema:
dao: Data-Access Objects (realizam as consultas e operações SQL necessárias para manipular as instâncias de cada objeto no Banco de Dados)
model: Modelo dos objetos tratados no sistema (classes Produto, Cliente, Fornecedor, Venda, Compra, Loja, etc.)
service: Classes responsáveis pelo tratamento das operações de cadastro dos objetos no sistema (implementam as regras de negócio)
view: Classes do tipo “Controller”, responsáveis por controlar o comportamento das janelas FXML. 
test: pacote usado para testes unitários (Framework Jupiter JUnit). Possui os pacotes dao, model, view e service com cada classe de teste separada.

# Autor

Gabriel Martins Abreu

https://www.linkedin.com/in/gabriel-martins-abreu-4b26a22a3/
