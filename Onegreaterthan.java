package org.os;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;


public class Onegreaterthan {

    public static void main(String[] args) {
        if (!args[args.length - 2].equals(">")) {
            System.out.println("Usage: java EchoOrCatExample <string(don't write it with quotations)_or_inputfile> > <outputfile>");
            return;
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
    }

    public static void handleSingleInput(String inputSource, String outputFile) {

        File inputFile = new File(inputSource);

        try (FileWriter writer = new FileWriter(outputFile)) {
            if (inputFile.exists() && inputFile.isFile()) {// bashoof la hwa afile

                try (FileReader reader = new FileReader(inputFile)) {
                    int character;
                    while ((character = reader.read()) != -1) {
                        writer.write(character);
                    }
                    System.out.println("Content from file '" + inputSource + "' has been written to " + outputFile);
                }
            } else {
                // treat it as string
                if ((inputSource.startsWith("\"") && inputSource.endsWith("\"")) || (inputSource.startsWith("'") && inputSource.endsWith("'"))) {
                    inputSource = inputSource.substring(1, inputSource.length() - 1);  // Remove quotes
                }
                writer.write(inputSource);
                System.out.println("String content '" + inputSource + "' has been written to " + outputFile);
            }
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }}

        private static void handleMultipleFiles(String[] inputSource, String outputFile){
            try (FileWriter writer = new FileWriter(outputFile)) {
                for (String inputSources: inputSource) {
                    File inputFile = new File(inputSources);
                    if (inputFile.exists() && inputFile.isFile()) {
                        writeFileContentToOutput(inputFile, writer);
                    } else {
                        System.out.println("Input '" + inputSource + "' does not exist or is not a file. It will be treated as a string.");
                        writer.write(inputSources);
                        writer.write(System.lineSeparator()); // Add new line for separation
                    }
                }
                System.out.println("Content from the specified files and strings has been written to " + outputFile);
            } catch (IOException e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
        private static void writeFileContentToOutput (File inputFile, FileWriter writer) throws IOException {
            try (FileReader reader = new FileReader(inputFile)) {
                int character;
                while ((character = reader.read()) != -1) {
                    writer.write(character);
                }
                writer.write(System.lineSeparator()); // Add a new line after each file's content
            }
        }

}



