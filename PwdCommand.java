import java.io.File;

public class PwdCommand implements Command {
    @Override
    public String execute(String[] args) {

        if (args.length == 0) {
            String userDir = System.getProperty("user.dir");
            return (userDir + '\n');
        }
        else{
            System.out.println("\u001B[31mThis command is not supported. Use 'pwd' to get the current working directory.\u001B[0m");
        }
        return null;
    }
}
