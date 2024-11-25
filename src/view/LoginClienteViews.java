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

import controller.ClienteController;
import controller.ContaController;
import model.Cliente;
import model.Conta;

public class LoginClienteViews extends JFrame {
    private JTextField cpfField;
    private JPasswordField senhaField;

    public LoginClienteViews() {
        setTitle("Banco Malvader - Login Cliente");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        getContentPane().setBackground(Color.WHITE);
        Font font = new Font("Arial", Font.BOLD, 16);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espaçamento entre os componentes

        UIManager.put("Button.background", Color.BLACK);
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("Button.font", font);

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

        JButton loginButton = new JButton("Entrar");
        loginButton.setFont(font);
        loginButton.setPreferredSize(new Dimension(200, 40));
        loginButton.addActionListener(new LoginHandler());
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(loginButton, gbc);

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
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(voltarButton, gbc);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private class LoginHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String cpf = cpfField.getText();
            String senha = new String(senhaField.getPassword());
    
            ClienteController clienteController = new ClienteController();
            ContaController contaController = new ContaController();
    
            // Autentica o cliente
            Cliente cliente = clienteController.autenticarCliente(cpf, senha);
            if (cliente != null) {
                System.out.println("Cliente encontrado: " + cliente.getNome());
                System.out.println("ID do cliente: " + cliente.getId());
    
                // Busca a conta associada ao cliente autenticado pelo cliente_id
                Conta conta = contaController.buscarContaPorClienteId(cliente.getId());
                if (conta != null) {
                    System.out.println("Conta encontrada: Número = " + conta.getNumeroConta());
                    JOptionPane.showMessageDialog(null, "Bem-vindo, " + cliente.getNome() + "!");
                    new MenuClienteView(conta); // Passando o cliente autenticado
                    dispose(); // Fecha a tela de login
                } else {
                    System.out.println("Conta não encontrada para o cliente ID = " + cliente.getId());
                    JOptionPane.showMessageDialog(null, "Conta não encontrada para este cliente.");
                }
            } else {
                System.out.println("CPF ou senha inválidos para CPF: " + cpf);
                JOptionPane.showMessageDialog(null, "CPF ou senha inválidos.");
            }
        }
    }
    

    public static void main(String[] args) {
        new LoginClienteViews();
    }
}
