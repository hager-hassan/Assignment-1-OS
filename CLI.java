package org.example;
import java.util.Scanner;

public class CLI {
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        String command;

        while(true)
        {
            System.out.print(System.getProperty("user.dir")+" > ");
            command = input.nextLine().trim(); // to receive the command from the user and delete white spaces from two ends

            CommandExecutor.executeCommand(command);
        }
    }
}