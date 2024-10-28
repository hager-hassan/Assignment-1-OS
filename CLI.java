import java.util.Scanner;

public class CLI {
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        String command;

        while(true)
        {
            System.out.print("> ");
            command = input.nextLine(); // to receive the command from the user

            if(command.equals("exit")) // to end program
            {
                System.out.println("\u001B[33mExiting CLI...\u001B[0m");
                break;
            }

            CommandExecutor.executeCommand(command);

        }
        input.close(); // make resources free
    }
}

