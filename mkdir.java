package org.example;
import java.io.File;

public class mkdir {
    public static void main(String[] args) {
        boolean createParents = false;
        int startIndex = 0;

        if (args.length > 0 && args[0].equals("-p")) {
            createParents = true;
            startIndex = 1;
        }

        if (startIndex >= args.length) {
            System.err.println("Usage: java org.example.mkdir [-p] <directory1> <directory2> ...");
        }

            for (int i = startIndex; i < args.length; i++) {
                String dirName = args[i];
                File dir = new File(args[i]);
                if (dir.exists()) {
                    System.err.println("Directory already exists: " + dirName);
                }
                else {
                    if (createParents) {
                        dir.mkdirs();//create parent dir needed law kan fe -p
                        System.out.println("Successfully created directory and any needed parent directories: " + args[1]);
                    }
                    else {
                        dir.mkdir();
                        System.out.println("Successfully created directory: " + dirName);
                    }

                }
            }
        }
    }

