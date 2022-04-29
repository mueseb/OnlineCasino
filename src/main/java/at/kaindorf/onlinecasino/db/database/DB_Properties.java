package at.kaindorf.onlinecasino.db.database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class DB_Properties {

    public static Properties dbProperties = new Properties();

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
