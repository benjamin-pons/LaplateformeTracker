import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserController {
    private Connection conn;

    public UserController(Connection conn) {
        this.conn = conn;
    }

    public void createAccount(String username, String hashedPassword) {
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, hashedPassword);
            pstmt.executeUpdate();
            System.out.println("Compte utilisateur créé !");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la création du compte.");
            e.printStackTrace();
        }
    }
}
