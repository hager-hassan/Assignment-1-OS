import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TouchCommand implements ICommand {
    @Override
public String[] execute(String[] args)
    {
        List<String> result = new ArrayList<>();

        if(args.length == 0){
            result.add("\u001B[31mThis command is not supported.\u001B[0m");
        }

        for(String fileName : args){
            File myFile = new File(fileName);

            try {
                if(myFile.createNewFile()){
                    result.add(fileName + " is created.");
                }
                else{
                    result.add("File already exists. Updated timestamp for " + myFile.getName());
                    boolean success = myFile.setLastModified(System.currentTimeMillis());
                    if(!success){
                        result.add("\u001B[31mFailed to update the timestamp for \u001B[0m" + myFile.getName() + ".");
                    }
                }
            }
            catch (Exception ex) {
                result.add("\u001B[31mAn error occurred with " + fileName + ".\u001B[0m");
            }
        }
        return (result.toArray(new String[0]));
    }
}
