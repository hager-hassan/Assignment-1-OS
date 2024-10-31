package org.os;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Catcommand {
    @Override
    public String[] excute(String[] args){
        List<String> result = new ArrayList<>();
        String inputFile = args[0]; // The input file from arguments
        String outputFile = args[args.length-1];
        if (args.length < 1 ) {
            result.add("Usage: java cat * || cat filename.txt || cat file(s)name.txt >>/> outputfile.txt ");
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
        return (result.toArray(new String[0]));
    }
    private  String[] printAllTxtFilesAndContents(File directory) {
        List<String> result = new ArrayList<>();
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
            result.add("The directory is empty or an I/O error occurred.");
        }
        return (result.toArray(new String[0]));
    }


    private String[] printFileContent(File file) {
        List<String> result = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                result.add(line);
            }

        } catch (IOException e) {
            result.add("Error reading file '" + file.getName() + "': " + e.getMessage());
        }
        return (result.toArray(new String[0]));
    }

    private String[] concatFiles(String[] args) {
        List<String> result = new ArrayList<>();

        for (String inputFile : args) {
            File file = new File(inputFile);
            if (file.exists()) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        result.add(line);
                    }
                } catch (IOException e) {
                  result.add("Error reading file '" + inputFile + "': " + e.getMessage());
                }
            } else {
                result.add("File '" + inputFile + "' does not exist.");
            }
        }
        return (result.toArray(new String[0]));
    }

}
