import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.os.CdCommand;
import org.os.LsCommand;
import org.os.LsaCommand;
import org.os.LsrCommand;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.DosFileAttributeView;

import static org.junit.jupiter.api.Assertions.*;

class CommandTests {

    private File tempDir;

    @BeforeEach
    void setUp() throws IOException {
        // Create a temporary test directory
        tempDir = new File("testDir");
        tempDir.mkdir();
        System.setProperty("user.dir", tempDir.getAbsolutePath());

        // Create some test files and directories
        new File(tempDir, "visibleFile.txt").createNewFile();
        File hiddenFile = new File(tempDir, ".hiddenFile.txt");
        hiddenFile.createNewFile();
        new File(tempDir, "subDir").mkdir();

        // Set .hiddenFile.txt as hidden on Windows
        Path hiddenFilePath = hiddenFile.toPath();
        Files.getFileAttributeView(hiddenFilePath, DosFileAttributeView.class)
                .setHidden(true);
    }

    @AfterEach
    void tearDown() {
        // Clean up files and directories after each test
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

    @Test // cd ..
    void testChangeToParentDirectory() {
        CdCommand cdCommand = new CdCommand();
        File parentDir = tempDir.getParentFile();

        if (parentDir != null) {
            cdCommand.execute(new String[]{".."});
            assertEquals(parentDir.getAbsolutePath(), System.getProperty("user.dir"));
        } else {
            System.out.println("No parent directory to change to; skipping this part of the test.");
        }
    }

    @Test // cd /
    void testChangeToRootDirectory() {
        CdCommand cdCommand = new CdCommand();
        cdCommand.execute(new String[]{"/"});
        assertEquals(new File("/").getAbsolutePath(), System.getProperty("user.dir"));
    }

    @Test // cd subDir
    void testChangeToSpecificDirectory() {
        CdCommand cdCommand = new CdCommand();
        cdCommand.execute(new String[]{"subDir"});
        assertEquals(new File(tempDir, "subDir").getAbsolutePath(), System.getProperty("user.dir"));
    }

    @Test // cd nonExistentDir
    void testChangeToNonExistentDirectory() {
        CdCommand cdCommand = new CdCommand();
        cdCommand.execute(new String[]{"nonExistentDir"});
        assertEquals(tempDir.getAbsolutePath(), System.getProperty("user.dir"));
    }

    @Test // ls
    void testListVisibleFiles() {
        LsCommand lsCommand = new LsCommand();

        // Verify that .hiddenFile.txt is set as hidden
        File hiddenFile = new File(tempDir, ".hiddenFile.txt");
        assertTrue(hiddenFile.isHidden(), ".hiddenFile.txt should be hidden but is not");

        // Capture ls command output
        String output = captureCommandOutput(() -> lsCommand.execute(new String[]{}));

        assertTrue(output.contains("visibleFile.txt"), "visibleFile.txt should be listed");
        assertTrue(output.contains("subDir"), "subDir should be listed");
        assertFalse(output.contains(".hiddenFile.txt"), ".hiddenFile.txt should not be listed");
    }

    @Test // ls -a
    void testListAllFiles() {
        LsaCommand lsaCommand = new LsaCommand();
        String output = captureCommandOutput(() -> lsaCommand.execute(new String[]{"-a"}));

        assertTrue(output.contains("visibleFile.txt"), "visibleFile.txt should be listed");
        assertTrue(output.contains(".hiddenFile.txt"), ".hiddenFile.txt should be listed");
        assertTrue(output.contains("subDir"), "subDir should be listed");
    }

    @Test // ls -r
    void testListFilesInReverse() {
        LsrCommand lsrCommand = new LsrCommand();
        String output = captureCommandOutput(() -> lsrCommand.execute(new String[]{"-r"}));

        // Verify reverse listing by checking expected order
        String[] lines = output.split(System.lineSeparator());
        assertEquals("visibleFile.txt", lines[0]);
        assertEquals("subDir", lines[1]);
        assertEquals(".hiddenFile.txt", lines[2]);
    }
}
