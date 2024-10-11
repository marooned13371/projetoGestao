package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Classe de conexão para o banco de dados mysql, mudar credenciais caso necessite
public class DatabaseConnection {
    // Coloque o endereço IP e porta, logo depois o banco de dados que deseja se conectar
    private static final String URL = "jdbc:mysql://localhost:3306/db_cadastro_clientes"; 
    private static final String USER = "DBA"; // Padrão: "root"
    private static final String PASSWORD = "12345"; // Padrão, ""

    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
