import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.os.LsrCommand;

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
    void testListFilesInReverse() {
        LsrCommand lsrCommand = new LsrCommand();

        // Execute the command and capture the output
        String[] results = lsrCommand.execute(new String[]{"-r"});

        // Verify reverse listing by checking expected order
        String[] expected = new String[]{"visibleFile.txt", "subDir", ".hiddenFile.txt"};
        assertArrayEquals(expected, results, "The files should be listed in reverse order");
    }
}
