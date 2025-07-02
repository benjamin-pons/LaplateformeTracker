import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserController {
    private Connection conn;

    public UserController(Connection conn) {
        this.conn = conn;
    }

    public void createAccount(String username, String password) {
        String hashedPassword = HashUtil.hashPassword(password);
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, hashedPassword);
            pstmt.executeUpdate();
            System.out.println("Compte utilisateur créé !");
            System.out.println("Hash enregistré : " + hashedPassword);
        } catch (SQLException e) {
            System.out.println("Erreur lors de la création du compte.");
            e.printStackTrace();
        }
    }

    public boolean login(String username, String password){
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        String hashedPassword = HashUtil.hashPassword(password); 
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, hashedPassword); 
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                System.out.println("Connexion réussie !");
                return true;
            } else {
                System.out.println("Nom d'utilisateur ou mot de passe incorrect.");
                System.out.println("Hash testé : " + hashedPassword);
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la connexion.");
            e.printStackTrace();
            return false;
        }
    }
}

