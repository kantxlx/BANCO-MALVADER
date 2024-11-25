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
import javax.swing.UIManager;

public class ClientesView extends JFrame {
    public ClientesView() {
        // Configurações básicas da janela
        setTitle("Banco Malvader - Área de Cliente");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());  // Usando GridBagLayout para um layout flexível

        // Configuração de cores (branco para fundo e preto para os botões)
        getContentPane().setBackground(Color.WHITE);
        Font font = new Font("Arial", Font.BOLD, 16);
        
        // Define a aparência do botão (fundo preto e texto branco)
        UIManager.put("Button.background", Color.BLACK);
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("Button.font", font);

        // GridBagConstraints para controle da posição dos componentes
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);  // Espaçamento entre os componentes

        // Texto de introdução
        JLabel introLabel = new JLabel("Escolha uma opção:");
        introLabel.setFont(font);
        introLabel.setForeground(Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // O rótulo ocupa as duas colunas
        add(introLabel, gbc);

        // Botão "Já tenho conta" (login)
        JButton loginButton = new JButton("Já tenho conta");
        loginButton.setFont(font);
        loginButton.setPreferredSize(new Dimension(200, 40));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginClienteViews();  // Abre a tela de login do cliente
                dispose();  // Fecha a tela de opções do cliente
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2; // O botão ocupa as duas colunas
        add(loginButton, gbc);

        // Botão "Não tenho conta ainda" (cadastro)
        JButton cadastroButton = new JButton("Não tenho conta ainda");
        cadastroButton.setFont(font);
        cadastroButton.setPreferredSize(new Dimension(200, 40));
        cadastroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CadastroClienteView();  // Abre a tela de cadastro do cliente
                dispose();  // Fecha a tela de opções do cliente
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2; // O botão ocupa as duas colunas
        add(cadastroButton, gbc);

        // Botão "Voltar" (volta para o Menu Principal)
        JButton voltarButton = new JButton("Voltar");
        voltarButton.setFont(font);
        voltarButton.setPreferredSize(new Dimension(200, 40));
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuPrincipalView();  // Volta para o menu principal
                dispose();  // Fecha a tela de opções do cliente
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2; // O botão ocupa as duas colunas
        add(voltarButton, gbc);

        // Centraliza a janela na tela e a torna visível
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new ClientesView();  // Exibe a tela de opções para o cliente
    }
}
