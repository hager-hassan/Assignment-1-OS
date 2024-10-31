package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class MV {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java MV <sourcefile> <destinationfile>");
        }

        String SRCfile = args[0];
        String DESTfile = args[1];

        String whichoperation = getOperationType(SRCfile, DESTfile);
        System.out.println("Operation:"+ whichoperation);

        boolean state = moveOrRename(SRCfile, DESTfile);
        if (state) {
            System.out.println("Successfully " + whichoperation + " from " + SRCfile + " to " + DESTfile);
        }
        else {
            System.out.println("Failed to " + whichoperation + " from " + SRCfile + " to " + DESTfile);
        }
    }

    // Method to determine if it's a rename or a move
    private static String getOperationType(String sourcePath, String destinationPath) {
        File sourceFile = new File(sourcePath);
        File destFile = new File(destinationPath);

        String sourceParent = sourceFile.getParent();
        String destParent = destFile.getParent();//law m4 equal tba2a move

        // Handle cases where getParent() might return null
        if (sourceParent == null) {
            sourceParent = "";
        }
        if (destParent == null) {
            destParent = ""; //
        }

        if (sourceParent.equals(destParent)) {
            return "Rename";
        } else {
            return "Move";
        }
    }

    // Method to move or rename the file
    public static boolean moveOrRename(String sourcePath, String destinationPath) {
        File sourceFile = new File(sourcePath);
        File destFile = new File(destinationPath);

        // Check if the source file exists
        if (!sourceFile.exists()) {
            System.err.println("Source file not found: " + sourcePath);
            return false;
        }

        try {
            // Move or rename the file using Files.move()
            Files.move(sourceFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (IOException e) {
            System.err.println("Error during move/rename: " + e.getMessage());
            return false;
        }
    }
}