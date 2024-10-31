package org.os;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Twogreaterthan {
    @Override
    public String [] excutes(String[] args) {
        List<String> result = new ArrayList<>();

        if (!args[args.length - 2].equals(">>")) {
            result.add("Usage: java EchoOrCatExample <string(don't write it with quotations)_or_inputfile> > <outputfile>");
        }

        String outputFile = args[args.length - 1];
        String[] inputSource = Arrays.copyOfRange(args, 0, args.length - 2);

        if (inputSource.length == 1) {
            // Single input case (either a string or a file)
            handleSingleInput(inputSource[0], outputFile);
        } else {
            // Multiple input files case
            handleMultipleFiles(inputSource, outputFile);
        }
        return (result.toArray(new String[0]));

    }

    public String[] handleSingleInput(String inputSource, String outputFile) {
        List<String> result = new ArrayList<>();

        File inputFile = new File(inputSource);

        try (FileWriter writer = new FileWriter(outputFile,true)) {
            if (inputFile.exists() && inputFile.isFile()) {// bashoof la hwa afile

                try (FileReader reader = new FileReader(inputFile)) {
                    int character;
                    while ((character = reader.read()) != -1) {
                        writer.write(character);
                    }
                   result.add("Content from file '" + inputSource + "' has been written to " + outputFile);
                }
            } else {
                // treat it as string
                if ((inputSource.startsWith("\"") && inputSource.endsWith("\"")) || (inputSource.startsWith("'") && inputSource.endsWith("'"))) {
                    inputSource = inputSource.substring(1, inputSource.length() - 1);  // Remove quotes
                }
                writer.write(inputSource);
                result.add("String content '" + inputSource + "' has been written to " + outputFile);
            }
        } catch (IOException e) {
            result.add("An error occurred: " + e.getMessage());
        }
        return (result.toArray(new String[0]));

    }

    private String[] handleMultipleFiles(String[] inputSource, String outputFile){
        List<String> result = new ArrayList<>();

        try (FileWriter writer = new FileWriter(outputFile,true)) {
            for (String inputSources: inputSource) {
                File inputFile = new File(inputSources);
                if (inputFile.exists() && inputFile.isFile()) {
                    writeFileContentToOutput(inputFile, writer);
                } else {
                    result.add("Input '" + inputSource + "' does not exist or is not a file. It will be treated as a string.");
                    writer.write(inputSources);
                    writer.write(System.lineSeparator()); // Add new line for separation
                }
            }
            result.add("Content from the specified files and strings has been written to " + outputFile);
        } catch (IOException e) {
            result.add("An error occurred: " + e.getMessage());
        }
        return (result.toArray(new String[0]));

    }
    private static void writeFileContentToOutput (File inputFile, FileWriter writer) throws IOException {
        try (FileReader reader = new FileReader(inputFile)) {
            int character=' ';
            writer.write(character);
            while ((character = reader.read()) != -1) {
                writer.write(character);
            }
            writer.write(System.lineSeparator()); // Add a new line after each file's content
        }

    }
    }



