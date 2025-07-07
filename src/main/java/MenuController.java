import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MenuController {
    @FXML
    private TableView<Student> tableView;
    @FXML
    private TableColumn<Student, String> firstNameColumn;
    @FXML
    private TableColumn<Student, String> lastNameColumn;

    @FXML private Label firstNameLabel;
    @FXML private Label lastNameLabel;
    @FXML private Label ageLabel;
    @FXML private Label gradeLabel;

    private ObservableList<Student> students = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        try {
            Database db = new Database();
            StudentController sc = new StudentController(db.getConnection());
            students.addAll(sc.getAllStudents());
        } catch (Exception e) {
            e.printStackTrace();
        }
        tableView.setItems(students);

        // Ajoute le listener de sélection
        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                firstNameLabel.setText(newSel.getFirstName());
                lastNameLabel.setText(newSel.getLastName());
                ageLabel.setText(String.valueOf(newSel.getAge()));
                gradeLabel.setText(String.valueOf(newSel.getGrade()));
            } else {
                firstNameLabel.setText("");
                lastNameLabel.setText("");
                ageLabel.setText("");
                gradeLabel.setText("");
            }
        });
    }
}