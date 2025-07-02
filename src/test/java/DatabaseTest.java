import org.checkerframework.checker.units.qual.s;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;

public class DatabaseTest {

    @BeforeAll
    public static void setup() {
        Database db = new Database();
        db.createDatabase("laplateformetracker");
        db.createTableUsers();
        db.createTableStudent();
        
    }

    @Test
    public void createStudentTest() throws java.sql.SQLException {
       Database db = new Database();
       StudentController studentController = new StudentController(db.getConnection());
       studentController.createStudent("John", "Doe", 20, 2);
    }

    @Test
    public void modifyStudentTest () throws java.sql.SQLException {
        Database db = new Database();
       
        StudentController studentController = new StudentController(db.getConnection());
        studentController.modifyStudent(7, "Jane", "Doe", 21, 3);
    }

    @Test
    public void deleteStudentTest() throws java.sql.SQLException {
        Database db = new Database();
        
        StudentController studentController = new StudentController(db.getConnection());
        studentController.deleteStudent(6);
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
    
}