package org.example;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class CommandExecutor {
    protected static Map<String, ICommand> commands = new HashMap<>();

    static {
        commands.put("pwd" , new PwdCommand());
        commands.put("help" , new HelpCommand());
        commands.put("exit" , new ExitCommand());
        commands.put("touch" , new TouchCommand());
        commands.put("cd" , new CdCommand());
        commands.put("ls" , new LsCommand());
        commands.put("ls-a" , new LsaCommand());
        commands.put("ls-r" , new LsrCommand());
        commands.put("mv" , new MvCommand());
        commands.put("rmdir" , new RmdirCommand());
        commands.put("mkdir" , new MkdirCommand());
        commands.put("|", new PipeCommand(new CommandExecutor()));
    }

    public static void executeCommand(String command){
        if(command.contains("|") || command.contains(">") || command.contains(">>")){
            specialCommand(command);
            return;
        }

        String[] parts = command.split(" ");
        String commandName = parts[0];

        String[] args = new String[parts.length - 1];

        if(parts.length > 1){
            System.arraycopy(parts, 1, args, 0, parts.length - 1);
        }

        ICommand cmd = commands.get(commandName); // take command name and return object

        if (cmd != null) {
            String[] result = cmd.execute(args);
            for (String line : result){ // to return the output from the function we override in lines
                System.out.println(line);
            }
        }
        else {
            System.out.println("\u001B[31m'" + commandName + "' is not recognized as an internal or external command.\u001B[0m");
        }

    }

    private static void specialCommand(String command){
        if (command.contains("|")) {
            String[] result = commands.get("|").execute(new String[]{command});
            for (String line : result) {
                System.out.println(line);
            }
            return;
        }
        else if (command.contains(">")) {
            // handle > case
        }
        else if (command.contains(">>")) {
            // handle >> case
        }
    }
}
