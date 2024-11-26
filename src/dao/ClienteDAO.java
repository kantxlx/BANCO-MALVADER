package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Cliente;

public class ClienteDAO {
    public boolean inserir(Cliente cliente) {
        String sql = "INSERT INTO clientes (nome, cpf, data_nascimento, telefone, endereco, senha) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setDate(3, Date.valueOf(cliente.getDataNascimento()));
            stmt.setString(4, cliente.getTelefone());
            stmt.setString(5, cliente.getEndereco());
            stmt.setString(6, cliente.getSenha());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Cliente> listarTodos() {
        String sql = "SELECT * FROM clientes";
        List<Cliente> clientes = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                // Criando o cliente com os dados do banco
                Cliente cliente = new Cliente(
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getDate("data_nascimento").toLocalDate(),
                        rs.getString("telefone"),
                        rs.getString("endereco"),
                        rs.getString("senha")
                );
                cliente.setId(rs.getInt("id"));  // Atribuindo o ID após a criação do cliente
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }    
    
    public Cliente autenticarCliente(String cpf, String senha) {
        String sql = "SELECT * FROM clientes WHERE cpf = ? AND senha = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            stmt.setString(2, senha);
    
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Criando o cliente com os dados do banco
                    Cliente cliente = new Cliente(
                            rs.getString("nome"),
                            rs.getString("cpf"),
                            rs.getDate("data_nascimento").toLocalDate(),
                            rs.getString("telefone"),
                            rs.getString("endereco"),
                            rs.getString("senha")
                    );
                    cliente.setId(rs.getInt("id"));  // Atribuindo o ID após a criação do cliente
                    return cliente;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Caso o cliente não seja encontrado
    }    
    
    public Cliente buscarPorCPF(String cpf) {
        String sql = "SELECT * FROM clientes WHERE cpf = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Criando o cliente com os dados retornados
                    Cliente cliente = new Cliente(
                            rs.getString("nome"),
                            rs.getString("cpf"),
                            rs.getDate("data_nascimento").toLocalDate(),
                            rs.getString("telefone"),
                            rs.getString("endereco"),
                            rs.getString("senha")
                    );
                    cliente.setId(rs.getInt("id"));  // Atribuindo o ID após a criação do cliente
                    return cliente;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Retorna null se o cliente não for encontrado
    }    

    public boolean atualizar(Cliente cliente) {
        String sql = "UPDATE clientes SET nome = ?, data_nascimento = ?, telefone = ?, endereco = ?, senha = ? WHERE cpf = ?";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNome());
            stmt.setDate(2, Date.valueOf(cliente.getDataNascimento()));
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getEndereco());
            stmt.setString(5, cliente.getSenha());
            stmt.setString(6, cliente.getCpf());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deletar(String cpf) {
        String sql = "DELETE FROM clientes WHERE cpf = ?";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
