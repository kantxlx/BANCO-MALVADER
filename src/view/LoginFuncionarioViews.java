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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import controller.FuncionarioController;

public class LoginFuncionarioViews extends JFrame {
    private JTextField cpfField;
    private JPasswordField senhaField;

    public LoginFuncionarioViews() {
        // Configuração básica da janela
        setTitle("Banco Malvader - Login Funcionário");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

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

        // CPF do Funcionário
        JLabel cpfLabel = new JLabel("CPF:");
        cpfLabel.setFont(font);
        cpfLabel.setForeground(Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(cpfLabel, gbc);

        cpfField = new JTextField();
        cpfField.setFont(font);
        cpfField.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(cpfField, gbc);

        // Senha do Funcionário
        JLabel senhaLabel = new JLabel("Senha:");
        senhaLabel.setFont(font);
        senhaLabel.setForeground(Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(senhaLabel, gbc);

        senhaField = new JPasswordField();
        senhaField.setFont(font);
        senhaField.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(senhaField, gbc);

        // Botão "Entrar"
        JButton loginButton = new JButton("Entrar");
        loginButton.setFont(font);
        loginButton.setPreferredSize(new Dimension(200, 40));
        loginButton.addActionListener(new LoginHandler());
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;  // O botão ocupa as duas colunas
        add(loginButton, gbc);

        // Botão "Voltar" (volta para o Menu Principal)
        JButton voltarButton = new JButton("Voltar");
        voltarButton.setFont(font);
        voltarButton.setPreferredSize(new Dimension(200, 40));
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuPrincipalView();  // Volta para o Menu Principal
                dispose();  // Fecha a tela de login
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

    private class LoginHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String cpf = cpfField.getText();
            String senha = new String(senhaField.getPassword());

            FuncionarioController funcionarioController = new FuncionarioController();
            if (funcionarioController.autenticarFuncionario(cpf, senha)) {
                new MenuFuncionarioView();  // Redireciona para o Menu do Funcionário após o login
                dispose();  // Fecha a tela de login
            } else {
                JOptionPane.showMessageDialog(null, "CPF ou senha inválidos.");
            }
        }
    }

    public static void main(String[] args) {
        new LoginFuncionarioViews();  // Exibe a tela de login para funcionários
    }
}
