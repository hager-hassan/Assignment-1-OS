package org.os;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class LsrCommand implements Command {
    @Override
    public String[] execute(String[] args) {
        List<String> result = new ArrayList<>();
        File currentDir = new File(System.getProperty("user.dir"));
        File[] files = currentDir.listFiles();

        if (args.length > 0) {
            result.add("\u001B[31mError: This command does not accept any arguments.\u001B[0m"); // Red error message
            return result.toArray(new String[0]);
        }

        if (files == null) {
            result.add("Unable to access directory contents.");
            return result.toArray(new String[0]);
        }

        // Filter out hidden files and sort by file name in reverse order
        Arrays.stream(files)
                .filter(file -> !file.isHidden()) // Only include visible files
                .sorted(Comparator.comparing(File::getName).reversed()) // Sort files by name in reverse order
                .forEach(file -> result.add(file.getName())); // Collecting all file names

        return result.toArray(new String[0]); // Returning the collected output
    }
}
