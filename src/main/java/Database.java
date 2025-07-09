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
            System.out.println("PostgreSQL driver not found.");
        }

        String url = "jdbc:postgresql://" + host + ":" + port + "/" + "postgres"; 
        try (Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement()) {
            String sql = "CREATE DATABASE " + dbName;
            stmt.executeUpdate(sql);
            System.out.println("Database " + dbName + " created successfully!");
        } catch (SQLException e) {
            if (e.getMessage().contains("already exists")) {
                System.out.println("Database " + dbName + " already exists.");
            } else {
                System.out.println("An error occurred while creating the database.");
                System.out.println(e.getMessage());
            }
        }
    }

    public void createTableUsers() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL driver not found.");
        }

        String url = "jdbc:postgresql://" + host + ":" + port + "/" + dbName;
        try (Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS users (" +
                        "id SERIAL PRIMARY KEY, " +
                         "username VARCHAR(50) UNIQUE NOT NULL, " +
                         "password VARCHAR(200) NOT NULL" +
                         ")";
            stmt.executeUpdate(sql);
            System.out.println("Users table created successfully!");
        } catch (SQLException e) {
            System.out.println("An error occurred while creating the users table.");
            System.out.println(e.getMessage());
        }
    }

    public void createTableStudent() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL driver not found.");
        }

        String url = "jdbc:postgresql://" + host + ":" + port + "/" + dbName;
        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS student (" +
                         "id SERIAL PRIMARY KEY, " +    
                         "first_name VARCHAR(100), " +
                         "last_name VARCHAR(100), " +
                         "age INT, " +
                         "grade REAL )";
            stmt.executeUpdate(sql);
            System.out.println("Student table created successfully!");
        } catch (SQLException e) {
            System.out.println("An error occurred while creating the student table.");
            System.out.println(e.getMessage());
        }
    }

    public void deleteDatabase(String dbName) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL driver not found.");
        }

        String url = "jdbc:postgresql://" + host + ":" + port + "/" + "postgres";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement()) {
            String sql = "DROP DATABASE IF EXISTS " + dbName;
            stmt.executeUpdate(sql);
            System.out.println("Database " + dbName + " deleted successfully!");
        } catch (SQLException e) {
            System.out.println("An error occurred while deleting the database.");
            System.out.println(e.getMessage());
        }
    }
}
