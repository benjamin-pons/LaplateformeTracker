import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        String url = "jdbc:postgresql://localhost:5432/testdb";
        String user = dotenv.get("PGUSER");
        String password = dotenv.get("PGPASSWORD");

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connexion réussie !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}