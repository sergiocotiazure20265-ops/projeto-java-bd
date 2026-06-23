-- Script para criação da tabela de produtos no banco de dados
CREATE TABLE produtos(
    id             SERIAL PRIMARY KEY,
    nome           VARCHAR(150) NOT NULL,
    preco          NUMERIC(10, 2) NOT NULL,
    quantidade     INT NOT NULL,
    data_cadastro  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);