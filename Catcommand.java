package org.os;
import java.io.*;

public class Catcommand {
    public static void main(String[] args){
        String inputFile = args[0]; // The input file from arguments
        String outputFile = args[args.length-1];
        if (args.length < 1 ) {
            System.out.println("Usage: java cat * || cat filename.txt || cat file(s)name.txt >>/> outputfile.txt ");
        }else if ("*".equals(args[0])) {
            String projectDirectory = ".";
            printAllTxtFilesAndContents(new File(projectDirectory));
        }else if (args.length > 2 && ">".equals(args[1])) {
            outputFile = args[2];
            Onegreaterthan.main(new String[]{inputFile, ">", outputFile});// hanadi el function bt3t >

        } else if (args.length > 2 && ">>".equals(args[1])) {
            // Default output file
            outputFile = args[2];
            Twogreaterthan.main(new String[]{inputFile, ">>", outputFile}); // hanadi el function bt3t >>

        }else {
            concatFiles(args);
        }
    }
    private static void printAllTxtFilesAndContents(File directory) {
        File[] files = directory.listFiles();

        if (files != null && files.length > 0) {
            for (File file : files) {
                if (file.isDirectory()) {
                    printAllTxtFilesAndContents(file); // Recursively check in subdirectories
                } else if (file.getName().endsWith(".txt")) {
                    // Print the file content if it's a .txt file
                    printFileContent(file);
                }
            }
        } else {
            System.out.println("The directory is empty or an I/O error occurred.");
        }
    }

    private static void printFileContent(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println(); // Add an empty line after each file's content
        } catch (IOException e) {
            System.out.println("Error reading file '" + file.getName() + "': " + e.getMessage());
        }
    }

    private static void concatFiles(String[] args) {
        for (String inputFile : args) {
            File file = new File(inputFile);
            if (file.exists()) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                    }
                } catch (IOException e) {
                    System.out.println("Error reading file '" + inputFile + "': " + e.getMessage());
                }
            } else {
                System.out.println("File '" + inputFile + "' does not exist.");
            }
        }
    }

}
