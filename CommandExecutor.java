import java.util.HashMap;
import java.util.Map;

public class CommandExecutor {
    private static Map<String, Command> commands = new HashMap<>();

    static {
        commands.put("pwd" , new PwdCommand());
        commands.put("help" , new HelpCommand());
        commands.put("exit" , new ExitCommand());
        commands.put("touch" , new TouchCommand());
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

        Command cmd = commands.get(commandName); // take command name and return object

        if (cmd != null) {
            cmd.execute(args); // the function we override in every class implement the interface
        }
        else {
            System.out.println("\u001B[31m'" + commandName + "' is not recognized as an internal or external command.\u001B[0m");
        }

    }

    private static void specialCommand(String command){
        if (command.contains("|")) {
           // handle | case
        }
        else if (command.contains(">")) {
            // handle > case
        }
        else if (command.contains(">>")) {
            // handle >> case
        }
    }
}