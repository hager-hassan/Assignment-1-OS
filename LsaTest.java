import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.os.LsaCommand;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class LsaCommandTest {
    private File tempDir;

    @BeforeEach
    void setUp() throws IOException {
        tempDir = new File("testDir");
        tempDir.mkdir();
        System.setProperty("user.dir", tempDir.getAbsolutePath());

        new File(tempDir, "visibleFile.txt").createNewFile();
        new File(tempDir, ".hiddenFile.txt").createNewFile();
        new File(tempDir, "subDir").mkdir();
    }

    @AfterEach
    void tearDown() {
        if (tempDir.exists()) {
            for (File file : tempDir.listFiles()) {
                file.delete();
            }
            tempDir.delete();
        }
    }

    @Test
    void testListAllFiles() {
        LsaCommand lsaCommand = new LsaCommand();

        // Execute the command and capture the output
        String[] results = lsaCommand.execute(new String[]{});

        // Check that all files, including hidden ones, are listed
        assertTrue(Arrays.asList(results).contains("visibleFile.txt"), "visibleFile.txt should be listed");
        assertTrue(Arrays.asList(results).contains(".hiddenFile.txt"), ".hiddenFile.txt should be listed");
        assertTrue(Arrays.asList(results).contains("subDir"), "subDir should be listed");
    }

    @Test
    void testRejectsArguments() {
        LsaCommand lsaCommand = new LsaCommand();

        // Execute the command with an invalid argument
        String[] results = lsaCommand.execute(new String[]{"-a"});

        // Expected error message
        String[] expected = {"\u001B[31mError: This command does not accept any arguments.\u001B[0m"};

        // Verify that the output matches the expected error message
        assertArrayEquals(expected, results, "The command should reject arguments with an error message.");
    }
}
