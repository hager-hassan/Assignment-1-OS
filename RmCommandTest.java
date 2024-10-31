package org.example;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

class RmCommandTest {
    private RmCommand rmCommand;
    private File testFile;
    private File testDir;
    private File nestedDir;
    private File nestedFile;

    @BeforeEach
    public void setUp() throws IOException {
        rmCommand = new RmCommand();

        // Create a test file
        testFile = new File("testFile.txt");
        try (FileWriter writer = new FileWriter(testFile)) {
            writer.write("This is a test file.");
        }

        // Create a test directory
        testDir = new File("testDir");

        if (!testDir.exists()) {
            testDir.mkdir(); // Create if it doesn't exist
        } else {
            // Ensure it's empty
            for (File file : testDir.listFiles()) {
                file.delete();
            }
        }

        // Create a nested directory and file
        nestedDir = new File(testDir, "nestedDir");
        nestedDir.mkdir();
        nestedFile = new File(nestedDir, "nestedFile.txt");
        try (FileWriter writer = new FileWriter(nestedFile)) {
            writer.write("This is a nested file.");
        }
    }

    @Test
    public void testDeleteFile() {

        String[] args = {"-r", "testFile.txt"};
        String[] result = rmCommand.execute(args);
        assertFalse(testFile.exists(), "The file should be deleted.");
    }

    @Test
    public void testDeleteEmptyDirectory() {
        File farah = new File("farah");
        String[] args = {"-d","farah"};
        String[] result = rmCommand.execute(args);
        assertFalse(farah.exists(), "The empty directory should be deleted.");
    }

    @Test
    public void testDeleteNonEmptyDirectory() {
        String[] args = {"-d",nestedDir.getPath()};
        String[] result = rmCommand.execute(args);
        assertEquals("Error: Directory '" + nestedDir.getPath() + "' is not empty. Use -r to delete non-empty directories.", result[0]);
    }

    @Test
    public void testDeleteDirectoryRecursively() {
        String[] args = {"-r",testDir.getPath()};
        String[] result = rmCommand.execute(args);
        assertEquals("success", result[0]);
        assertFalse(testDir.exists(), "The directory should be deleted.");
        assertFalse(nestedFile.exists(), "The nested file should also be deleted.");
    }

    @Test
    public void testFileDoesNotExist() {
        String[] args = {"-r","nonexistent.txt"};
        String[] result = rmCommand.execute(args);
        assertEquals("Error: File or directory 'nonexistent.txt' does not exist.", result[0]);
    }

    @AfterEach
    public void tearDown() {
        // Cleanup any remaining files or directories
        if (testFile.exists()) {
            testFile.delete();
        }
        if (nestedFile.exists()) {
            nestedFile.delete();
        }
        if (nestedDir.exists()) {
            nestedDir.delete();
        }
        if (testDir.exists()) {
            testDir.delete();
        }
    }

}