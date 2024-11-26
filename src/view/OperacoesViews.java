package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import controller.ClienteController;
import controller.ContaController;
import controller.FuncionarioController;
import model.Cliente;
import model.Conta;
import model.Funcionario;

public class OperacoesViews extends JFrame {

    public OperacoesViews() {
        setTitle("Banco Malvader - Operações");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        getContentPane().setBackground(Color.WHITE);

        Font font = new Font("Arial", Font.BOLD, 16);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        UIManager.put("Button.background", Color.BLACK);
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("Button.font", font);

        JLabel titleLabel = new JLabel("Escolha uma operação:");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);

        // Botões para operações
        addButton("Abrir Conta", gbc, 1, e -> abrirConta());
        addButton("Encerrar Conta", gbc, 2, e -> encerrarConta());
        addButton("Consultar Dados", gbc, 3, e -> consultarDados());
        addButton("Alterar Dados", gbc, 4, e -> alterarDados());
        addButton("Realizar Depósito", gbc, 5, e -> realizarDeposito());
        addButton("Realizar Saque", gbc, 6, e -> realizarSaque());

        JButton voltarButton = new JButton("Voltar");
        voltarButton.setFont(font);
        voltarButton.setPreferredSize(new Dimension(200, 40));
        voltarButton.addActionListener(e -> dispose());
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        add(voltarButton, gbc);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addButton(String text, GridBagConstraints gbc, int y, java.awt.event.ActionListener action) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setPreferredSize(new Dimension(200, 40));
        button.addActionListener(action);
        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.gridwidth = 2;
        add(button, gbc);
    }

    private void abrirConta() {
        JFrame frame = new JFrame("Abrir Conta");
        frame.setSize(400, 600);
        frame.setLayout(new GridBagLayout());
        frame.getContentPane().setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        Font font = new Font("Arial", Font.BOLD, 16);

        // Declarando os campos de texto
        JTextField nomeField, cpfField, dataNascField, telefoneField, enderecoField, tipoField, saldoField;
        JPasswordField senhaField;

        // Campos para cadastro
        JLabel nomeLabel = new JLabel("Nome do Cliente:");
        nomeLabel.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 0;
        frame.add(nomeLabel, gbc);

        nomeField = new JTextField();
        nomeField.setFont(font);
        nomeField.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        gbc.gridy = 0;
        frame.add(nomeField, gbc);

        JLabel cpfLabel = new JLabel("CPF do Cliente:");
        cpfLabel.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 1;
        frame.add(cpfLabel, gbc);

        cpfField = new JTextField();
        cpfField.setFont(font);
        cpfField.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        gbc.gridy = 1;
        frame.add(cpfField, gbc);

        JLabel dataNascLabel = new JLabel("Data de Nascimento (dd/MM/yyyy):");
        dataNascLabel.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 2;
        frame.add(dataNascLabel, gbc);

        dataNascField = new JTextField();
        dataNascField.setFont(font);
        dataNascField.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        gbc.gridy = 2;
        frame.add(dataNascField, gbc);

        JLabel telefoneLabel = new JLabel("Telefone:");
        telefoneLabel.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 3;
        frame.add(telefoneLabel, gbc);

        telefoneField = new JTextField();
        telefoneField.setFont(font);
        telefoneField.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        gbc.gridy = 3;
        frame.add(telefoneField, gbc);

        JLabel enderecoLabel = new JLabel("Endereço:");
        enderecoLabel.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 4;
        frame.add(enderecoLabel, gbc);

        enderecoField = new JTextField();
        enderecoField.setFont(font);
        enderecoField.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        gbc.gridy = 4;
        frame.add(enderecoField, gbc);

        JLabel senhaLabel = new JLabel("Senha do Cliente:");
        senhaLabel.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 5;
        frame.add(senhaLabel, gbc);

        senhaField = new JPasswordField();
        senhaField.setFont(font);
        senhaField.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        gbc.gridy = 5;
        frame.add(senhaField, gbc);

        JLabel tipoLabel = new JLabel("Tipo de Conta (CP/CC):");
        tipoLabel.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 6;
        frame.add(tipoLabel, gbc);

        tipoField = new JTextField();
        tipoField.setFont(font);
        tipoField.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        gbc.gridy = 6;
        frame.add(tipoField, gbc);

        JLabel saldoLabel = new JLabel("Saldo Inicial:");
        saldoLabel.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 7;
        frame.add(saldoLabel, gbc);

        saldoField = new JTextField();
        saldoField.setFont(font);
        saldoField.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        gbc.gridy = 7;
        frame.add(saldoField, gbc);

        JButton criarButton = new JButton("Criar Conta");
        criarButton.setFont(font);
        criarButton.setPreferredSize(new Dimension(200, 40));
        criarButton.addActionListener(e -> {
            try {
                String nome = nomeField.getText();
                String cpf = cpfField.getText();
                String dataNascimentoStr = dataNascField.getText();
                String telefone = telefoneField.getText();
                String endereco = enderecoField.getText();
                String senha = senhaField.getText();
                String tipo = tipoField.getText();
                double saldo = Double.parseDouble(saldoField.getText());

                if (nome.isEmpty() || cpf.isEmpty() || dataNascimentoStr.isEmpty() || telefone.isEmpty() || endereco.isEmpty() || senha.isEmpty() || tipo.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Preencha todos os campos.");
                    return;
                }

                // Validar e formatar data
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate dataNascimento = LocalDate.parse(dataNascimentoStr, formatter);

                // Criar cliente e conta
                ClienteController clienteController = new ClienteController();
                Cliente cliente = new Cliente(nome, cpf, dataNascimento, telefone, endereco, senha);  // Não é necessário passar o ID

                // Cadastrar cliente
                if (clienteController.cadastrarCliente(cliente)) {
                    // Buscar cliente recém-cadastrado para obter o ID gerado
                    Cliente clienteCadastrado = clienteController.buscarClientePorCPF(cpf);
                    if (clienteCadastrado == null) {
                        JOptionPane.showMessageDialog(frame, "Erro ao recuperar cliente cadastrado.");
                        return;
                    }

                    ContaController contaController = new ContaController();
                    String numeroConta = gerarNumeroConta();

                    // Definir um vencimento padrão para contas se necessário
                    LocalDate vencimento = tipo.equalsIgnoreCase("CC") ? LocalDate.now().plusMonths(1) : null;

                    Conta conta = new Conta(
                        0,  // ID será gerado automaticamente pelo banco
                        "0001",  // Agência padrão
                        numeroConta,
                        tipo,
                        saldo,
                        tipo.equalsIgnoreCase("CC") ? 500.0 : 0.0, // Limite padrão apenas para CC
                        vencimento,
                        clienteCadastrado.getId()  // Atribuir o ID correto do cliente
                    );

                    if (contaController.abrirConta(conta)) {
                        JOptionPane.showMessageDialog(frame, "Cliente e conta criados com sucesso!\nNúmero da conta: " + numeroConta);
                        frame.dispose();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Erro ao criar conta.");
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Erro ao cadastrar cliente.");
                }
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(frame, "Data de nascimento inválida. Use dd/MM/yyyy.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Erro: " + ex.getMessage());
            }
        });

        gbc.gridy = 8;
        frame.add(criarButton, gbc);

        JButton voltarButton = new JButton("Voltar");
        voltarButton.setFont(font);
        voltarButton.addActionListener(e -> frame.dispose());
        gbc.gridy = 9;
        frame.add(voltarButton, gbc);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    
    private String gerarNumeroConta() {
        // Gerar número de conta único (com 8 dígitos aleatórios)
        String numeroConta;
        Random rand = new Random();
        do {
            numeroConta = String.format("%08d", rand.nextInt(100000000)); // 8 dígitos aleatórios
        } while (verificarNumeroContaExistente(numeroConta));
        return numeroConta;
    }
    
    private boolean verificarNumeroContaExistente(String numeroConta) {
        // Verificar se o número de conta gerado já existe no banco de dados
        ContaController contaController = new ContaController();
        return contaController.buscarContaPorNumero(numeroConta) != null;
    }
    
    private void encerrarConta() {
        String cpf = JOptionPane.showInputDialog("Digite o CPF do cliente:");
        if (cpf != null) {
            ClienteController clienteController = new ClienteController();
            Cliente cliente = clienteController.buscarClientePorCPF(cpf);
    
            if (cliente != null) {
                ContaController contaController = new ContaController();
                Conta conta = contaController.buscarContaPorClienteId(cliente.getId());
    
                if (conta != null) {
                    int option = JOptionPane.showConfirmDialog(this,
                            "Deseja encerrar a conta e o cadastro?\nNúmero da conta: " + conta.getNumeroConta(),
                            "Confirmar", JOptionPane.YES_NO_OPTION);
    
                    if (option == JOptionPane.YES_OPTION) {
                        String senhaAdm = JOptionPane.showInputDialog("Digite a senha de administrador:");
                        if ("1234".equals(senhaAdm)) {
                            if (contaController.encerrarConta(conta.getNumeroConta()) && clienteController.excluirCliente(cpf)) {
                                JOptionPane.showMessageDialog(this, "Conta e cadastro encerrados com sucesso!");
                            } else {
                                JOptionPane.showMessageDialog(this, "Erro ao encerrar conta ou cliente.");
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "Senha de administrador inválida.");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Conta não encontrada para o CPF.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Cliente não encontrado.");
            }
        }
    }

    private void consultarDados() {
        JFrame frame = new JFrame("Consultar Dados");
        frame.setSize(500, 400);
        frame.setLayout(new GridBagLayout());
        frame.getContentPane().setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        Font font = new Font("Arial", Font.BOLD, 16);

        // Consultar dados da conta
        JButton consultarConta = new JButton("Consultar Conta");
        consultarConta.setFont(font);
        consultarConta.setPreferredSize(new Dimension(200, 40));
        consultarConta.addActionListener(e -> {
            String numeroConta = JOptionPane.showInputDialog("Digite o número da conta:");
            if (numeroConta != null) {
                ContaController contaController = new ContaController();
                Conta conta = contaController.buscarContaPorNumero(numeroConta);

                if (conta != null) {
                    ClienteController clienteController = new ClienteController();
                    Cliente cliente = clienteController.buscarClientePorCPF(String.valueOf(conta.getClienteId()));
                    JOptionPane.showMessageDialog(frame,
                            "Dados da Conta:\n" +
                                    "Tipo: " + conta.getTipo() + "\n" +
                                    "Nome: " + (cliente != null ? cliente.getNome() : "Não encontrado") + "\n" +
                                    "CPF: " + (cliente != null ? cliente.getCpf() : "Não encontrado") + "\n" +
                                    "Saldo: R$ " + conta.getSaldo() + "\n" +
                                    "Limite: R$ " + conta.getLimite() + "\n" +
                                    "Data de Vencimento: " + (conta.getVencimento() != null ? conta.getVencimento() : "Não aplicável"));
                } else {
                    JOptionPane.showMessageDialog(frame, "Conta não encontrada.");
                }
            }
        });

        // Consultar dados do cliente
        JButton consultarCliente = new JButton("Consultar Cliente");
        consultarCliente.setFont(font);
        consultarCliente.setPreferredSize(new Dimension(200, 40));
        consultarCliente.addActionListener(e -> {
            String cpf = JOptionPane.showInputDialog("Digite o CPF do cliente:");
            if (cpf != null) {
                ClienteController clienteController = new ClienteController();
                Cliente cliente = clienteController.buscarClientePorCPF(cpf);

                if (cliente != null) {
                    JOptionPane.showMessageDialog(frame,
                            "Dados do Cliente:\n" +
                                    "Nome: " + cliente.getNome() + "\n" +
                                    "CPF: " + cliente.getCpf() + "\n" +
                                    "Data de Nascimento: " + cliente.getDataNascimento() + "\n" +
                                    "Telefone: " + cliente.getTelefone() + "\n" +
                                    "Endereço: " + cliente.getEndereco());
                } else {
                    JOptionPane.showMessageDialog(frame, "Cliente não encontrado.");
                }
            }
        });

        // Consultar dados do funcionário
        JButton consultarFuncionario = new JButton("Consultar Funcionário");
        consultarFuncionario.setFont(font);
        consultarFuncionario.setPreferredSize(new Dimension(200, 40));
        consultarFuncionario.addActionListener(e -> {
            String cpf = JOptionPane.showInputDialog("Digite o CPF do funcionário:");
            if (cpf != null) {
                FuncionarioController funcionarioController = new FuncionarioController();
                Funcionario funcionario = funcionarioController.buscarFuncionarioPorCPF(cpf);

                if (funcionario != null) {
                    JOptionPane.showMessageDialog(frame,
                            "Dados do Funcionário:\n" +
                                    "Código: " + funcionario.getId() + "\n" +
                                    "Cargo: " + funcionario.getCargo() + "\n" +
                                    "Nome: " + funcionario.getNome() + "\n" +
                                    "CPF: " + funcionario.getCpf());
                } else {
                    JOptionPane.showMessageDialog(frame, "Funcionário não encontrado.");
                }
            }
        });

        // Adicionar os botões no frame
        gbc.gridx = 0;
        gbc.gridy = 0;
        frame.add(consultarConta, gbc);

        gbc.gridy = 1;
        frame.add(consultarCliente, gbc);

        gbc.gridy = 2;
        frame.add(consultarFuncionario, gbc);

        JButton voltarButton = new JButton("Voltar");
        voltarButton.setFont(font);
        voltarButton.setPreferredSize(new Dimension(200, 40));
        voltarButton.addActionListener(e -> frame.dispose());
        gbc.gridy = 3;
        frame.add(voltarButton, gbc);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void alterarDados() {
        JFrame frame = new JFrame("Alterar Dados");
        frame.setSize(500, 400);
        frame.setLayout(new GridBagLayout());
        frame.getContentPane().setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        Font font = new Font("Arial", Font.BOLD, 16);

        // Botão para alterar dados de conta
        JButton alterarConta = new JButton("Alterar Conta");
        alterarConta.setFont(font);
        alterarConta.setPreferredSize(new Dimension(200, 40));
        alterarConta.addActionListener(e -> {
            String numeroConta = JOptionPane.showInputDialog("Digite o número da conta a alterar:");
            if (numeroConta != null) {
                ContaController contaController = new ContaController();
                Conta conta = contaController.buscarContaPorNumero(numeroConta);

                if (conta != null) {
                    String novoLimite = JOptionPane.showInputDialog("Novo limite:", conta.getLimite());
                    String novaDataVencimento = JOptionPane.showInputDialog("Nova data de vencimento (AAAA-MM-DD):", conta.getVencimento());
                    if (novaDataVencimento != null) {
                        try {
                            // Convertendo a String para LocalDate
                            LocalDate localDateVencimento = LocalDate.parse(novaDataVencimento);

                            conta.setVencimento(localDateVencimento);
                            contaController.atualizarConta(conta);

                            JOptionPane.showMessageDialog(frame, "Dados da conta atualizados com sucesso!");
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(frame, "Formato de data inválido. Use o formato AAAA-MM-DD.");
                        }
                    }

                    if (novoLimite != null && novaDataVencimento != null) {
                        // Solicita senha de administrador
                        String senhaAdm = JOptionPane.showInputDialog("Digite a senha do administrador:");
                    
                        if (senhaAdm != null && senhaAdm.equals("1234")) {
                            conta.setLimite(Double.parseDouble(novoLimite));
                    
                            try {
                                // Convertendo a string novaDataVencimento para LocalDate
                                LocalDate localDateVencimento = LocalDate.parse(novaDataVencimento);
                    
                                // Agora, podemos usar o LocalDate para definir o vencimento
                                conta.setVencimento(localDateVencimento);
                                contaController.atualizarConta(conta);
                    
                                JOptionPane.showMessageDialog(frame, "Dados da conta atualizados com sucesso!");
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(frame, "Formato de data inválido. Use o formato AAAA-MM-DD.");
                            }
                        } else {
                            JOptionPane.showMessageDialog(frame, "Senha de administrador inválida.");
                        }
                    }
                    
                } else {
                    JOptionPane.showMessageDialog(frame, "Conta não encontrada.");
                }
            }
        });

        // Botão para alterar dados do cliente
        JButton alterarCliente = new JButton("Alterar Cliente");
        alterarCliente.setFont(font);
        alterarCliente.setPreferredSize(new Dimension(200, 40));
        alterarCliente.addActionListener(e -> {
            String cpf = JOptionPane.showInputDialog("Digite o CPF do cliente a alterar:");
            if (cpf != null) {
                ClienteController clienteController = new ClienteController();
                Cliente cliente = clienteController.buscarClientePorCPF(cpf);

                if (cliente != null) {
                    String novoTelefone = JOptionPane.showInputDialog("Novo telefone:", cliente.getTelefone());
                    String novoEndereco = JOptionPane.showInputDialog("Novo endereço:", cliente.getEndereco());

                    if (novoTelefone != null && novoEndereco != null) {
                        // Solicita senha de administrador
                        String senhaAdm = JOptionPane.showInputDialog("Digite a senha do administrador:");

                        if (senhaAdm != null && senhaAdm.equals("1234")) {
                            cliente.setTelefone(novoTelefone);
                            cliente.setEndereco(novoEndereco);
                            clienteController.atualizarCliente(cliente);

                            JOptionPane.showMessageDialog(frame, "Dados do cliente atualizados com sucesso!");
                        } else {
                            JOptionPane.showMessageDialog(frame, "Senha de administrador inválida.");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Cliente não encontrado.");
                }
            }
        });

        // Botão para alterar dados do funcionário
        JButton alterarFuncionario = new JButton("Alterar Funcionário");
        alterarFuncionario.setFont(font);
        alterarFuncionario.setPreferredSize(new Dimension(200, 40));
        alterarFuncionario.addActionListener(e -> {
            String cpf = JOptionPane.showInputDialog("Digite o CPF do funcionário a alterar:");
            if (cpf != null) {
                FuncionarioController funcionarioController = new FuncionarioController();
                Funcionario funcionario = funcionarioController.buscarFuncionarioPorCPF(cpf);

                if (funcionario != null) {
                    String novoCargo = JOptionPane.showInputDialog("Novo cargo:", funcionario.getCargo());
                    String novoTelefone = JOptionPane.showInputDialog("Novo telefone:", funcionario.getTelefone());
                    String novoEndereco = JOptionPane.showInputDialog("Novo endereço:", funcionario.getEndereco());

                    if (novoCargo != null && novoTelefone != null && novoEndereco != null) {
                        // Solicita senha de administrador
                        String senhaAdm = JOptionPane.showInputDialog("Digite a senha do administrador:");

                        if (senhaAdm != null && senhaAdm.equals("1234")) {
                            funcionario.setCargo(novoCargo);
                            funcionario.setTelefone(novoTelefone);
                            funcionario.setEndereco(novoEndereco);
                            funcionarioController.atualizarFuncionario(funcionario);

                            JOptionPane.showMessageDialog(frame, "Dados do funcionário atualizados com sucesso!");
                        } else {
                            JOptionPane.showMessageDialog(frame, "Senha de administrador inválida.");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Funcionário não encontrado.");
                }
            }
        });

        // Adicionar os botões no frame
        gbc.gridx = 0;
        gbc.gridy = 0;
        frame.add(alterarConta, gbc);

        gbc.gridy = 1;
        frame.add(alterarCliente, gbc);

        gbc.gridy = 2;
        frame.add(alterarFuncionario, gbc);

        JButton voltarButton = new JButton("Voltar");
        voltarButton.setFont(font);
        voltarButton.setPreferredSize(new Dimension(200, 40));
        voltarButton.addActionListener(e -> frame.dispose());
        gbc.gridy = 3;
        frame.add(voltarButton, gbc);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void realizarDeposito() {
        // Solicitar dados para o depósito
        String numeroConta = JOptionPane.showInputDialog("Digite o número da conta:");
        String agencia = JOptionPane.showInputDialog("Digite o número da agência:");
        String valorStr = JOptionPane.showInputDialog("Digite o valor do depósito:");
        String senhaAdm = JOptionPane.showInputDialog("Digite a senha de administrador:");
    
        // Verificar se a senha do administrador está correta
        if (!"1234".equals(senhaAdm)) {
            JOptionPane.showMessageDialog(this, "Senha de administrador inválida.");
            return;
        }
    
        try {
            double valor = Double.parseDouble(valorStr);
    
            // Verificar se o valor é positivo
            if (valor <= 0) {
                JOptionPane.showMessageDialog(this, "O valor do depósito deve ser positivo.");
                return;
            }
    
            // Buscar a conta pelo número e agência
            ContaController contaController = new ContaController();
            Conta conta = contaController.buscarContaPorNumeroEAgencia(numeroConta, agencia);
    
            if (conta != null) {
                // Realizar o depósito, atualizando o saldo
                conta.setSaldo(conta.getSaldo() + valor);
                if (contaController.atualizarConta(conta)) {
                    JOptionPane.showMessageDialog(this, "Depósito realizado com sucesso!\nNovo saldo: R$ " + conta.getSaldo());
                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao realizar depósito.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Conta não encontrada.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Valor inválido. Por favor, insira um valor numérico.");
        }
    }
    
    private void realizarSaque() {
        // Solicitar dados para o saque
        String numeroConta = JOptionPane.showInputDialog("Digite o número da conta:");
        String agencia = JOptionPane.showInputDialog("Digite o número da agência:");
        String valorStr = JOptionPane.showInputDialog("Digite o valor do saque:");
        String senhaAdm = JOptionPane.showInputDialog("Digite a senha de administrador:");
    
        // Verificar se a senha do administrador está correta
        if (!"1234".equals(senhaAdm)) {
            JOptionPane.showMessageDialog(this, "Senha de administrador inválida.");
            return;
        }
    
        try {
            double valor = Double.parseDouble(valorStr);
    
            // Verificar se o valor é positivo
            if (valor <= 0) {
                JOptionPane.showMessageDialog(this, "O valor do saque deve ser positivo.");
                return;
            }
    
            // Buscar a conta pelo número e agência
            ContaController contaController = new ContaController();
            Conta conta = contaController.buscarContaPorNumeroEAgencia(numeroConta, agencia);
    
            if (conta != null) {
                // Verificar se o saldo da conta é suficiente para o saque
                if (conta.getSaldo() >= valor) {
                    conta.setSaldo(conta.getSaldo() - valor); // Deduzir o valor do saque
                    if (contaController.atualizarConta(conta)) {
                        JOptionPane.showMessageDialog(this, "Saque realizado com sucesso!\nNovo saldo: R$ " + conta.getSaldo());
                    } else {
                        JOptionPane.showMessageDialog(this, "Erro ao realizar saque.");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Saldo insuficiente para o saque.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Conta não encontrada.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Valor inválido. Por favor, insira um valor numérico.");
        }
    }

    public static void main(String[] args) {
        new OperacoesViews();
    }
}
