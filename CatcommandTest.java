package org.os;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach; // Update to JUnit 5
import org.junit.jupiter.api.BeforeEach; // Update to JUnit 5
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
class CatcommandTest {
    private Catcommand catCommand;
    private File testDir;
    private File testFile1;
    private File testFile2;

    @BeforeEach
    public void setUp() throws IOException {
        catCommand = new Catcommand();
        testDir = new File("testDir");
        testFile1 = new File(testDir, "file1.txt");
        testFile2 = new File(testDir, "file2.txt");

        // Create test directory and files
        if (!testDir.exists()) {
            testDir.mkdir();
        }
        try (FileWriter writer1 = new FileWriter(testFile1);
             FileWriter writer2 = new FileWriter(testFile2)) {
            writer1.write("Hello from file 1.");
            writer2.write("Hello from file 2.");
        }
    }

    @Test
    public void testExecuteWithWildCard() {
        String[] args = {"*"};
        String[] result = catCommand.excute(args);
        assertNotNull(result);
        assertTrue(result.length > 0); // Check that we have some output
        assertTrue(Arrays.asList(result).stream().anyMatch(s -> s.contains("Hello from file 1."))); // Check if file 1 content is in output
        assertTrue(Arrays.asList(result).stream().anyMatch(s -> s.contains("Hello from file 2."))); // Check if file 2 content is in output
    }

    @AfterEach
    public void tearDown() {
        // Cleanup test files and directory
        if (testFile1.exists()) {
            testFile1.delete();
        }
        if (testFile2.exists()) {
            testFile2.delete();
        }
        if (testDir.exists()) {
            testDir.delete();
        }
    }
}
