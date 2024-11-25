package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Conta;

public class ContaDAO {
    // Método para inserir uma nova conta no banco de dados
    public boolean inserir(Conta conta) {
        String sql = "INSERT INTO contas (agencia, numero_conta, tipo, saldo, limite, vencimento, cliente_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, conta.getAgencia());
            stmt.setString(2, conta.getNumeroConta());
            stmt.setString(3, conta.getTipo());
            stmt.setDouble(4, conta.getSaldo());
            stmt.setDouble(5, conta.getLimite());
            stmt.setDate(6, Date.valueOf(conta.getVencimento()));
            stmt.setInt(7, conta.getClienteId());  // Aqui, o cliente_id deve ser atribuído corretamente
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para listar todas as contas
    public List<Conta> listarTodas() {
        String sql = "SELECT * FROM contas";
        List<Conta> contas = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Conta conta = new Conta(
                        rs.getInt("id"),
                        rs.getString("agencia"),
                        rs.getString("numero_conta"),
                        rs.getString("tipo"),
                        rs.getDouble("saldo"),
                        rs.getDouble("limite"),
                        rs.getDate("vencimento").toLocalDate(),
                        rs.getInt("cliente_id")
                );
                contas.add(conta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contas;
    }

    public Conta buscarPorClienteId(int clienteId) {
        String sql = "SELECT * FROM contas WHERE cliente_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, clienteId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Conta encontrada: ID = " + rs.getInt("id"));
                    return new Conta(
                        rs.getInt("id"),
                        rs.getString("agencia"),
                        rs.getString("numero_conta"),
                        rs.getString("tipo"),
                        rs.getDouble("saldo"),
                        rs.getDouble("limite"),
                        rs.getDate("vencimento").toLocalDate(),
                        rs.getInt("cliente_id")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Retorna null se não encontrar a conta
    }
    
    public Conta buscarPorClienteCPF(String cpf) {
        String sql = "SELECT * FROM contas WHERE cliente_id = (SELECT id FROM clientes WHERE cpf = ?)";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Conta(
                        rs.getInt("id"),
                        rs.getString("agencia"),
                        rs.getString("numero_conta"),
                        rs.getString("tipo"),
                        rs.getDouble("saldo"),
                        rs.getDouble("limite"),
                        rs.getDate("vencimento").toLocalDate(),
                        rs.getInt("cliente_id")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Retorna null se não encontrar a conta
    }

    // Método para buscar uma conta pelo número da conta
    public Conta buscarPorNumero(String numeroConta) {
        String sql = "SELECT * FROM contas WHERE numero_conta = ?";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, numeroConta);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Conta(
                            rs.getInt("id"),
                            rs.getString("agencia"),
                            rs.getString("numero_conta"),
                            rs.getString("tipo"),
                            rs.getDouble("saldo"),
                            rs.getDouble("limite"),
                            rs.getDate("vencimento").toLocalDate(),
                            rs.getInt("cliente_id")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Método para verificar se o saldo é suficiente para a transação (depósito ou saque)
    public boolean verificarSaldoSuficiente(String numeroConta, double valor) {
        String sql = "SELECT saldo FROM contas WHERE numero_conta = ?";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, numeroConta);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    double saldoAtual = rs.getDouble("saldo");
                    return saldoAtual >= valor;  // Verifica se o saldo é suficiente
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Método para atualizar o saldo após um depósito ou saque
    public boolean atualizarSaldo(String numeroConta, double novoSaldo) {
        String sql = "UPDATE contas SET saldo = ? WHERE numero_conta = ?";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, novoSaldo);
            stmt.setString(2, numeroConta);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Método para atualizar os dados da conta
    public boolean atualizar(Conta conta) {
        String sql = "UPDATE contas SET saldo = ?, limite = ?, vencimento = ? WHERE numero_conta = ?";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, conta.getSaldo());
            stmt.setDouble(2, conta.getLimite());
            stmt.setDate(3, Date.valueOf(conta.getVencimento()));
            stmt.setString(4, conta.getNumeroConta());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para deletar uma conta
    public boolean deletar(String numeroConta) {
        String sql = "DELETE FROM contas WHERE numero_conta = ?";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, numeroConta);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
