import java.util.ArrayList;
import java.util.List;

public class ExitCommand implements ICommand {
    @Override
    public String[] execute(String[] args){
        List<String> result = new ArrayList<>();

        if(args.length == 0){
            System.out.println("\u001B[33mExiting Terminal...\u001B[0m");
            System.exit(0); // to close the program
        }
        else{
            result.add("\u001B[31mThis command is not supported. Use 'exit' to close the terminal window.\u001B[0m");
        }
        return (result.toArray(new String[0]));
    }
}
