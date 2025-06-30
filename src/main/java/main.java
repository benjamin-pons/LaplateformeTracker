public class main {
    public static void main(String[] args) {
        Database db = new Database();
        String dbName = "mydatabase";
        db.createDatabase(dbName);
        db.createTable(dbName);
    }
}
