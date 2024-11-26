package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import dao.ContaDAO;
import dao.DatabaseConnection;
import model.Conta;

public class ContaController {
    private ContaDAO contaDAO;

    public ContaController() {
        this.contaDAO = new ContaDAO();
    }

    public boolean atualizarConta(Conta conta) {
        String sql = "UPDATE contas SET saldo = ? WHERE numeroConta = ?";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, conta.getSaldo());
            stmt.setString(2, conta.getNumeroConta());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Busca uma conta por número de conta e agência
    public Conta buscarContaPorNumeroEAgencia(String numeroConta, String agencia) {
        String sql = "SELECT * FROM contas WHERE numeroConta = ? AND agencia = ?";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, numeroConta);
            stmt.setString(2, agencia);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Conta(
                            rs.getInt("id"),
                            rs.getString("agencia"),
                            rs.getString("numeroConta"),
                            rs.getString("tipo"),
                            rs.getDouble("saldo"),
                            rs.getDouble("limite"),
                            rs.getDate("vencimento") != null ? rs.getDate("vencimento").toLocalDate() : null,
                            rs.getInt("cliente_id")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Abrir uma nova conta
    public boolean abrirConta(Conta conta) {
        return contaDAO.inserir(conta);  // Insere a conta no banco
    }

    // Listar todas as contas
    public List<Conta> listarContas() {
        return contaDAO.listarTodas();  // Lista todas as contas no banco
    }

    // Buscar uma conta pelo número da conta
    public Conta buscarContaPorNumero(String numeroConta) {
        return contaDAO.buscarPorNumero(numeroConta);  // Busca conta por número
    }

    public Conta buscarContaPorClienteId(int clienteId) {
        return contaDAO.buscarPorClienteId(clienteId);  // Busca conta pelo ID do cliente
    }

    // Buscar conta usando o CPF do cliente
    public Conta buscarContaPorClienteCPF(String cpf) {
        return contaDAO.buscarPorClienteCPF(cpf);  // Busca conta pelo CPF do cliente
    }

    public Conta buscarContaPorCPF(String cpf) {
        return contaDAO.buscarContaPorCPF(cpf);  // Agora a conta será buscada pelo CPF do cliente
    }

    // Encerrar uma conta
    public boolean encerrarConta(String numeroConta) {
        return contaDAO.deletar(numeroConta);  // Exclui a conta do banco
    }

    // Verificar se o saldo da conta é suficiente para realizar uma transação (depósito/saque)
    public boolean verificarSaldoSuficiente(String numeroConta, double valor) {
        return contaDAO.verificarSaldoSuficiente(numeroConta, valor);  // Verifica saldo suficiente
    }

    // Atualizar o saldo da conta após uma transação
    public boolean atualizarSaldo(String numeroConta, double novoSaldo) {
        return contaDAO.atualizarSaldo(numeroConta, novoSaldo);  // Atualiza saldo da conta
    }

    // Gerar número de conta único
    public String gerarNumeroConta() {
        // Gerar número de conta único (8 dígitos aleatórios)
        String numeroConta;
        Random rand = new Random();
        do {
            numeroConta = String.format("%08d", rand.nextInt(100000000));  // 8 dígitos aleatórios
        } while (verificarNumeroContaExistente(numeroConta));
        return numeroConta;
    }

    private boolean verificarNumeroContaExistente(String numeroConta) {
        // Verificar se o número de conta gerado já existe no banco de dados
        return contaDAO.buscarPorNumero(numeroConta) != null;
    }
}
