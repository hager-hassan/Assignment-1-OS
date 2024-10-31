import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        String[] results = lsaCommand.execute(new String[]{"-a"});

        // Check that all files, including hidden ones, are listed
        assertTrue(Arrays.asList(results).contains("visibleFile.txt"), "visibleFile.txt should be listed");
        assertTrue(Arrays.asList(results).contains(".hiddenFile.txt"), ".hiddenFile.txt should be listed");
        assertTrue(Arrays.asList(results).contains("subDir"), "subDir should be listed");
    }
}
