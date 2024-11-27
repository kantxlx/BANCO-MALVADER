package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.DatabaseConnection;
import dao.TransacaoDAO;

public class TransacaoController {
    private TransacaoDAO transacaoDAO;

    public TransacaoController() {
        this.transacaoDAO = new TransacaoDAO();
    }

    public String getExtrato(String numeroConta) {
        String sql = """
            SELECT t.tipo, t.valor, DATE_FORMAT(t.data_transacao, '%d/%m/%Y %H:%i') AS data_formatada
            FROM transacoes t
            INNER JOIN contas c ON t.conta_id = c.id
            WHERE c.numero_conta = ?
            ORDER BY t.data_transacao DESC
        """;
    
        StringBuilder extrato = new StringBuilder();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            stmt.setString(1, numeroConta);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String tipo = rs.getString("tipo");
                    double valor = rs.getDouble("valor");
                    String data = rs.getString("data_formatada");
                    extrato.append(tipo).append(" R$").append(String.format("%.2f", valor)).append(" ").append(data).append("\n");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro ao carregar extrato.";
        }
    
        if (extrato.length() == 0) {
            return "Nenhuma transação encontrada.";
        }
    
        return extrato.toString();
    }
    

    public boolean registrarTransacao(int contaId, String tipo, double valor) {
        String sql = "INSERT INTO transacoes (conta_id, tipo, valor) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, contaId);
            stmt.setString(2, tipo);
            stmt.setDouble(3, valor);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean gerarRelatorioMovimentacoesCSV() {
        return transacaoDAO.gerarRelatorioMovimentacoesCSV();  // Chama o método que gera o CSV
    }
}
