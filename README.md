# Banco Malvader

Banco Malvader é um sistema bancário desenvolvido em Java com funcionalidades básicas de autenticação de usuários (clientes e funcionários), consulta de saldo, depósitos e saques. O projeto foi criado com o objetivo de demonstrar conceitos básicos de programação orientada a objetos, manipulação de banco de dados e interação com o usuário através de uma interface gráfica.

## Acesso ao Programa

### Autenticação Inicial:
- Exibir o menu principal com três opções:
  1. **Funcionário**: Permite acesso ao menu do funcionário.
  2. **Cliente**: Permite acesso ao menu do cliente.
  3. **Sair do Programa**: Encerra a aplicação.
  
- Solicitar senha para acessar as funcionalidades.

## Menu Funcionário

### Abertura de Conta:
- Permitir ao funcionário abrir novas contas bancárias.
- **Opções de tipo de conta**:
  - Conta Poupança (CP) e Conta Corrente (CC).
- **Solicitar dados específicos**:
  - **Poupança**: agência, número da conta, nome do cliente, CPF, data de nascimento, telefone, endereço completo (incluindo CEP, local, número da casa, bairro, cidade, estado), e senha do cliente.
  - **Corrente**: itens acima, além de limite da conta e data de vencimento.

### Encerramento de Conta:
- Solicitar senha de administrador para encerrar contas.
- Permitir busca por número da conta para encerramento.
- Confirmar o encerramento ao usuário após operação bem-sucedida.

### Consulta de Dados:
- **Submenu com três opções**:
  1. **Consultar Conta**: Exibir tipo de conta, nome, CPF, saldo, limite disponível e data de vencimento.
  2. **Consultar Funcionário**: Exibir código, cargo, nome, CPF, data de nascimento, telefone, e endereço completo.
  3. **Consultar Cliente**: Exibir nome, CPF, data de nascimento, telefone, e endereço completo.
  
- Possibilitar retorno ao menu principal a partir desse submenu.

### Alteração de Dados:
- **Submenu com três opções para editar**:
  1. **Conta**: Alterar limite disponível e data de vencimento.
  2. **Funcionário**: Alterar código, cargo, telefone e endereço completo.
  3. **Cliente**: Alterar telefone e endereço completo.
  
- Solicitar senha de administrador para alterações.

### Cadastro de Funcionários:
- Solicitar senha de administrador para acesso.
- Inserir dados completos: código do funcionário, cargo, nome, CPF, data de nascimento, telefone e endereço completo.

### Geração de Relatórios:
- Gerar um relatório geral com movimentações financeiras e exportá-lo para CSV.
- Solicitar senha do funcionário antes de exibir o relatório.

### Sair:
- Retornar ao menu principal do sistema.

## Menu Cliente

### Operações de Conta:
- **Submenu com funcionalidades**:
  1. **Saldo**: Mostrar saldo ao cliente (solicitar senha antes de exibir).
  2. **Depósito**: Permitir depósito de valores na conta.
  3. **Saque**: Permitir saque (solicitar senha e verificar saldo).
  4. **Extrato**: Exibir extrato com movimentações e permitir exportação para CSV.
  5. **Consultar Limite**: Exibir limite disponível na conta (solicitar senha).
  
- Opção para retornar ao menu principal.

### Encerrar Programa:
- Finalizar o sistema e encerrar o acesso, retornando ao Menu Principal.

---

## Tecnologias Utilizadas

- **Java**: Linguagem de programação principal.
- **JDBC (Java Database Connectivity)**: Para comunicação com o banco de dados.
- **MySQL/MariaDB**: Banco de dados relacional para armazenar dados de clientes, contas, transações e funcionários.
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
   - Execute os scripts SQL para criar as tabelas necessárias (clientes, contas, funcionários, transações, administradores).

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
        senha VARCHAR(100),
        telefone VARCHAR(15),
        endereco VARCHAR(200)
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

    -- Adicionar a tabela de Administradores
    CREATE TABLE administradores (
        id INT AUTO_INCREMENT PRIMARY KEY,
        nome VARCHAR(100),
        senha VARCHAR(100) -- Armazena a senha do administrador
    );

    -- Inserir a tabela de administradores
    INSERT INTO administradores (nome, senha) VALUES ('Administrador', '1234');
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
   - Os clientes podem se cadastrar, fazer login, consultar saldo, realizar depósitos/saques e visualizar o extrato de movimentações.
   - Os clientes também podem alterar seus dados pessoais (nome, telefone, endereço).
   - Os funcionários podem acessar a interface administrativa para visualizar ou gerenciar clientes e transações, além de gerar relatórios das movimentações em formato CSV.

---

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