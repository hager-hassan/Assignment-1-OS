package org.os;
import java.io.File;

public class Rmcommand {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java rmcommand <file_or_directory>");
            System.out.println("Options:");
            System.out.println("  -r    Recursively remove directories and their contents");
            System.out.println("  -d    Remove empty directory");
            System.exit(1);
        }

        String path = args[0];
        boolean recursive = args.length > 1 && args[1].equals("-r");
        boolean deleteEmptyDir = args.length > 1 && args[1].equals("-d");

        File file = new File(path);

        if (!file.exists()) {
            System.out.println("Error: File or directory '" + path + "' does not exist.");
            System.exit(1);
        }

        if (file.isDirectory()) {
            if (recursive) {
                deleteDirectory(file);
            } else if (deleteEmptyDir) {
                if (file.list().length == 0) {  // Checks if directory is empty
                    deleteFile(file);
                } else {
                    System.out.println("Error: Directory '" + path + "' is not empty. Use -r to delete non-empty directories.");
                }// delete the empty directory
            } else {
                System.out.println("Error: '" + path + "' is a directory. Use -d to delete an empty directory or -r to delete a directory and its contents.");
                System.exit(1);
            }
        } else {
            deleteFile(file); // if it’s a file, delete it directly
        }
    }



    private static void deleteFile(File file) {
        if (file.delete()) {
            System.out.println("File deleted: " + file.getAbsolutePath());
        } else {
            System.out.println("Error: Unable to delete file.");
        }
    }

    private static void deleteDirectory(File dir) {
        File[] contents = dir.listFiles();
        if (contents != null) {
            for (File file : contents) {
                if (file.isDirectory()) {
                    deleteDirectory(file);
                } else {
                    deleteFile(file);
                }
            }
        }
        if (dir.delete()) {
            System.out.println("Directory deleted: " + dir.getAbsolutePath());
        } else {
            System.out.println("Error: Unable to delete directory.");
        }
    }
}
