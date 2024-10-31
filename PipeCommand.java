package org.example;
import java.util.ArrayList;
import java.util.List;

public class PipeCommand implements ICommand{

    private final CommandExecutor executor;

    public PipeCommand(CommandExecutor executor) { // constructor
        this.executor = executor;
    }

    @Override
    public String[] execute(String[] args) {

        List<String> output = new ArrayList<>();
        String[] commands = args[0].split("\\|"); // split command by " | ".

        String[] input = null; // initial input(null) for first command

        for(String cmd : commands){
            cmd = cmd.trim(); // remove spaces from two ends
            String[] parts = cmd.split(" ");
            String commandName = parts[0];  // first word is command name

            String[] commandArgs = new String[parts.length - 1];  // copy command's arguments to this array
            if (parts.length > 1) {
                System.arraycopy(parts, 1, commandArgs, 0, parts.length - 1);
            }

            ICommand command = executor.commands.get(commandName); // choose which command will be executed
            if (command != null) {
                input = command.execute(commandArgs);  // store the result
            } else {
                input = new String[]{"\u001B[31m"+ commandName + " not recognized.\u001B[0m"};
            }
        }

        if (input != null) { // collect output
            for (String line : input) {
                output.add(line);
            }
        }

        return output.toArray(new String[0]);
    }
}
