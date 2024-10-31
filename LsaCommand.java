package org.os;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LsaCommand implements ICommand {
    @Override
    public String[] execute(String[] args) {
        List<String> result = new ArrayList<>();

        // Check if there are any arguments
        if (args.length > 0) {
            result.add("\u001B[31mError: This command does not accept any arguments.\u001B[0m"); // Red error message
            return result.toArray(new String[0]);
        }

        File currentDir = new File(System.getProperty("user.dir"));
        File[] files = currentDir.listFiles();

        if (files == null) {
            result.add("Unable to access directory contents.");
            return result.toArray(new String[0]);
        }

        Arrays.stream(files).forEach(file -> result.add(file.getName())); // Collecting all file names

        return result.toArray(new String[0]); // Returning the collected output
    }
}
