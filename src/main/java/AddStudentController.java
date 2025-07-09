import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddStudentController {
    public TextField firstNameField;
    public TextField lastNameField;
    public TextField ageField;
    public TextField gradeField;

    private StudentController studentController;
    private Runnable onStudentAdded;
    private Student editingStudent = null;

    public void setStudentController(StudentController sc) {
        this.studentController = sc;
    }

    public void setOnStudentAdded(Runnable r) {
        this.onStudentAdded = r;
    }

    public void setEditingStudent(Student student) {
        this.editingStudent = student;
        if (student != null) {
            firstNameField.setText(student.getFirstName());
            lastNameField.setText(student.getLastName());
            ageField.setText(String.valueOf(student.getAge()));
            gradeField.setText(String.valueOf(student.getGrade()));
        }
    }

    @FXML
    private void handleAdd() {
        try {
            String firstName = firstNameField.getText().trim();
            String lastName = lastNameField.getText().trim();
            int age = Integer.parseInt(ageField.getText().trim());
            float grade = Float.parseFloat(gradeField.getText().trim());
            if (editingStudent == null) {
                studentController.createStudent(firstName, lastName, age, grade);
            } else {
                studentController.modifyStudent(editingStudent.getId(), firstName, lastName, age, grade);
            }
            if (onStudentAdded != null) onStudentAdded.run();
            closeWindow();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error adding/modifying student: " + e.getMessage());
        }
    }

    @FXML
    private void handleCancel() {
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) firstNameField.getScene().getWindow();
        stage.close();
    }
}