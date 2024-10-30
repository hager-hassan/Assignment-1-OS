import java.io.File;

public class TouchCommand implements Command {
    @Override
public String execute(String[] args)
    {
        StringBuilder result = new StringBuilder();

        if(args.length == 0){
            System.out.println("\u001B[31mThis command is not supported.\u001B[0m");
            return null;
        }

        for(String fileName : args){
            File myFile = new File(fileName);

            try {
                if(myFile.createNewFile()){
                    result.append(fileName).append(" is created.\n");
                }
                else{
                    result.append("File already exists. Updated timestamp for ").append(myFile.getName()).append("\n");
                    boolean success = myFile.setLastModified(System.currentTimeMillis());
                    if(!success){
                        result.append("\u001B[31mFailed to update the timestamp for \u001B[0m").append(myFile.getName()).append(".\n");
                    }
                }
            }
            catch (Exception ex) {
                result.append("\u001B[31mAn error occurred with ").append(fileName).append(".\u001B[0m\n");
            }
        }
        return (result.toString());
    }
}
