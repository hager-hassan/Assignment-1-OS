import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PwdCommand implements ICommand {
    @Override
    public String[] execute(String[] args) {

        List<String> result = new ArrayList<>();

        if (args.length == 0) {
            String userDir = System.getProperty("user.dir");
            result.add(userDir);
        }
        else{
            result.add("\u001B[31mThis command is not supported. Use 'pwd' to get the current working directory.\u001B[0m");
        }
        return (result.toArray(new String[0]));
    }
}
