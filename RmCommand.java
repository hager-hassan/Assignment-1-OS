package org.example;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;

public class RmCommand implements ICommand {

    @Override
    public String[] execute(String[] args) {
        List<String> result = new ArrayList<>();

        if (args.length < 2) {
            result.add("Usage: java rmcommand <file_or_directory>");
            result.add("Options:");
            result.add("  -r    Recursively remove directories and their contents");
            result.add("  -d    Remove empty directory");
            return result.toArray(new String[0]);
        }
        String option = args[0];
        String path = args[1];
        boolean recursive = args[0].equals("-r");
        boolean deleteEmptyDir = args[0].equals("-d");

        File file = new File(path);

        if (!file.exists()) {
            result.add("Error: File or directory '" + path + "' does not exist.");
            return result.toArray(new String[0]);
        }

        if (file.isDirectory()) {
            if (recursive) {
                String[] deleteResult = deleteDirectory(file);
                Collections.addAll(result, deleteResult);
            }
            else if (deleteEmptyDir) {
                if (file.list().length == 0) {  // Checks if directory is empty
                    String[] deleteResult = deleteFile(file);
                    Collections.addAll(result, deleteResult);
                } else {
                    result.add("Error: Directory '" + path + "' is not empty. Use -r to delete non-empty directories.");
                }// delete the empty directory
            }
        } else {
            String[] deleteResult = deleteFile(file);
            Collections.addAll(result, deleteResult);
        }
        return (result.toArray(new String[0]));

    }



    private String[] deleteFile(File file) {
        List<String> result = new ArrayList<>();

        if (file.delete()) {
            result.add("File deleted: " + file.getAbsolutePath());
        } else {
            result.add("Error: Unable to delete file.");
        }
        return (result.toArray(new String[0]));

    }

    private String[] deleteDirectory(File dir) {
        List<String> result = new ArrayList<>();

        File[] contents = dir.listFiles();
        if (contents != null) {
            for (File file : contents) {
                if (file.isDirectory()) {
                    deleteDirectory(file);
                    result.add("success");

                } else {
                    deleteFile(file);
                    result.add("success");

                }
            }
        }
        if (dir.delete()) {
            result.add("Directory deleted: " + dir.getAbsolutePath());
        } else {
            result.add("Error: Unable to delete directory.");
        }
        return (result.toArray(new String[0]));

    }
}
