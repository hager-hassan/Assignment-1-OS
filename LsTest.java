import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.os.LsCommand;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class LsCommandTest {
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
    void testListVisibleFiles() {
        LsCommand lsCommand = new LsCommand();

        // Execute the command and capture the output
        String[] results = lsCommand.execute(new String[]{});

        // Check that only visible files and directories are listed
        assertTrue(Arrays.asList(results).contains("visibleFile.txt"), "visibleFile.txt should be listed");
        assertTrue(Arrays.asList(results).contains("subDir"), "subDir should be listed");
        assertFalse(Arrays.asList(results).contains(".hiddenFile.txt"), ".hiddenFile.txt should not be listed");
    }
}
