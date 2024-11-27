package dao;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    public boolean gerarRelatorioMovimentacoesCSV() {
        String sql = """
            SELECT t.id, t.conta_id, t.tipo, t.valor, DATE_FORMAT(t.data_transacao, '%d/%m/%Y %H:%i:%s') AS data_formatada, 
                c.numero_conta, c.agencia
            FROM transacoes t
            JOIN contas c ON t.conta_id = c.id
        """;

        String arquivoCSV = "Relatorio_Movimentacoes.csv";

        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            FileWriter writer = new FileWriter(arquivoCSV)) {

            // Escrever cabeçalho no arquivo CSV
            String[] colunas = {"ID", "Conta ID", "Número da Conta", "Agência", "Tipo", "Valor", "Data da Transação"};
            writer.append(String.join(",", colunas)).append("\n");

            // Preencher os dados das transações
            while (rs.next()) {
                int id = rs.getInt("id");
                int contaId = rs.getInt("conta_id");
                String numeroConta = rs.getString("numero_conta");
                String agencia = rs.getString("agencia");
                String tipo = rs.getString("tipo");
                double valor = rs.getDouble("valor");
                String dataTransacao = rs.getString("data_formatada");

                writer.append(String.valueOf(id)).append(",")
                    .append(String.valueOf(contaId)).append(",")
                    .append(numeroConta).append(",")
                    .append(agencia).append(",")
                    .append(tipo).append(",")
                    .append(String.format("%.2f", valor)).append(",")
                    .append(dataTransacao).append("\n");
            }

            System.out.println("Relatório gerado com sucesso no arquivo: " + arquivoCSV);
            return true;

        } catch (SQLException | IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
