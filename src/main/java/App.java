public class App {
    public static void main(String[] args) {
        Database db = new Database();
        db.createDatabase("laplateformetracker");
        db.createTable();
    }
}