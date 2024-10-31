package org.os;

import java.io.File;
import java.util.Arrays;

public class LsaCommand implements Command {
    @Override
    public void execute(String[] args) {
        File currentDir = new File(System.getProperty("user.dir"));
        File[] files = currentDir.listFiles();

        if (files == null) {
            System.out.println("Unable to access directory contents.");
            return;
        }

        Arrays.stream(files)
                .forEach(file -> System.out.println(file.getName()));
    }
}

