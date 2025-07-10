import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class CreateUserController {
    @FXML private TextField usernameField;
    @FXML private TextField passwordField;
    @FXML private TextField confirmPasswordField;

    @FXML
    private void handleCreateAccount() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();
        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            showAlert("All fields are required.");
            return;
        }
        if (!password.equals(confirmPassword)) {
            showAlert("Passwords do not match.");
            return;
        }
        try {
            Database db = new Database();
            UserController uc = new UserController(db.getConnection());
            uc.createAccount(username, password);
            showAlert("Account created successfully!");
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            showAlert("Error creating account: " + e.getMessage());
        }
    }

    @FXML
    private void handleGoToLogin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Login");
            stage.setScene(new Scene(root));
            stage.show();
            // Ferme la fenêtre actuelle
            Stage currentStage = (Stage) usernameField.getScene().getWindow();
            currentStage.close();
        } catch (Exception e) {
            showAlert("Error opening login window: " + e.getMessage());
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
