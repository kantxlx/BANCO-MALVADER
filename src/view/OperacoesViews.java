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
import javax.swing.JTextField;
import javax.swing.UIManager;

public class OperacoesViews extends JFrame {

    public OperacoesViews() {
        // Configurações básicas da janela
        setTitle("Banco Malvader - Operações");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());  // Usando GridBagLayout para uma disposição mais flexível

        // Configuração de cores (branco para fundo e preto para os botões)
        getContentPane().setBackground(Color.WHITE);
        Font font = new Font("Arial", Font.BOLD, 16);

        // GridBagConstraints para controle da posição dos componentes
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);  // Espaçamento entre os componentes

        // Define a aparência do botão (fundo preto e texto branco)
        UIManager.put("Button.background", Color.BLACK);
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("Button.font", font);

        // Título de boas-vindas
        JLabel titleLabel = new JLabel("Escolha uma operação:");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));  // Fonte maior e mais chamativa
        titleLabel.setForeground(Color.BLACK);  // Cor do texto
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // O título ocupa as duas colunas
        add(titleLabel, gbc);

        // Botão "Abrir Conta"
        JButton abrirContaButton = new JButton("Abrir Conta");
        abrirContaButton.setFont(font);
        abrirContaButton.setPreferredSize(new Dimension(200, 40));
        abrirContaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirConta();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;  // O botão ocupa as duas colunas
        add(abrirContaButton, gbc);

        // Botão "Realizar Depósito"
        JButton realizarDepositoButton = new JButton("Realizar Depósito");
        realizarDepositoButton.setFont(font);
        realizarDepositoButton.setPreferredSize(new Dimension(200, 40));
        realizarDepositoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarDeposito();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;  // O botão ocupa as duas colunas
        add(realizarDepositoButton, gbc);

        // Botão "Realizar Saque"
        JButton realizarSaqueButton = new JButton("Realizar Saque");
        realizarSaqueButton.setFont(font);
        realizarSaqueButton.setPreferredSize(new Dimension(200, 40));
        realizarSaqueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarSaque();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;  // O botão ocupa as duas colunas
        add(realizarSaqueButton, gbc);

        // Botão "Voltar"
        JButton voltarButton = new JButton("Voltar");
        voltarButton.setFont(font);
        voltarButton.setPreferredSize(new Dimension(200, 40));
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();  // Fecha a tela de operações
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;  // O botão ocupa as duas colunas
        add(voltarButton, gbc);

        // Centraliza a janela na tela e a torna visível
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void abrirConta() {
        // Função para abrir a conta
        JFrame frame = new JFrame("Abrir Conta");
        frame.setSize(400, 500);
        frame.setLayout(new GridBagLayout());  // Usando GridBagLayout para melhor distribuição dos campos

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);  // Espaçamento entre os componentes

        // Definindo cores e fonte
        Font font = new Font("Arial", Font.BOLD, 16);
        frame.getContentPane().setBackground(Color.WHITE);

        // Tipo da Conta
        JLabel tipoLabel = new JLabel("Tipo (CP/CC):");
        tipoLabel.setFont(font);
        tipoLabel.setForeground(Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = 0;
        frame.add(tipoLabel, gbc);

        JTextField tipoField = new JTextField();
        tipoField.setFont(font);
        tipoField.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        gbc.gridy = 0;
        frame.add(tipoField, gbc);

        // Nome do Cliente
        JLabel nomeLabel = new JLabel("Nome do Cliente:");
        nomeLabel.setFont(font);
        nomeLabel.setForeground(Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = 1;
        frame.add(nomeLabel, gbc);

        JTextField nomeField = new JTextField();
        nomeField.setFont(font);
        nomeField.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        gbc.gridy = 1;
        frame.add(nomeField, gbc);

        // CPF do Cliente
        JLabel cpfLabel = new JLabel("CPF:");
        cpfLabel.setFont(font);
        cpfLabel.setForeground(Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = 2;
        frame.add(cpfLabel, gbc);

        JTextField cpfField = new JTextField();
        cpfField.setFont(font);
        cpfField.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        gbc.gridy = 2;
        frame.add(cpfField, gbc);

        // Saldo Inicial
        JLabel saldoLabel = new JLabel("Saldo Inicial:");
        saldoLabel.setFont(font);
        saldoLabel.setForeground(Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = 3;
        frame.add(saldoLabel, gbc);

        JTextField saldoField = new JTextField();
        saldoField.setFont(font);
        saldoField.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        gbc.gridy = 3;
        frame.add(saldoField, gbc);

        // Botão Criar Conta
        JButton criarButton = new JButton("Criar Conta");
        criarButton.setFont(font);
        criarButton.setPreferredSize(new Dimension(200, 40));
        criarButton.addActionListener(e -> {
            // Lógica para criação da conta
            JOptionPane.showMessageDialog(frame, "Conta criada com sucesso!");
            frame.dispose();
        });
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        frame.add(criarButton, gbc);

        // Centraliza e exibe a tela
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void realizarDeposito() {
        // Função para realizar o depósito
        JFrame frame = new JFrame("Realizar Depósito");
        frame.setSize(400, 300);
        frame.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        Font font = new Font("Arial", Font.BOLD, 16);
        frame.getContentPane().setBackground(Color.WHITE);

        JLabel contaLabel = new JLabel("Número da Conta:");
        contaLabel.setFont(font);
        contaLabel.setForeground(Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = 0;
        frame.add(contaLabel, gbc);

        JTextField contaField = new JTextField();
        contaField.setFont(font);
        contaField.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        gbc.gridy = 0;
        frame.add(contaField, gbc);

        JLabel valorLabel = new JLabel("Valor do Depósito:");
        valorLabel.setFont(font);
        valorLabel.setForeground(Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = 1;
        frame.add(valorLabel, gbc);

        JTextField valorField = new JTextField();
        valorField.setFont(font);
        valorField.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        gbc.gridy = 1;
        frame.add(valorField, gbc);

        JButton depositarButton = new JButton("Depositar");
        depositarButton.setFont(font);
        depositarButton.setPreferredSize(new Dimension(200, 40));
        depositarButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Depósito realizado com sucesso!"));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        frame.add(depositarButton, gbc);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void realizarSaque() {
        // Função para realizar o saque
        JFrame frame = new JFrame("Realizar Saque");
        frame.setSize(400, 300);
        frame.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        Font font = new Font("Arial", Font.BOLD, 16);
        frame.getContentPane().setBackground(Color.WHITE);

        JLabel contaLabel = new JLabel("Número da Conta:");
        contaLabel.setFont(font);
        contaLabel.setForeground(Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = 0;
        frame.add(contaLabel, gbc);

        JTextField contaField = new JTextField();
        contaField.setFont(font);
        contaField.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        gbc.gridy = 0;
        frame.add(contaField, gbc);

        JLabel valorLabel = new JLabel("Valor do Saque:");
        valorLabel.setFont(font);
        valorLabel.setForeground(Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = 1;
        frame.add(valorLabel, gbc);

        JTextField valorField = new JTextField();
        valorField.setFont(font);
        valorField.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        gbc.gridy = 1;
        frame.add(valorField, gbc);

        JButton sacarButton = new JButton("Sacar");
        sacarButton.setFont(font);
        sacarButton.setPreferredSize(new Dimension(200, 40));
        sacarButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Saque realizado com sucesso!"));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        frame.add(sacarButton, gbc);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new OperacoesViews();  // Exibe a tela de operações
    }
}
