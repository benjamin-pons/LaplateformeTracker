public class App {
    public static void main(String[] args) {
        Database db = new Database();
        db.deleteDatabase();
        db.createDatabase();
        db.createTable();
    }
}