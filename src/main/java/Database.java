import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

public class Database {
    private String user = "postgres";
    private String password = "password";
    private String host = "localhost";
    private int port = 5432;

    public void createDatabase(String dbName) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Le driver PostgreSQL n'a pas été trouvé.");
        }

        String url = "jdbc:postgresql://localhost:5432/LaplateformeTracker";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement()) {
            String sql = "CREATE DATABASE LaplateformeTracker";
            stmt.executeUpdate(sql);
            System.out.println("Base de données créée !");
        } catch (SQLException e) {
            System.out.println("Une erreur est survenue lors de la création de la base de données.");
            System.out.println(e.getMessage()); 
        }
    }

    public void createTable(String dbName) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Le driver PostgreSQL n'a pas été trouvé.");
        }
        String url = "jdbc:postgresql://localhost:5432/LaplateformeTracker";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS users (" +
                         "id SERIAL PRIMARY KEY, " +
                         "first_name VARCHAR(100), " +
                         "last_name VARCHAR(100), " +   
                         "age int, "+
                         "grade VARCHAR(50) )" ;
            stmt.executeUpdate(sql);
            System.out.println("Table créée !");
        } catch (SQLException e) {
            System.out.println("Une erreur est survenue lors de la création de la table.");
            System.out.println(e.getMessage()); 
        }
    }


}
