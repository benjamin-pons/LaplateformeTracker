import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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
    public void deleteDatabaseTest() {
        Database db = new Database();
        db.deleteDatabase("laplateformetracker");
    }

    @Test
    public void createStudentTest() throws java.sql.SQLException {
       Database db = new Database();
       StudentController studentController = new StudentController(db.getConnection());
       studentController.createStudent("Hamza", "H", 20, 15);
    }

    @Test
    public void modifyStudentTest () throws java.sql.SQLException {
        Database db = new Database();
       
        StudentController studentController = new StudentController(db.getConnection());
        studentController.modifyStudent(7, "jeremy", "werenoi", 21, 10);
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

    @Test
    public void getStudentByAgeTest() throws java.sql.SQLException {
        Database db = new Database();
        StudentController studentController = new StudentController(db.getConnection());
        List<Student> students = studentController.getStudentByAge(20);
        // Assert that the list of students is not empty
        assertEquals(false, students.isEmpty());
        System.out.println("Liste des étudiants de 20 ans :");
        for (Student s : students) {
            System.out.println("ID: " + s.getId() + ", Nom: " + s.getFirstName() + " " + s.getLastName() + ", Age: " + s.getAge() + ", Note: " + s.getGrade());
        }
    }

    @Test
    public void getStudentByLastNameTest() throws java.sql.SQLException {
        String name = "w";
        Database db = new Database();
        StudentController studentController = new StudentController(db.getConnection());
        List<Student> students = studentController.getStudentByName(name);
        // Assert that the list of students is not empty
        assertEquals(false, students.isEmpty());
        System.out.println("Liste des étudiants avec le nom '" + name + "' :");
        for (Student s : students) {
            System.out.println("ID: " + s.getId() + ", Nom: " + s.getFirstName() + " " + s.getLastName() + ", Age: " + s.getAge() + ", Note: " + s.getGrade());
        }
    }

    @Test
    public void getStudentByFirstNameTest() throws java.sql.SQLException {
        String firstName = "jeremy";
        Database db = new Database();
        StudentController studentController = new StudentController(db.getConnection());
        List<Student> students = studentController.getStudentByFirstName(firstName);
        // Assert that the list of students is not empty
        assertEquals(false, students.isEmpty());
        System.out.println("Liste des étudiants avec le prénom '" + firstName + "' :");
        for (Student s : students) {
            System.out.println("ID: " + s.getId() + ", Nom: " + s.getFirstName() + " " + s.getLastName() + ", Age: " + s.getAge() + ", Note: " + s.getGrade());
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
        System.out.println("Liste des étudiants en classe " + grade + " :");
        for (Student s : students) {
            System.out.println("ID: " + s.getId() + ", Nom: " + s.getFirstName() + " " + s.getLastName() + ", Age: " + s.getAge() + ", Note: " + s.getGrade());
        }
    }

    @Test
    public void exportToJsonTest() throws java.sql.SQLException {
        JsonManager jsonExport = new JsonManager();
        try {
            jsonExport.exportToJson();
        } catch (Exception e) {
            System.out.println("Erreur lors de l'exportation des étudiants en JSON.");
            e.printStackTrace();
        }
    }

    @Test
    public void importFromJsonTest() {
        JsonManager jsonImport = new JsonManager();
        try {
            jsonImport.importFromJson("students.json");
        } catch (Exception e) {
            System.out.println("Erreur lors de l'importation des étudiants depuis le JSON.");
            e.printStackTrace();
        }
    }
}