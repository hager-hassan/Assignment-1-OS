import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class LsrCommandTest {
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
    void testRejectsArguments() {
        LsrCommand lsrCommand = new LsrCommand();

        // Execute the command with an invalid argument
        String[] results = lsrCommand.execute(new String[]{"-r"});

        // Expected error message
        String[] expected = {"\u001B[31mError: This command does not accept any arguments.\u001B[0m"};

        // Verify that the output matches the expected error message
        assertArrayEquals(expected, results, "The command should reject arguments with an error message.");
    }


}
