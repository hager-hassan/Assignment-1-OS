import org.example.MV;
import org.junit.jupiter.api.*;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;

public class MVTest {

    private String srcfile = "Mvsrctest.txt";
    private String destfileM = "try/Mvdestfile.txt";
    private String destfileR = "MvRenamedtest.txt";

    @BeforeEach
    public void setUp() throws IOException {
        // Set up the source file for the tests
        File sourceFile = new File(srcfile);
        if (!sourceFile.exists()) {
            sourceFile.createNewFile(); // Create file if it doesn't exist
        }

        // Ensure destination files don't exist before each test
        File destFile = new File(destfileM);
        if (destFile.exists()) {
            destFile.delete();
        }
        File renamedFile = new File(destfileR);
        if (renamedFile.exists()) {
            renamedFile.delete();
        }
    }

    @Test
    public void testMoveFile() {
        //fe assignemnt bara m4 java mn gowa
        MV.moveOrRename(srcfile, destfileM); // Move to directory

        //lazem ykon deleted al src
        File sourceFile = new File(srcfile);
        assertFalse(sourceFile.exists(), "Source file should not exist after moving.");

        //w ykon rah fe al destination bgd
        File destFile = new File(destfileM);
        assertTrue(destFile.exists(), "File should exist in the destination after moving.");

    }

    @Test
    public void testRenameFile() {
        MV.moveOrRename(srcfile, destfileR); // Rename in the same directory

        File renamedFile = new File(destfileR);
        assertTrue(renamedFile.exists(), "File should exist with the new name after renaming.");
        assertFalse(new File(srcfile).exists(), "Source file should not exist after renaming.");
    }
}
