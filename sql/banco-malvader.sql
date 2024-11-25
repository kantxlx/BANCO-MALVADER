CREATE DATABASE banco_malvader;

USE banco_malvader;

-- Tabela de Clientes
CREATE TABLE clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
    cpf VARCHAR(11) UNIQUE,
    data_nascimento DATE,
    telefone VARCHAR(15),
    endereco VARCHAR(200),
    senha VARCHAR(100)
);

-- Tabela de Contas
CREATE TABLE contas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    agencia VARCHAR(10),
    numero_conta VARCHAR(20) UNIQUE,
    tipo ENUM('CP', 'CC'),
    saldo DECIMAL(15, 2) DEFAULT 0.0,
    limite DECIMAL(15, 2) DEFAULT 0.0,
    vencimento DATE,
    cliente_id INT,
    FOREIGN KEY (cliente_id) REFERENCES clientes(id)
);

-- Tabela de Funcionários
CREATE TABLE funcionarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
    cpf VARCHAR(11) UNIQUE,
    cargo VARCHAR(50),
    senha VARCHAR(100)
);

-- Tabela de Transações
CREATE TABLE transacoes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    conta_id INT,
    tipo ENUM('DEPOSITO', 'SAQUE'),
    valor DECIMAL(15, 2),
    data_transacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (conta_id) REFERENCES contas(id)
);
