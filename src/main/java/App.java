import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Create the database and tables if they do not exist
        Database db = new Database();
        db.createDatabase("laplateformetracker");
        db.createTableUsers();
        db.createTableStudent();

        // Load the FXML file for the create user scene
        Parent root = FXMLLoader.load(getClass().getResource("/create_user.fxml"));
        primaryStage.setTitle("Create Account / Login");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}