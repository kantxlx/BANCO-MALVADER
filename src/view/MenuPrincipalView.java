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

public class MenuPrincipalView extends JFrame {
    public MenuPrincipalView() {
        // Configurações da janela
        setTitle("Banco Malvader - Menu Principal");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());  // Usando GridBagLayout para uma disposição mais flexível

        // Configuração de cores (fundo branco e botões pretos com texto branco)
        getContentPane().setBackground(Color.WHITE);
        Font font = new Font("Arial", Font.BOLD, 16);

        // GridBagConstraints para controle da posição dos componentes
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);  // Espaçamento entre os componentes

        // Definindo aparência dos botões
        UIManager.put("Button.background", Color.BLACK);
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("Button.font", font);

        // Texto de boas-vindas
        JLabel welcomeLabel = new JLabel("BEM VINDO AO BANCO MALVADER!!!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));  // Fonte maior e mais chamativa
        welcomeLabel.setForeground(Color.BLACK);  // Cor do texto
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;  // O título ocupa as duas colunas
        add(welcomeLabel, gbc);

        // Botão "Funcionário"
        JButton funcionarioButton = new JButton("Funcionário");
        funcionarioButton.setFont(font);
        funcionarioButton.setPreferredSize(new Dimension(200, 40));
        funcionarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuFuncionarioView();  // Abre o menu do funcionário
                dispose();  // Fecha a tela de menu principal
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;  // O botão ocupa as duas colunas
        add(funcionarioButton, gbc);

        // Botão "Cliente"
        JButton clienteButton = new JButton("Cliente");
        clienteButton.setFont(font);
        clienteButton.setPreferredSize(new Dimension(200, 40));
        clienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ClientesView();  // Abre o menu do cliente
                dispose();  // Fecha a tela de menu principal
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;  // O botão ocupa as duas colunas
        add(clienteButton, gbc);

        // Botão "Sair"
        JButton sairButton = new JButton("Sair");
        sairButton.setFont(font);
        sairButton.setPreferredSize(new Dimension(200, 40));
        sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);  // Fecha a aplicação
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;  // O botão ocupa as duas colunas
        add(sairButton, gbc);

        // Centraliza a janela na tela e a torna visível
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MenuPrincipalView();  // Exibe o Menu Principal
    }
}
