package at.kaindorf.onlinecasino.db.connection;

import lombok.Data;
import lombok.Getter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

@Data
@Getter
public class DB_Properties {

    public static Properties dbProperties = new Properties();

    private String db_driver = "org.postgresql.Driver";
    private String db_url = "jdbc:postgresql://107.20.254.204:5432/CasinoDB";
    private String db_user = "postgres";
    private String db_password = "2002";

    static {
        Path path = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "dbConnect.properties");

        try {
            FileInputStream fis = new FileInputStream(path.toFile());
            dbProperties.load(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return dbProperties.getProperty(key);
    }
}
