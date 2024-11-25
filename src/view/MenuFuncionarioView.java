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

import controller.FuncionarioController;

public class MenuFuncionarioView extends JFrame {

    public MenuFuncionarioView() {
        // Configurações básicas da janela
        setTitle("Banco Malvader - Menu Funcionário");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());  // Usando GridBagLayout para um layout flexível

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

        // Texto de introdução
        JLabel introLabel = new JLabel("Escolha uma opção:");
        introLabel.setFont(font);
        introLabel.setForeground(Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // O rótulo ocupa as duas colunas
        add(introLabel, gbc);

        // Botão "Cadastrar Funcionário"
        JButton cadastroButton = new JButton("Cadastrar Funcionário");
        cadastroButton.setFont(font);
        cadastroButton.setPreferredSize(new Dimension(200, 40));
        cadastroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CadastroFuncionarioView();  // Abre a tela de cadastro de funcionário
                dispose();  // Fecha a tela de menu de funcionário
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;  // O botão ocupa as duas colunas
        add(cadastroButton, gbc);

        // Botão "Fazer Login"
        JButton loginButton = new JButton("Fazer Login");
        loginButton.setFont(font);
        loginButton.setPreferredSize(new Dimension(200, 40));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Realiza o login do funcionário
                String cpf = JOptionPane.showInputDialog("Digite o CPF:");
                String senha = JOptionPane.showInputDialog("Digite a Senha:");

                FuncionarioController funcionarioController = new FuncionarioController();
                if (funcionarioController.autenticarFuncionario(cpf, senha)) {
                    // Se o login for bem-sucedido, abre a tela de operações
                    new OperacoesViews();  
                    dispose();  // Fecha o menu de funcionário
                } else {
                    // Exibe mensagem de erro se as credenciais estiverem incorretas
                    JOptionPane.showMessageDialog(null, "CPF ou senha inválidos.");
                }
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;  // O botão ocupa as duas colunas
        add(loginButton, gbc);

        // Botão "Voltar"
        JButton voltarButton = new JButton("Voltar");
        voltarButton.setFont(font);
        voltarButton.setPreferredSize(new Dimension(200, 40));
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuPrincipalView();  // Volta para o menu inicial
                dispose();  // Fecha a tela de menu de funcionário
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;  // O botão ocupa as duas colunas
        add(voltarButton, gbc);

        // Centraliza a janela na tela e a torna visível
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MenuFuncionarioView();  // Exibe o menu de opções para o funcionário
    }
}
