import org.example.MV;
import org.junit.jupiter.api.*;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;

public class MVTest {
    @Test
    public void testMoveFile() {

        String srcfile = "Menna.txt";
        String destfile = "try/Menna.txt";
        MV.moveOrRename(srcfile, destfile); // Move to directory

        //lazem ykon deleted al src
        File sourceFile = new File(srcfile);
        assertFalse(sourceFile.exists(), "Source file should not exist after moving.");

        //w ykon rah fe al destination bgd
        File destFile = new File(destfile);
        assertTrue(destFile.exists(), "File should exist in the destination after moving.");

    }

    @Test
    public void testRenameFile() {
        String srcfile = "Noraa.txt";
        String destfile = "Farah.txt";

        MV.moveOrRename(srcfile, destfile); // Rename in the same directory

        File renamedFile = new File(destfile);
        assertTrue(renamedFile.exists(), "File should exist with the new name after renaming.");
        assertFalse(new File(srcfile).exists(), "Source file should not exist after renaming.");
    }
}
