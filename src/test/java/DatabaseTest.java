import javax.xml.crypto.Data;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
// import static org.junit.jupiter.api.Assertions.assertEquals;

public class DatabaseTest {
    @BeforeAll
    public static void setup() {
        
    }

    @Test
    public void testDeleteDatabase() {
        Database db = new Database();
        db.deleteDatabase();
        // assertEquals("La base de données a été supprimée avec succès.", db.deleteDatabase());
    }

    @Test
    public void testCreateDatabase() {
        Database db = new Database();
        db.createDatabase();
    }
}