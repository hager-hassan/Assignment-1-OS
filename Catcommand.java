package org.example;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class Catcommand implements ICommand {
    @Override
    public String[] execute(String[] args){
        List<String> result = new ArrayList<>();
        String inputFile = args[0]; // The input file from arguments
        String outputFile = args[args.length-1];
       if ("*".equals(args[0])) {
            String[] allTxtFilesResult =printAllTxtFilesAndContents(new File("."));
            Collections.addAll(result, allTxtFilesResult);

        }else if ( ">".equals(args[1])) {
            outputFile = args[2];
            Onegreaterthan obj1 =new Onegreaterthan();
            String[] outputResult = obj1.execute(args);
            Collections.addAll(result, outputResult);

        }else if ( ">>".equals(args[1])) {
            // Default output file
            String outputFile1 = args[2];
           String[] inputFiles = Arrays.copyOfRange(args, 0, args.length - 2);
           Twogreaterthan obj2 =new Twogreaterthan();
            String[] outputResult = obj2.execute(args);
            Collections.addAll(result, outputResult);

        }else {
            String[] concatResult = concatFiles(args);
            Collections.addAll(result, concatResult);
        }
        return (result.toArray(new String[0]));
    }

    private  String[] printAllTxtFilesAndContents(File directory) {
        List<String> result = new ArrayList<>();
        File[] files = directory.listFiles();

        if (files != null && files.length > 0) {
            for (File file : files) {
                if (file.isDirectory()) {
                String[]    subDirFilesResult=  printAllTxtFilesAndContents(file);
                    Collections.addAll(result, subDirFilesResult);// Recursively check in subdirectories
                } else if (file.getName().endsWith(".txt")) {
                    // Print the file content if it's a .txt file
                    String[]  subDirFilesResult1= printFileContent(file);
                    Collections.addAll(result, subDirFilesResult1);// Recursively check in subdirectories
                }
            }
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
