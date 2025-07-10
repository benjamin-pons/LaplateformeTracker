import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TextField;

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
    @FXML private Label idLabel;
    @FXML private TextField searchField;
    @FXML private TextField ageFilterField;
    @FXML private TextField gradeFilterField;

    private ObservableList<Student> students = FXCollections.observableArrayList();
    private int currentPage = 0;
    private final int pageSize = 14;
    private ObservableList<Student> allStudents = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        try {
            Database db = new Database();
            StudentController sc = new StudentController(db.getConnection());
            allStudents.addAll(sc.getAllStudents());
            updateTableView();
        } catch (Exception e) {
            e.printStackTrace();
        }
        tableView.setItems(students);

        // Listen for selection changes in the table view
        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                idLabel.setText(String.valueOf(newSel.getId()));
                firstNameLabel.setText(newSel.getFirstName());
                lastNameLabel.setText(newSel.getLastName());
                ageLabel.setText(String.valueOf(newSel.getAge()));
                gradeLabel.setText(String.valueOf(newSel.getGrade()));
            } else {
                idLabel.setText("");
                firstNameLabel.setText("");
                lastNameLabel.setText("");
                ageLabel.setText("");
                gradeLabel.setText("");
            }
        });
    }

    private void updateTableView() {
        students.clear();
        int fromIndex = currentPage * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, allStudents.size());
        if (fromIndex < toIndex) {
            students.addAll(allStudents.subList(fromIndex, toIndex));
        }
        tableView.setItems(students);
    }

    @FXML
    private void handleNextPage() {
        if ((currentPage + 1) * pageSize < allStudents.size()) {
            currentPage++;
            updateTableView();
        }
    }

    @FXML
    private void handlePreviousPage() {
        if (currentPage > 0) {
            currentPage--;
            updateTableView();
        }
    }

    @FXML
    private void handleAddStudent() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/add_student.fxml"));
            Parent root = loader.load();

            AddStudentController controller = loader.getController();
            Database db = new Database();
            StudentController sc = new StudentController(db.getConnection());
            controller.setStudentController(sc);
            controller.setOnStudentAdded(() -> {
                allStudents.clear();
                allStudents.addAll(sc.getAllStudents());
                updateTableView();
            });

            Stage stage = new Stage();
            stage.setTitle("Ajouter un étudiant");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleDeleteStudent() {
        Student selected = tableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try {
                Database db = new Database();
                StudentController sc = new StudentController(db.getConnection());
                sc.deleteStudent(selected.getId());
                // Refresh the table view
                allStudents.clear();
                allStudents.addAll(sc.getAllStudents());
                updateTableView();
                // Reset the labels
                firstNameLabel.setText("");
                lastNameLabel.setText("");
                ageLabel.setText("");
                gradeLabel.setText("");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void handleEditStudent() {
        Student selected = tableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/add_student.fxml"));
                Parent root = loader.load();

                AddStudentController controller = loader.getController();
                Database db = new Database();
                StudentController sc = new StudentController(db.getConnection());
                controller.setStudentController(sc);
                controller.setEditingStudent(selected);
                controller.setOnStudentAdded(() -> {
                    allStudents.clear();
                    allStudents.addAll(sc.getAllStudents());
                    updateTableView();
                });

                Stage stage = new Stage();
                stage.setTitle("Modifier un étudiant");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(new Scene(root));
                stage.showAndWait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void handleSearch() {
        String query = searchField.getText().trim();
        allStudents.clear();
        try {
            Database db = new Database();
            StudentController sc = new StudentController(db.getConnection());
            if (query.isEmpty()) {
                allStudents.addAll(sc.getAllStudents());
            } else {
                allStudents.addAll(sc.getStudentByName(query));
            }
            updateTableView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleFilter() {
        String ageText = ageFilterField.getText().trim();
        String gradeText = gradeFilterField.getText().trim();
        allStudents.clear();
        try {
            Database db = new Database();
            StudentController sc = new StudentController(db.getConnection());
            if (!ageText.isEmpty()) {
                try {
                    int age = Integer.parseInt(ageText);
                    allStudents.addAll(sc.getStudentByAge(age));
                    updateTableView();
                    return;
                } catch (NumberFormatException ignored) {}
            }
            if (!gradeText.isEmpty()) {
                try {
                    float grade = Float.parseFloat(gradeText);
                    allStudents.addAll(sc.getStudentByGrade(grade));
                    updateTableView();
                    return;
                } catch (NumberFormatException ignored) {}
            }
            // Else fetch all students
            allStudents.addAll(sc.getAllStudents());
            updateTableView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleShowStatistics() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/statistics.fxml"));
            Parent root = loader.load();
            StatisticsController controller = loader.getController();
            controller.setStudents(allStudents);
            Stage stage = new Stage();
            stage.setTitle("Statistiques");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}