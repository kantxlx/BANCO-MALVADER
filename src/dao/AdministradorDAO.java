package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdministradorDAO {
    public boolean autenticarAdministrador(String senha) {
        String sql = "SELECT * FROM administradores WHERE senha = ?";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, senha);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next(); // Retorna true se a senha for válida
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
