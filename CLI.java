import java.io.File;
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
                System.out.println("Exiting CLI...");
                break;
            }

            executeCommand(command); // to execute commands
        }
        input.close(); // make resources free
    }

    public static void executeCommand(String command)
    {
        switch(command)
        {
            case "pwd":
                pwd();
                break;
        }
    }

    public static void pwd()
    {
        File myFile = new File("CLI.java");
        System.out.println(myFile.getAbsolutePath());
    }
}

