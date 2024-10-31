package org.example;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class RmdirCommand implements ICommand {
    @Override
    public String[] execute(String[] args) {
        List<String> result = new ArrayList<>();
        boolean recursivedeletion = false;
        String dirName;

        if (args.length == 0) {
            result.add("\u001B[31mUsage: java rmdir <directory> or java rmdir -r <directory>.\u001B[0m");
            return (result.toArray(new String[0]));
        }

        if (args.length > 1 && !(args[0].equals("-r"))) {
            result.add("\u001B[31mUsage: java rmdir <directory> or java rmdir -r <directory>.\u001B[0m");
            return (result.toArray(new String[0]));
        }

        if (args.length == 1 && args[0].equals("-r")) {
            result.add("\u001B[31mUsage: java rmdir <directory> or java rmdir -r <directory>.\u001B[0m");
            return (result.toArray(new String[0]));
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
                result.add("\u001B[31mDirectory is not empty: " + dirName +".\u001B[0m");
            }
        }

        if (success && recursivedeletion) {
            result.add("Successfully deleted directories recursively: " + dirName);
        } else if (success) {
            result.add("Successfully deleted directory: " + dirName);
        } else if (!dir.exists()) {
            result.add("\u001B[31mDirectory does not exist: " + dirName+".\u001B[0m");
        } else {
            result.add("\u001B[31mFailed to delete directory: " + dirName+".\u001B[0m");
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
