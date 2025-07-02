    import java.sql.Connection;
    import java.util.List;
    import controllers.StudentController;

    public class App {
        public static void main(String[] args) {
            Database db = null;
            try {
                db = new Database();
                db.createDatabase("laplateformetracker");
                db.createTableUser();
                // db.createTableStudent();

                
                StudentController studentController = new StudentController(db.getConnection());
                studentController.createStudent("John", "Doe", 20, 2); 
                studentController.deleteStudent(2);
            } catch (java.sql.SQLException e) {
                System.out.println("Erreur de connexion à la base de données : " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Erreur : " + e.getMessage());
            }


        //     try {
        //         List<Student> students = controller.getAllStudents();

        //         System.out.println("Liste des étudiants :");
        //         for (Student s : students) {
        //             System.out.println("ID: " + s.getId() + ", Nom: " + s.getFirstName() + " " + s.getLastName() + ", Age: " + s.getAge() + ", Note: " + s.getGrade());
        //         }
        //     } catch (Exception e) {
        //         System.out.println("Erreur : " + e.getMessage());
        //     }

        //     try (Connection conn = db.getConnection()) {
        //         UserController userController = new UserController(conn);

        //         String username = "john_doe";
        //         String password = "password123";
        //         String hashedPassword = HashUtil.hashPassword(password);

        //         userController.createAccount(username, hashedPassword);
        //     } catch (java.sql.SQLException e) {
        //         System.out.println("Erreur lors de la connexion à la base de données : " + e.getMessage());
        //     }
        // }
    }
}
