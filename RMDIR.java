package org.example;
import java.io.File;

public class RMDIR {
    public static void main(String[] args) {
        boolean recursivedeletion = false;
        String dirName;

        if (args.length == 0) {
            System.out.println("Usage: java rmdir <directory>");
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
                System.out.println("Directory is not empty: " + dirName);
            }
        }

        if (success && recursivedeletion) {
            System.out.println("Successfully deleted directories recursively: " + dirName);
        } else if (success) {
            System.out.println("Successfully deleted directory: " + dirName);
        } else if (!dir.exists()) {
            System.out.println("Directory does not exist: " + dirName);
        } else {
            System.err.println("Failed to delete directory: " + dirName);
        }
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
