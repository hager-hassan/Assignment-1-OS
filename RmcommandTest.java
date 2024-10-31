package org.os;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class RmcommandTest {

    private Rmcommand rmCommand;
    private File testFile;
    private File testDir;
    private File nestedDir;
    private File nestedFile;

    @BeforeEach
    public void setUp() throws IOException {
        rmCommand = new Rmcommand();

        // Create a test file
        testFile = new File("testFile.txt");
        try (FileWriter writer = new FileWriter(testFile)) {
            writer.write("This is a test file.");
        }

        // Create a test directory
        testDir = new File("testDir");
        testDir.mkdir();

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
        String[] args = {testFile.getPath()};
        String[] result = rmCommand.excute(args);
        assertEquals("success", result[0]);
        assertFalse(testFile.exists(), "The file should be deleted.");
    }

    @Test
    public void testDeleteEmptyDirectory() {
        String[] args = {testDir.getPath(), "-d"};
        String[] result = rmCommand.excute(args);
        assertEquals("success", result[0]);
        assertFalse(testDir.exists(), "The empty directory should be deleted.");
    }

    @Test
    public void testDeleteNonEmptyDirectory() {
        String[] args = {nestedDir.getPath(), "-d"};
        String[] result = rmCommand.excute(args);
        assertEquals("Error: Directory '" + nestedDir.getPath() + "' is not empty. Use -r to delete non-empty directories.", result[0]);
    }

    @Test
    public void testDeleteDirectoryRecursively() {
        String[] args = {testDir.getPath(), "-r"};
        String[] result = rmCommand.excute(args);
        assertEquals("success", result[0]);
        assertFalse(testDir.exists(), "The directory should be deleted.");
        assertFalse(nestedFile.exists(), "The nested file should also be deleted.");
    }

    @Test
    public void testFileDoesNotExist() {
        String[] args = {"nonexistent.txt"};
        String[] result = rmCommand.excute(args);
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
