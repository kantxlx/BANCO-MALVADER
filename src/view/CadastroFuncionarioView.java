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
import model.Funcionario;

public class CadastroFuncionarioView extends JFrame {
    public CadastroFuncionarioView() {
        // Configurações da janela
        setTitle("Banco Malvader - Cadastro de Funcionário");
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

        // Nome do Funcionário
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

        // CPF do Funcionário
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

        // Cargo do Funcionário
        JLabel cargoLabel = new JLabel("Cargo:");
        cargoLabel.setFont(font);
        cargoLabel.setForeground(Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(cargoLabel, gbc);

        JTextField cargoField = new JTextField();
        cargoField.setFont(font);
        cargoField.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(cargoField, gbc);

        // Telefone do Funcionário
        JLabel telefoneLabel = new JLabel("Telefone:");
        telefoneLabel.setFont(font);
        telefoneLabel.setForeground(Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 10, 5, 10);
        add(telefoneLabel, gbc);

        JTextField telefoneField = new JTextField();
        telefoneField.setFont(font);
        telefoneField.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.insets = new Insets(10, 10, 5, 10);
        add(telefoneField, gbc);

        // Endereço do Funcionário
        JLabel enderecoLabel = new JLabel("Endereço:");
        enderecoLabel.setFont(font);
        enderecoLabel.setForeground(Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 10, 5, 10);
        add(enderecoLabel, gbc);

        JTextField enderecoField = new JTextField();
        enderecoField.setFont(font);
        enderecoField.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.insets = new Insets(10, 10, 5, 10);
        add(enderecoField, gbc);

        // Senha do Funcionário
        JLabel senhaLabel = new JLabel("Senha:");
        senhaLabel.setFont(font);
        senhaLabel.setForeground(Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 10, 5, 10);
        add(senhaLabel, gbc);

        JPasswordField senhaField = new JPasswordField();
        senhaField.setFont(font);
        senhaField.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.insets = new Insets(10, 10, 5, 10);
        add(senhaField, gbc);

        // Botão "Cadastrar"
        JButton cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.setFont(font);
        cadastrarButton.setPreferredSize(new Dimension(200, 40));
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Captura os dados dos campos
                String nome = nomeField.getText();
                String cpf = cpfField.getText();
                String cargo = cargoField.getText();
                String senha = new String(senhaField.getPassword());
                String telefone = telefoneField.getText();
                String endereco = enderecoField.getText();

                // Validação simples
                if (nome.isEmpty() || cpf.isEmpty() || cargo.isEmpty() || senha.isEmpty() || telefone.isEmpty() || endereco.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos.");
                    return;
                }

                // Criação do objeto Funcionario
                Funcionario funcionario = new Funcionario(0, nome, cpf, cargo, senha, telefone, endereco);

                // Chama o método para cadastrar o funcionário
                FuncionarioController controller = new FuncionarioController();
                if (controller.cadastrarFuncionario(funcionario)) {
                    JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso!");
                    // Após o sucesso, volta para o MenuFuncionarioView
                    new MenuFuncionarioView();  // Abre o menu de funcionário novamente
                    dispose();  // Fecha a janela de cadastro de funcionário
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar funcionário.");
                }
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(15, 10, 10, 10);
        add(cadastrarButton, gbc);

        // Botão "Cancelar"
        JButton cancelarButton = new JButton("Cancelar");
        cancelarButton.setFont(font);
        cancelarButton.setPreferredSize(new Dimension(200, 40));
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuFuncionarioView();  // Volta para o Menu de Funcionário
                dispose();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 7; // Coloca o botão "Cancelar" na linha 7, logo abaixo do "Cadastrar"
        gbc.gridwidth = 2;  // O botão ocupa as duas colunas
        gbc.insets = new Insets(10, 10, 10, 10); // Ajustando o espaçamento
        add(cancelarButton, gbc);

        // Centraliza a janela na tela e a torna visível
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new CadastroFuncionarioView();  // Exibe a tela de cadastro de funcionário
    }
}
