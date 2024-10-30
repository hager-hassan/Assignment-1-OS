package org.os;

import java.io.File;

public class CdCommand implements Command {
    @Override
    public void execute(String[] args) {
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
            System.out.println("Changed directory to: " + newDir.getAbsolutePath());
        } else {
            System.out.println("Directory not found: " + path);
        }
    }
}


//package org.os;
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.Arrays;
//import java.util.Comparator;
//
//public class cd {
//
//    public static void main(String[] args) {
//        CommandLineInterpreter cli = new CommandLineInterpreter();
//        cli.start();
//    }
//
//    static class CommandLineInterpreter {
//
//        public void start() {
//            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//            String command;
//
//            System.out.println("Welcome to the CLI! Type 'help' for available commands.");
//
//            while (true) {
//                System.out.print("> ");
//                try {
//                    command = reader.readLine();
//                    if (command.equals("exit")) {
//                        System.out.println("Exiting...");
//                        break;
//                    } else if (command.equals("help")) {
//                        showHelp();
//                    } else if (command.startsWith("cd ")) {
//                        String path = command.substring(3); // Extract the path after "cd "
//                        changeDirectory(path);
//                    } else if (command.startsWith("ls")) {
//                        listDirectory(command);
//                    } else {
//                        executeCommand(command);
//                    }
//                } catch (IOException e) {
//                    System.err.println("Error reading input: " + e.getMessage());
//                }
//            }
//        }
//
//        private void executeCommand(String command) {
//            try {
//                ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
//                processBuilder.inheritIO(); // Inherit IO of the current process
//                Process process = processBuilder.start();
//                process.waitFor(); // Wait for the process to finish
//            } catch (IOException | InterruptedException e) {
//                System.err.println("Error executing command: " + e.getMessage());
//            }
//        }
//
//        private void changeDirectory(String path) {
//            File newDir;
//
//            // Handle special cases for ".." and "/"
//            if (path.equals("..")) {
//                newDir = new File(System.getProperty("user.dir")).getParentFile(); // Go to the parent directory
//            } else if (path.equals("/")) {
//                newDir = new File("/"); // Go to the root directory
//            } else {
//                newDir = new File(System.getProperty("user.dir"), path); // Go to the specified directory
//            }
//
//            // Check if the directory exists
//            if (newDir.exists() && newDir.isDirectory()) {
//                System.setProperty("user.dir", newDir.getAbsolutePath());
//                System.out.println("Changed directory to: " + newDir.getAbsolutePath());
//            } else {
//                System.out.println("Directory not found: " + path);
//            }
//        }
//
//        private void listDirectory(String command) {
//            File currentDir = new File(System.getProperty("user.dir"));
//            File[] files = currentDir.listFiles();
//
//            if (files == null) {
//                System.out.println("Unable to access directory contents.");
//                return;
//            }
//
//            // Check command options
//            if (command.equals("ls -a")) {
//                // List all files, including hidden
//                Arrays.stream(files)
//                        .forEach(file -> System.out.println(file.getName()));
//            } else if (command.equals("ls -r")) {
//                // List files in reverse order
//                Arrays.stream(files)
//                        .sorted(Comparator.reverseOrder())
//                        .forEach(file -> System.out.println(file.getName()));
//            } else if (command.equals("ls")) {
//                // Default listing (non-hidden files only)
//                Arrays.stream(files)
//                        .filter(file -> !file.isHidden())
//                        .forEach(file -> System.out.println(file.getName()));
//            } else {
//                System.out.println("Invalid ls command. Use 'ls', 'ls -a', or 'ls -r'.");
//            }
//        }
//
//        private void showHelp() {
//            System.out.println("Available commands:");
//            System.out.println("  exit   - Terminate the CLI");
//            System.out.println("  help   - Display available commands");
//            System.out.println("  cd     - Change directory (Usage: cd <directory_path>, cd .., or cd /)");
//            System.out.println("  ls     - List directory contents");
//            System.out.println("  ls -a  - List all files including hidden");
//            System.out.println("  ls -r  - List files in reverse order");
//        }
//    }
//}
