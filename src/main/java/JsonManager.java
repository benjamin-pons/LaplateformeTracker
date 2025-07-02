import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.List;

public class JsonManager {
    String filePath = "students.json"; // Specify the path where you want to save the JSON file

    public void exportToJson() throws java.sql.SQLException {
        ObjectMapper objectMapper = new ObjectMapper();
        Database db = new Database();
        StudentController studentController = new StudentController(db.getConnection());
        List<Student> students = studentController.getAllStudents();
        try {
            objectMapper.writeValue(new File(filePath), students);
            System.out.println("Exported students to JSON file: " + filePath);
        } catch (Exception e) {
            System.out.println("Error exporting students to JSON.");
            e.printStackTrace();
        }
    }

    public void importFromJson(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<Student> students = objectMapper.readValue(new File(filePath), objectMapper.getTypeFactory().constructCollectionType(List.class, Student.class));
            Database db = new Database();
            StudentController studentController = new StudentController(db.getConnection());
            for (Student student : students) {
                studentController.createStudent(student.getFirstName(), student.getLastName(), student.getAge(), student.getGrade());
            }
            System.out.println("Imported students from JSON file: " + filePath);
        } catch (Exception e) {
            System.out.println("Error importing students from JSON.");
            e.printStackTrace();
        }
    }
}