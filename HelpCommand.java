import java.util.ArrayList;
import java.util.List;

public class HelpCommand implements ICommand {
    @Override
    public String[] execute(String[] args) {
        List<String> result = new ArrayList<>();

        if (args.length == 0) {
            result.add("\u001B[35mpwd   - Prints the current working directory.");
            result.add("cd    - Changes the current directory to the specified directory.");
            result.add("ls    - Lists the files in the current directory.");
            result.add("ls -a - Lists all files, including hidden files.");
            result.add("ls -r - Lists the files in reverse order.");
            result.add("mkdir - Creates a new directory with the specified name.");
            result.add("rmdir - Removes the specified empty directory.");
            result.add("touch - Creates a new empty file or updates the timestamp of an existing file.");
            result.add("mv    - Moves or renames files or directories.");
            result.add("rm    - Deletes the specified file.");
            result.add("cat   - Displays the contents of the specified file.");
            result.add("exit  - Close the terminal window.");
            result.add(">     - Redirects output to a file (overwrites).");
            result.add(">>    - Redirects output to a file (appends).");
            result.add("|     - Pipes the output of one command to another command.\u001B[0m");
        }
        else {
            result.add("\u001B[31mThis command is not supported. Use 'help' for a list of commands.\u001B[0m");
        }
        return (result.toArray(new String[0]));
    }
}
