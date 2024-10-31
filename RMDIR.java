package org.example;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class RMDIR {
    public String[] execute(String[] args) {
        List<String> result = new ArrayList<>();
        boolean recursivedeletion = false;
        String dirName;

        if (args.length == 0) {
            result.add("Usage: java rmdir <directory>");
        }

        if (args.length > 0 && args[0].equals("-r")) {
            recursivedeletion = true;
            dirName = args[1];
        }
        else{
            dirName = args[0];
        }

        File dir = new File(dirName);
        boolean success;

        if (recursivedeletion) {
            success = deleteRecursively(dir);
        } else {
            success = dir.delete(); // Only deletes if the directory is empty
            if (!success && dir.exists()) {
                result.add("Directory is not empty: " + dirName);
            }
        }

        if (success && recursivedeletion) {
            result.add("Successfully deleted directories recursively: " + dirName);
        } else if (success) {
            result.add("Successfully deleted directory: " + dirName);
        } else if (!dir.exists()) {
            result.add("Directory does not exist: " + dirName);
        } else {
            result.add("Failed to delete directory: " + dirName);
        }
        return (result.toArray(new String[0]));
    }

    // Method for recursive deletion
    private static boolean deleteRecursively(File file) {
        if (file.isDirectory()) {//ha4of dahh dir gowaha hagat wala file
            File[] files = file.listFiles(); // get list of all subdirectories inside this dir
            if (files != null) {
                for (File subFile : files) { //ha3ml recursion lahad ma yba2a file bas m4 subdir w a3mlo delete
                    deleteRecursively(subFile);
                }
            }
        }
        return file.delete(); // Deletes the file or directory itself
    }

}
