package org.os;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Rmcommand {
    public String[] excute(String[] args) {
        List<String> result = new ArrayList<>();

        if (args.length < 1) {
            result.add("Usage: java rmcommand <file_or_directory>");
            result.add("Options:");
            result.add("  -r    Recursively remove directories and their contents");
            result.add("  -d    Remove empty directory");
            System.exit(1);
        }

        String path = args[0];
        boolean recursive = args.length > 1 && args[1].equals("-r");
        boolean deleteEmptyDir = args.length > 1 && args[1].equals("-d");

        File file = new File(path);

        if (!file.exists()) {
            result.add("Error: File or directory '" + path + "' does not exist.");
            System.exit(1);
        }

        if (file.isDirectory()) {
            if (recursive) {
                deleteDirectory(file);
            } else if (deleteEmptyDir) {
                if (file.list().length == 0) {  // Checks if directory is empty
                    deleteFile(file);
                    result.add("success");
                } else {
                    result.add("Error: Directory '" + path + "' is not empty. Use -r to delete non-empty directories.");
                }// delete the empty directory
            } else {
                result.add("Error: '" + path + "' is a directory. Use -d to delete an empty directory or -r to delete a directory and its contents.");
                System.exit(1);
            }
        } else {
            deleteFile(file);
            result.add("success");
// if it’s a file, delete it directly
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
