package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dao.DatabaseConnection;
import dao.TransacaoDAO;
import model.Transacao;

public class TransacaoController {
    private TransacaoDAO transacaoDAO;

    public TransacaoController() {
        this.transacaoDAO = new TransacaoDAO();
    }

    public String getExtrato(String numeroConta) {
        String sql = "SELECT t.data_transacao, t.valor, t.tipo " +
                     "FROM transacoes t " +
                     "JOIN contas c ON t.conta_id = c.id " +
                     "WHERE c.numero_conta = ?"; // Verifique se o nome da coluna é realmente 'numero_conta'
        
        StringBuilder extrato = new StringBuilder();
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, numeroConta); // Passa o número da conta para a consulta
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // Usando o nome correto da coluna 'tipo' em vez de 'tipo_transacao'
                    extrato.append("Data: ").append(rs.getTimestamp("data_transacao"))
                           .append(" | Valor: R$ ").append(rs.getDouble("valor"))
                           .append(" | Tipo: ").append(rs.getString("tipo"))
                           .append("\n");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return extrato.toString();
    }

    public boolean registrarTransacao(Transacao transacao) {
        return transacaoDAO.inserir(transacao);
    }

    public List<Transacao> listarTransacoes() {
        return transacaoDAO.listarTodas();
    }

    public List<Transacao> listarTransacoesPorConta(String numeroConta) {
        return transacaoDAO.listarPorConta(numeroConta);
    }
}
