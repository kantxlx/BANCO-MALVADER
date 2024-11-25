import java.sql.Connection;

import dao.DatabaseConnection;
import view.MenuPrincipalView;  // Importando a classe de View para o Menu Principal

public class Main {
    public static void main(String[] args) {
        try {
            // Tenta conectar ao banco de dados
            Connection connection = DatabaseConnection.getConnection();
            if (connection != null) {
                System.out.println("Conexão bem-sucedida com o banco de dados!");

                // Se a conexão for bem-sucedida, abre a tela do Menu Principal
                new MenuPrincipalView();  // Inicia a tela MenuPrincipalView
            }
        } catch (RuntimeException e) {
            System.out.println("Falha na conexão com o banco de dados.");
            e.printStackTrace();
        }
    }
}
