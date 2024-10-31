// CdTest.java
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.os.CdCommand;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.DosFileAttributeView;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CdTest {

    private File tempDir;

    @BeforeEach
    void setUp() throws IOException {
        tempDir = new File("testDir");
        tempDir.mkdir();
        System.setProperty("user.dir", tempDir.getAbsolutePath());

        new File(tempDir, "visibleFile.txt").createNewFile();
        File hiddenFile = new File(tempDir, ".hiddenFile.txt");
        hiddenFile.createNewFile();
        new File(tempDir, "subDir").mkdir();

        Path hiddenFilePath = hiddenFile.toPath();
        Files.getFileAttributeView(hiddenFilePath, DosFileAttributeView.class).setHidden(true);
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

    private String captureCommandOutput(Runnable commandExecution) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            commandExecution.run();
            return outputStream.toString().trim();
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void testChangeToParentDirectory() {
        CdCommand cdCommand = new CdCommand();
        File parentDir = tempDir.getParentFile();

        if (parentDir != null) {
            cdCommand.execute(new String[]{".."});
            assertEquals(parentDir.getAbsolutePath(), System.getProperty("user.dir"));
        }
    }

    @Test
    void testChangeToRootDirectory() {
        CdCommand cdCommand = new CdCommand();
        cdCommand.execute(new String[]{"/"});
        assertEquals(new File("/").getAbsolutePath(), System.getProperty("user.dir"));
    }

    @Test
    void testChangeToSpecificDirectory() {
        CdCommand cdCommand = new CdCommand();
        cdCommand.execute(new String[]{"subDir"});
        assertEquals(new File(tempDir, "subDir").getAbsolutePath(), System.getProperty("user.dir"));
    }

    @Test
    void testChangeToNonExistentDirectory() {
        CdCommand cdCommand = new CdCommand();
        cdCommand.execute(new String[]{"nonExistentDir"});
        assertEquals(tempDir.getAbsolutePath(), System.getProperty("user.dir"));
    }
}
