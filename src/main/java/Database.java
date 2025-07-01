import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

public class Database {
    private String user;
    private String password;
    private String host = "localhost";
    private int port = 5432;
    private String dbName = "laplateformetracker";
    private Dotenv dotenv;

    public Database() {
        dotenv = Dotenv.load();
        user = dotenv.get("PGUSER");
        password = dotenv.get("PGPASSWORD");
    }

    public Connection getConnection() throws SQLException {
        return getConnection(dbName);
    }

    public Connection getConnection(String dbName) throws SQLException {
        String url = "jdbc:postgresql://" + host + ":" + port + "/" + dbName;
        return DriverManager.getConnection(url, user, password);
    }

    public void createDatabase(String dbName) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Le driver PostgreSQL n'a pas été trouvé.");
        }

        String url = "jdbc:postgresql://" + host + ":" + port + "/postgres";
        try (Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement()) {
            String sql = "CREATE DATABASE " + dbName;
            stmt.executeUpdate(sql);
            System.out.println("Base de données créée !");
        } catch (SQLException e) {
            if (e.getMessage().contains("already exists")) {
                System.out.println("La base de données existe déjà.");
            } else {
                System.out.println("Une erreur est survenue lors de la création de la base de données.");
                System.out.println(e.getMessage());
            }
        }
    }

    public void createTableUser() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Le driver PostgreSQL n'a pas été trouvé.");
        }

        String url = "jdbc:postgresql://" + host + ":" + port + "/postgres";
        try (Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS users (" +
                        "id SERIAL PRIMARY KEY, " +
                         "username VARCHAR(50) UNIQUE NOT NULL, " +
                         "password VARCHAR(50) NOT NULL" +
                         ")";
            stmt.executeUpdate(sql);
            System.out.println("Table users créée !");
        } catch (SQLException e) {
            System.out.println("Une erreur est survenue lors de la création de la table users.");
            System.out.println(e.getMessage());
        }
    }



    public void createTableStudent() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Le driver PostgreSQL n'a pas été trouvé.");
        }

        String url = "jdbc:postgresql://" + host + ":" + port + "/" + dbName;
        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS student (" +
                         "id SERIAL PRIMARY KEY, " +    
                         "first_name VARCHAR(100), " +
                         "last_name VARCHAR(100), " +
                         "age INT, " +
                         "grade INT )";
            stmt.executeUpdate(sql);
            System.out.println("Table student créée !");
        } catch (SQLException e) {
            System.out.println("Une erreur est survenue lors de la création de la table.");
            System.out.println(e.getMessage());
        }
    }
}
