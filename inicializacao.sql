-- Criação das tabelas
CREATE TABLE IF NOT EXISTS fornecedores (
    cnpj TEXT PRIMARY KEY,
    nome TEXT NOT NULL,
    telefone TEXT NOT NULL,
    email TEXT NOT NULL,
    endereco TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS clientes (
    cpf TEXT PRIMARY KEY,
    nome TEXT NOT NULL,
    telefone TEXT NOT NULL,
    endividado BOOLEAN NOT NULL,
    divida REAL
);

-- Criação da tabela Produto (caso ainda não exista)
CREATE TABLE IF NOT EXISTS produtos (
    id VARCHAR(50) PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    desc TEXT,
    tipo VARCHAR(100)
);

-- Inserção de dados na tabela fornecedores
INSERT INTO fornecedores (cnpj, nome, telefone, email, endereco) VALUES
('12.345.678/0001-99', 'Construtora ABC', '(11) 98765-4321', 'contato@construtoraabc.com', 'Rua das Obras, 123, São Paulo, SP'),
('98.765.432/0001-55', 'Distribuidora Materiais XYZ', '(21) 91234-5678', 'vendas@xyzmateriais.com', 'Avenida Central, 456, Rio de Janeiro, RJ'),
('33.456.789/0001-22', 'Ferragens do Norte', '(85) 99876-5432', 'suporte@ferragensnorte.com', 'Rua dos Operários, 789, Fortaleza, CE'),
('45.678.901/0001-11', 'Cimentos Beta', '(31) 98765-1122', 'vendas@cimentosbeta.com', 'Avenida Concreto, 200, Belo Horizonte, MG');

-- Inserção de dados na tabela clientes
INSERT INTO clientes (cpf, nome, telefone, endividado, divida) VALUES
('123.456.789-10', 'João Silva', '(11) 91234-5678', 1, 450.00),
('987.654.321-00', 'Maria Oliveira', '(21) 92345-6789', 0, 0.00),
('456.789.123-33', 'Pedro Santos', '(31) 93456-7890', 1, 1200.50),
('789.123.456-66', 'Ana Costa', '(85) 99887-6655', 0, 0.00);

-- Inserção de produtos na tabela Produto
INSERT INTO produtos (id, nome, desc, tipo) VALUES
('Produto001', 'Martelo', 'Martelo de ferro com cabo de madeira', 'Ferramenta'),
('Produto002', 'Parafuso', 'Parafuso Phillips 3x30mm', 'Fixação'),
('Produto003', 'Cimento', 'Saco de cimento CP-II 50kg', 'Construção'),
('Produto004', 'Tinta Azul', 'Tinta acrílica azul 3,6L', 'Pintura'),
('Produto005', 'Serrote', 'Serrote manual 18 polegadas', 'Ferramenta');


