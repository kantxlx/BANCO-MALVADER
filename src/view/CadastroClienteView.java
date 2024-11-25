package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import controller.ClienteController;
import model.Cliente;

public class CadastroClienteView extends JFrame {
    public CadastroClienteView() {
        // Configurações da janela
        setTitle("Banco Malvader - Cadastro de Cliente");
        setSize(400, 500);
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

        // Nome do Cliente
        JLabel nomeLabel = new JLabel("Nome:");
        nomeLabel.setFont(font);
        nomeLabel.setForeground(Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(nomeLabel, gbc);

        JTextField nomeField = new JTextField();
        nomeField.setFont(font);
        nomeField.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(nomeField, gbc);

        // CPF do Cliente
        JLabel cpfLabel = new JLabel("CPF:");
        cpfLabel.setFont(font);
        cpfLabel.setForeground(Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(cpfLabel, gbc);

        JTextField cpfField = new JTextField();
        cpfField.setFont(font);
        cpfField.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(cpfField, gbc);

        // Telefone do Cliente
        JLabel telefoneLabel = new JLabel("Telefone:");
        telefoneLabel.setFont(font);
        telefoneLabel.setForeground(Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(telefoneLabel, gbc);

        JTextField telefoneField = new JTextField();
        telefoneField.setFont(font);
        telefoneField.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(telefoneField, gbc);

        // Endereço do Cliente
        JLabel enderecoLabel = new JLabel("Endereço:");
        enderecoLabel.setFont(font);
        enderecoLabel.setForeground(Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(enderecoLabel, gbc);

        JTextField enderecoField = new JTextField();
        enderecoField.setFont(font);
        enderecoField.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(enderecoField, gbc);

        // Senha do Cliente
        JLabel senhaLabel = new JLabel("Senha:");
        senhaLabel.setFont(font);
        senhaLabel.setForeground(Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(senhaLabel, gbc);

        JPasswordField senhaField = new JPasswordField();
        senhaField.setFont(font);
        senhaField.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(senhaField, gbc);

        // Botão "Cadastrar"
        JButton cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.setFont(font);
        cadastrarButton.setPreferredSize(new Dimension(200, 40));
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validação de dados do cliente
                if (nomeField.getText().isEmpty() || cpfField.getText().isEmpty() || telefoneField.getText().isEmpty() || enderecoField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.");
                    return;
                }

                // Criação do cliente
                Cliente cliente = new Cliente(
                        0, // id
                        nomeField.getText(),
                        cpfField.getText(),
                        LocalDate.now(), // Data fictícia, você pode adicionar um campo para a data de nascimento
                        telefoneField.getText(),
                        enderecoField.getText(),
                        new String(senhaField.getPassword())
                );

                // Cadastro do cliente
                ClienteController clienteController = new ClienteController();
                if (clienteController.cadastrarCliente(cliente)) {
                    JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
                    dispose();  // Fecha a tela de cadastro
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao realizar cadastro.");
                }
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;  // O botão ocupa as duas colunas
        add(cadastrarButton, gbc);

        // Botão "Cancelar"
        JButton cancelarButton = new JButton("Cancelar");
        cancelarButton.setFont(font);
        cancelarButton.setPreferredSize(new Dimension(200, 40));
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ClientesView();  // Volta para a tela anterior ou para o menu de cliente
                dispose();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;  // O botão ocupa as duas colunas
        add(cancelarButton, gbc);

        // Centraliza a janela na tela e a torna visível
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new CadastroClienteView();  // Exibe a tela de cadastro de cliente
    }
}
