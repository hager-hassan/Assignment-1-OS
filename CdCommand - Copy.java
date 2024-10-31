package org.os;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CdCommand implements Command {
    @Override
    public String[] execute(String[] args) {
        List<String> result = new ArrayList<>();
        String path = args.length > 0 ? args[0] : ".";
        File newDir;

        if (path.equals("..")) {
            newDir = new File(System.getProperty("user.dir")).getParentFile();
        } else if (path.equals("/")) {
            newDir = new File("/");
        } else {
            newDir = new File(System.getProperty("user.dir"), path);
        }

        if (newDir.exists() && newDir.isDirectory()) {
            System.setProperty("user.dir", newDir.getAbsolutePath());
            result.add("Changed directory to: " + newDir.getAbsolutePath());
        } else {
            result.add("Directory not found: " + path);
        }

        return result.toArray(new String[0]);
    }
}


