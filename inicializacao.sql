-- Criação da tabela Produto (caso ainda não exista)
CREATE TABLE IF NOT EXISTS produtos (
    id VARCHAR(50) PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    desc TEXT,
    tipo VARCHAR(100)
);

-- Inserção de produtos na tabela Produto
INSERT INTO produtos (id, nome, desc, tipo) VALUES
('Produto001', 'Martelo', 'Martelo de ferro com cabo de madeira', 'Ferramenta'),
('Produto002', 'Parafuso', 'Parafuso Phillips 3x30mm', 'Fixação'),
('Produto003', 'Cimento', 'Saco de cimento CP-II 50kg', 'Construção'),
('Produto004', 'Tinta Azul', 'Tinta acrílica azul 3,6L', 'Pintura'),
('Produto005', 'Serrote', 'Serrote manual 18 polegadas', 'Ferramenta');
