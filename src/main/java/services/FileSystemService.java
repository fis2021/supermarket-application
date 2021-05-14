package services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class FileSystemService {
    public static String APPLICATION_FOLDER = ".registration-example";
    private static final String USER_FOLDER = System.getProperty("user.home");
    //public static final Path APPLICATION_HOME_PATH = Paths.get(USER_FOLDER, APPLICATION_FOLDER);

    public static Path getPathToFile(String... path) {
        return Paths.get(USER_FOLDER, APPLICATION_FOLDER).resolve(Paths.get(".", path));
    }

    public static Path getApplicationHomeFolder(){
        return Paths.get(USER_FOLDER, APPLICATION_FOLDER);
    }

    static void initDirectory() {
        Path applicationHomePath = Paths.get(USER_FOLDER, APPLICATION_FOLDER);
        if (!Files.exists(applicationHomePath))
            applicationHomePath.toFile().mkdirs();
    }
}
