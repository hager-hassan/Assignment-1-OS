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

        if (files == null) {
            result.add("Unable to access directory contents.");
            return result.toArray(new String[0]);
        }

        Arrays.stream(files)
                .sorted(Comparator.reverseOrder()) // Sort files in reverse order
                .forEach(file -> result.add(file.getName())); // Collecting all file names

        return result.toArray(new String[0]); // Returning the collected output
    }
}

