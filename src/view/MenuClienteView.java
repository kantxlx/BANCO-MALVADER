package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import controller.ContaController;
import controller.TransacaoController; // Assumindo que exista um controller de transações
import model.Conta;

public class MenuClienteView extends JFrame {
    private Conta conta;
    private ContaController contaController;

    public MenuClienteView(Conta conta) {
        if (conta == null) {
            JOptionPane.showMessageDialog(null, "Conta não encontrada.");
            dispose();
            return;
        }

        this.conta = conta;
        this.contaController = new ContaController();

        setTitle("Banco Malvader - Menu Cliente");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());

        getContentPane().setBackground(Color.WHITE);
        Font font = new Font("Arial", Font.BOLD, 16);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        UIManager.put("Button.background", Color.BLACK);
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("Button.font", font);

        JLabel introLabel = new JLabel("Escolha uma opção:");
        introLabel.setFont(font);
        introLabel.setForeground(Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(introLabel, gbc);

        // Botão "Ver Saldo"
        JButton saldoButton = new JButton("Ver Saldo");
        saldoButton.setFont(font);
        saldoButton.setPreferredSize(new Dimension(200, 40));
        saldoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarConta();
                JOptionPane.showMessageDialog(null, "Seu saldo atualizado é: R$ " + conta.getSaldo());
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(saldoButton, gbc);

        // Botão "Fazer Depósito"
        JButton depositoButton = new JButton("Fazer Depósito");
        depositoButton.setFont(font);
        depositoButton.setPreferredSize(new Dimension(200, 40));
        depositoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String deposito = JOptionPane.showInputDialog("Digite o valor a ser depositado:");
                try {
                    double valorDeposito = Double.parseDouble(deposito);
                    if (valorDeposito <= 0) {
                        JOptionPane.showMessageDialog(null, "Valor de depósito inválido.");
                    } else {
                        atualizarConta();
                        conta.setSaldo(conta.getSaldo() + valorDeposito);
                        contaController.atualizarSaldo(conta.getNumeroConta(), conta.getSaldo());
                        JOptionPane.showMessageDialog(null, "Depósito de R$ " + valorDeposito + " realizado com sucesso!");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, insira um valor válido para o depósito.");
                }
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(depositoButton, gbc);

        // Botão "Fazer Saque"
        JButton saqueButton = new JButton("Fazer Saque");
        saqueButton.setFont(font);
        saqueButton.setPreferredSize(new Dimension(200, 40));
        saqueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String saque = JOptionPane.showInputDialog("Digite o valor a ser sacado:");
                try {
                    double valorSaque = Double.parseDouble(saque);
                    if (valorSaque <= 0) {
                        JOptionPane.showMessageDialog(null, "Valor de saque inválido.");
                    } else {
                        atualizarConta();
                        if (conta.getSaldo() >= valorSaque) {
                            conta.setSaldo(conta.getSaldo() - valorSaque);
                            contaController.atualizarSaldo(conta.getNumeroConta(), conta.getSaldo());
                            JOptionPane.showMessageDialog(null, "Saque de R$ " + valorSaque + " realizado com sucesso!");
                        } else {
                            JOptionPane.showMessageDialog(null, "Saldo insuficiente para realizar o saque.");
                        }
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, insira um valor válido para o saque.");
                }
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(saqueButton, gbc);

        // Botão "Ver Extrato"
        JButton extratoButton = new JButton("Ver Extrato");
        extratoButton.setFont(font);
        extratoButton.setPreferredSize(new Dimension(200, 40));
        extratoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Exibe o extrato de transações
                TransacaoController transacaoController = new TransacaoController();
                String extrato = transacaoController.getExtrato(conta.getNumeroConta());
                JOptionPane.showMessageDialog(null, "Extrato:\n" + extrato);
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(extratoButton, gbc);

        // Botão "Ver Dados da Conta"
        JButton dadosButton = new JButton("Ver Dados da Conta");
        dadosButton.setFont(font);
        dadosButton.setPreferredSize(new Dimension(200, 40));
        dadosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarConta();
                JOptionPane.showMessageDialog(null, "Dados da Conta:\nAgência: " + conta.getAgencia() +
                        "\nNúmero da Conta: " + conta.getNumeroConta());
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        add(dadosButton, gbc);

        // Botão "Voltar"
        JButton voltarButton = new JButton("Voltar");
        voltarButton.setFont(font);
        voltarButton.setPreferredSize(new Dimension(200, 40));
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuPrincipalView();
                dispose();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        add(voltarButton, gbc);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void atualizarConta() {
        Conta contaAtualizada = contaController.buscarContaPorNumero(conta.getNumeroConta());
        if (contaAtualizada != null) {
            conta.setSaldo(contaAtualizada.getSaldo());
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar os dados da conta.");
        }
    }
}
