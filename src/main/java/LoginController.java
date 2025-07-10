import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class LoginController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;

    @FXML
    private void handleLogin() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText();
        if (username.isEmpty() || password.isEmpty()) {
            showAlert("All fields are required.");
            return;
        }
        try {
            Database db = new Database();
            UserController uc = new UserController(db.getConnection());
            if (uc.login(username, password)) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/menu.fxml"));
                    Parent root = loader.load();
                    Stage mainStage = new Stage();
                    mainStage.setTitle("Menu principal");
                    mainStage.setScene(new Scene(root));
                    mainStage.show();
                } catch (Exception e) {
                    showAlert("Erreur lors de l'ouverture du menu principal : " + e.getMessage());
                }
                Stage stage = (Stage) usernameField.getScene().getWindow();
                stage.close();
            } else {
                showAlert("Invalid username or password.");
            }
        } catch (Exception e) {
            showAlert("Error during login: " + e.getMessage());
        }
    }

    @FXML
    private void handleBack() {
        Stage stage = (Stage) usernameField.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleGoToCreateUser() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/create_user.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Créer un compte");
            stage.setScene(new Scene(root));
            stage.show();
            // Ferme la fenêtre actuelle
            Stage currentStage = (Stage) usernameField.getScene().getWindow();
            currentStage.close();
        } catch (Exception e) {
            showAlert("Erreur lors de l'ouverture de la fenêtre de création de compte : " + e.getMessage());
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
