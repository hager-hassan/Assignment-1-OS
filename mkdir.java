package org.example;
import java.io.File;

import java.util.ArrayList;
import java.util.List;
public class mkdir {
    public String[] execute (String[] args) {
        List<String> result = new ArrayList<>();
        boolean createParents = false;
        int startIndex = 0;

        if (args.length > 0 && args[0].equals("-p")) {
            createParents = true;
            startIndex = 1;
        }

        if (startIndex >= args.length) {
            result.add("Usage: java org.example.mkdir [-p] <directory1> <directory2> ...");
        }

            for (int i = startIndex; i < args.length; i++) {
                String dirName = args[i];
                File dir = new File(args[i]);
                if (dir.exists()) {
                    result.add("Directory already exists: " + dirName);
                }
                else {
                    if (createParents) {
                        dir.mkdirs();//create parent dir needed law kan fe -p
                        result.add("Successfully created directory and any needed parent directories: " + args[1]);
                    }
                    else {
                        dir.mkdir();
                        result.add("Successfully created directory: " + dirName);
                    }
                }
            }
            return (result.toArray(new String[0]));
        }
    }

