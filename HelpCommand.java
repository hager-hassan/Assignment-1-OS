public class HelpCommand implements Command {
    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            System.out.println("pwd   - Prints the current working directory.");
            System.out.println("cd    - Changes the current directory to the specified directory.");
            System.out.println("ls    - Lists the files in the current directory.");
            System.out.println("ls -a - Lists all files, including hidden files.");
            System.out.println("ls -r - Lists the files in reverse order.");
            System.out.println("mkdir - Creates a new directory with the specified name.");
            System.out.println("rmdir - Removes the specified empty directory.");
            System.out.println("touch - Creates a new empty file or updates the timestamp of an existing file.");
            System.out.println("mv    - Moves or renames files or directories.");
            System.out.println("rm    - Deletes the specified file.");
            System.out.println("cat   - Displays the contents of the specified file.");
            System.out.println("exit  - Close the terminal window.");
            System.out.println(">     - Redirects output to a file (overwrites).");
            System.out.println(">>    - Redirects output to a file (appends).");
            System.out.println("|     - Pipes the output of one command to another command.");
        }
        else {
            System.out.println("\u001B[31mThis command is not supported. Use 'help' for a list of commands.\u001B[0m");
        }
    }
}
