package steps.continuumsecurity.scanner;

import java.io.File;
import java.io.FileNotFoundException;

public class FileUtils {
    public static boolean deleteRecursive(File path) throws FileNotFoundException {
        if (!path.exists()) throw new FileNotFoundException(path.getAbsolutePath());
        boolean ret = true;
        if (path.isDirectory()) {
            for (File f : path.listFiles()) {
                ret = ret && FileUtils.deleteRecursive(f);
            }
        }
        return ret && path.delete();
    }
}