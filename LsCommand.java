package org.example;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LsCommand implements ICommand {
    @Override
    public String[] execute(String[] args) {
        List<String> result = new ArrayList<>();
        File currentDir = new File(System.getProperty("user.dir"));
        File[] files = currentDir.listFiles();

        if (args.length > 0) {
            result.add("\u001B[31mThis command does not accept any arguments.\u001B[0m");
            return result.toArray(new String[0]);
        }

        if (files == null) {
            result.add("Unable to access directory contents.");
            return result.toArray(new String[0]);
        }

        Arrays.stream(files)
                .filter(file -> !file.isHidden())
                .forEach(file -> result.add(file.getName())); // Collecting file names

        return result.toArray(new String[0]); // Returning the collected output
    }
}
