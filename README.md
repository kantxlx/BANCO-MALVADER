# Banco Malvader

Banco Malvader é um sistema bancário desenvolvido em Java com funcionalidades básicas de autenticação de usuários (clientes e funcionários), consulta de saldo, depósitos e saques. O projeto foi criado com o objetivo de demonstrar conceitos básicos de programação orientada a objetos, manipulação de banco de dados e interação com o usuário através de uma interface gráfica.

## Funcionalidades

- **Cadastro de Clientes e Funcionários**
- **Autenticação de Clientes e Funcionários**
- **Consulta de Saldo**
- **Realização de Depósitos**
- **Realização de Saques**
- **Visualização de Dados da Conta**

## Tecnologias Utilizadas

- **Java**: Linguagem de programação principal.
- **JDBC (Java Database Connectivity)**: Para comunicação com o banco de dados.
- **MySQL/MariaDB**: Banco de dados relacional para armazenar dados de clientes, contas e transações.
- **Swing**: Biblioteca gráfica para criação da interface do usuário.

## Como Rodar o Projeto

### Pré-requisitos

- **Java 8 ou superior** instalado.
- **MySQL ou MariaDB** configurado e em execução.

### Passos para Configuração

1. **Clone este repositório**:
    ```bash
    git clone https://github.com/kantxlx/BANCO-MALVADER.git
    ```

2. **Importe o projeto para o seu IDE preferida** (ex: IntelliJ, Eclipse, NetBeans).

3. **Configuração do banco de dados**:
   - Crie um banco de dados chamado `banco_malvader` no MySQL ou MariaDB.
   - Execute os scripts SQL para criar as tabelas necessárias (clientes, contas, funcionários, transações).

    ```sql
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
    ```

4. **Configuração da Conexão com o Banco de Dados**:
   - No arquivo `DatabaseConnection.java`, ajuste a URL, o usuário e a senha do banco de dados:

    ```java
    private static final String URL = "jdbc:mysql://localhost:3306/banco_malvader";
    private static final String USER = "root";  // Insira o seu usuário
    private static final String PASSWORD = "";  // Insira a sua senha
    ```

5. **Rodando o Projeto**:
   - Após configurar o banco de dados e ajustar as credenciais de conexão, compile e execute o projeto.

6. **Utilização**:
   - Inicie o projeto e a interface gráfica será exibida.
   - Os clientes podem se cadastrar, fazer login, consultar saldo e realizar depósitos/saques.
   - Os funcionários podem acessar a interface administrativa para visualizar ou gerenciar clientes e transações.

## Contribuições

Contribuições são bem-vindas! Siga os passos abaixo para contribuir:

1. **Fork este repositório**.
2. **Crie uma branch para sua funcionalidade** (`git checkout -b minha-funcionalidade`).
3. **Faça o commit das suas alterações** (`git commit -m 'Adicionando nova funcionalidade'`).
4. **Faça o push para sua branch** (`git push origin minha-funcionalidade`).
5. **Abra um Pull Request**.

## Licença

Este projeto está licenciado sob a Licença MIT - veja o arquivo [LICENSE](LICENSE) para mais detalhes.

---

