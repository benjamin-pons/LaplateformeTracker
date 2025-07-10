import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DatabaseTest {

    @BeforeAll
    public static void setup() {
        Database db = new Database();
        db.createDatabase("laplateformetracker");
        db.createTableUsers();
        db.createTableStudent();
        
    }

    @Test
    public void deleteDatabaseTest() {
        Database db = new Database();
        db.deleteDatabase("laplateformetracker");
    }

    @Test
    public void createStudentTest() throws java.sql.SQLException {
       Database db = new Database();
       StudentController studentController = new StudentController(db.getConnection());
       studentController.createStudent("John", "Doe", 20, 15);
    }

    @Test
    public void modifyStudentTest () throws java.sql.SQLException {
        Database db = new Database();
       
        StudentController studentController = new StudentController(db.getConnection());
        studentController.modifyStudent(1, "Kali", "Uchis", 30, 3.5f);
    }

    @Test
    public void deleteStudentTest() throws java.sql.SQLException {
        Database db = new Database();
        
        StudentController studentController = new StudentController(db.getConnection());
        studentController.deleteStudent(1);
    }

    @Test
    public void createAccountTest() throws java.sql.SQLException {
        Database db = new Database();
        UserController userController = new UserController(db.getConnection());
        userController.createAccount("cc", "1234");
    }

    @Test
    public void loginTest() throws java.sql.SQLException {
        Database db = new Database();
        UserController userController = new UserController(db.getConnection());
        // Assert that the login was successful
        assertEquals(true, userController.login("cc", "1234"));
    }

    @Test
    public void getAllStudentsTest() throws java.sql.SQLException {
        Database db = new Database();
        StudentController studentController = new StudentController(db.getConnection());
        List<Student> students = studentController.getAllStudents();
        // Assert that the list of students is not empty
        assertEquals(false, students.isEmpty());
        System.out.println("Liste des étudiants :");
        for (Student s : students) {
            System.out.println("ID: " + s.getId() + ", Nom: " + s.getFirstName() + " " + s.getLastName() + ", Age: " + s.getAge() + ", Note: " + s.getGrade());
        }
    }

    @Test
    public void getStudentByAgeTest() throws java.sql.SQLException {
        Database db = new Database();
        StudentController studentController = new StudentController(db.getConnection());
        List<Student> students = studentController.getStudentByAge(20);
        // Assert that the list of students is not empty
        assertEquals(false, students.isEmpty());
        System.out.println("Students older than 20:");
        for (Student s : students) {
            System.out.println("ID: " + s.getId() + ", First Name: " + s.getFirstName() + ", Last Name: " + s.getLastName() + ", Age: " + s.getAge() + ", Grade: " + s.getGrade());
        }
    }

    @Test
    public void getStudentByLastNameTest() throws java.sql.SQLException {
        String name = "U";
        Database db = new Database();
        StudentController studentController = new StudentController(db.getConnection());
        List<Student> students = studentController.getStudentByName(name);
        // Assert that the list of students is not empty
        assertEquals(false, students.isEmpty());
        System.out.println("Students lists with last name '" + name + "':");
        for (Student s : students) {
            System.out.println("ID: " + s.getId() + ", First Name: " + s.getFirstName() + ", Last Name: " + s.getLastName() + ", Age: " + s.getAge() + ", Grade: " + s.getGrade());
        }
    }

    @Test
    public void getStudentByFirstNameTest() throws java.sql.SQLException {
        String firstName = "H";
        Database db = new Database();
        StudentController studentController = new StudentController(db.getConnection());
        List<Student> students = studentController.getStudentByFirstName(firstName);
        // Assert that the list of students is not empty
        assertEquals(false, students.isEmpty());
        System.out.println("Students lists with first name '" + firstName + "':");
        for (Student s : students) {
            System.out.println("ID: " + s.getId() + ", First Name: " + s.getFirstName() + ", Last Name: " + s.getLastName() + ", Age: " + s.getAge() + ", Grade: " + s.getGrade());
        }
    }

    @Test
    public void getStudentByGradeTest() throws java.sql.SQLException {
        float grade = 2.73f;
        Database db = new Database();
        StudentController studentController = new StudentController(db.getConnection());
        List<Student> students = studentController.getStudentByGrade(grade);
        // Assert that the list of students is not empty
        assertEquals(false, students.isEmpty());
        System.out.println("Student list with grade " + grade + ":");
        for (Student s : students) {
            System.out.println("ID: " + s.getId() + ", First Name: " + s.getFirstName() + ", Last Name: " + s.getLastName() + ", Age: " + s.getAge() + ", Grade: " + s.getGrade());
        }
    }

    @Test
    public void exportToJsonTest() throws java.sql.SQLException {
        JsonManager jsonExport = new JsonManager();
        try {
            jsonExport.exportToJson();
        } catch (Exception e) {
            System.out.println("Error while exporting to JSON");
            e.printStackTrace();
        }
    }

    @Test
    public void importFromJsonTest() {
        JsonManager jsonImport = new JsonManager();
        try {
            jsonImport.importFromJson("src/main/resources/students.json");
        } catch (Exception e) {
            System.out.println("Error while importing from JSON");
            e.printStackTrace();
        }
    }

    @Test
    public void openCreateUserMenuTest() throws Exception {
        // Initialise JavaFX toolkit si besoin
        Platform.startup(() -> {});
        Platform.runLater(() -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/create_user.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setTitle("Create User");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        Thread.sleep(10000);
    }
}