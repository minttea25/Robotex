package Util;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtil {
    public static boolean createFolder(Path path) {
        File folder = new File(path.toString());
        if (!folder.exists()) {
            try {
                Files.createDirectory(Paths.get(folder.getAbsolutePath()));
                System.out.println("Folder created");
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        else {
            System.out.println("Already exists");
            return true;
        }
    }

    public static boolean deleteFile(Path path) {
        File f = new File(path.toString());

        if (f.exists()) {
            if (f.delete()) {
                System.out.println("Wrong file is deleted");
                return true;
            }else {
                System.out.println("Failed to delete file");
                return false;
            }
        }
        return false;
    }
}
