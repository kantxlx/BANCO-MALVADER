package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Transacao;

public class TransacaoDAO {
    public boolean inserir(Transacao transacao) {
        String sql = "INSERT INTO transacoes (conta_id, tipo, valor) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, transacao.getContaId());
            stmt.setString(2, transacao.getTipo());
            stmt.setDouble(3, transacao.getValor());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Transacao> listarTodas() {
        String sql = "SELECT * FROM transacoes";
        List<Transacao> transacoes = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Transacao transacao = new Transacao(
                        rs.getInt("id"),
                        rs.getInt("conta_id"),
                        rs.getString("tipo"),
                        rs.getDouble("valor"),
                        rs.getTimestamp("data_transacao").toLocalDateTime()
                );
                transacoes.add(transacao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transacoes;
    }

    public List<Transacao> listarPorConta(String numeroConta) {
        String sql = "SELECT t.* FROM transacoes t " +
                     "INNER JOIN contas c ON t.conta_id = c.id " +
                     "WHERE c.numero_conta = ?";
        List<Transacao> transacoes = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, numeroConta);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Transacao transacao = new Transacao(
                            rs.getInt("id"),
                            rs.getInt("conta_id"),
                            rs.getString("tipo"),
                            rs.getDouble("valor"),
                            rs.getTimestamp("data_transacao").toLocalDateTime()
                    );
                    transacoes.add(transacao);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transacoes;
    }
}
