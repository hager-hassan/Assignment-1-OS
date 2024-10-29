public class ExitCommand implements Command {
    @Override
    public void execute(String[] args){
        if(args.length == 0){
            System.out.println("\u001B[33mExiting Terminal...\u001B[0m");
            System.exit(0); // to close the program
        }
        else{
            System.out.println("\u001B[31mThis command is not supported. Use 'exit' to close the terminal window.\u001B[0m");
        }
    }
}
