package org.example;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class RMDIRTest {
    @BeforeEach
    public void setup() {
        // Ensure directories are created before each test
        new File("Team").mkdirs();

        File TesttDir = new File("Testt");
        TesttDir.mkdirs();
        new File(TesttDir, "subdir").mkdirs();
    }

    @Test
    public void TestDeletionOneDirectory() {
        String Dir = "Team";
        String[] args = {Dir};
        RMDIR.main(args); // Move to directory

        //the dir should appear
        File createdDir = new File(Dir);
        assertFalse(createdDir.exists(), "Directory shouldn't exist after creation.");//eno etmasa7
    }

    @Test
    public void TestCreatingMultipleDirectories() {
        String Dir = "Testt";
        String[] args = {"-r", Dir};
        RMDIR.main(args); // Move to directory

        File createdDir = new File(args[1]);
        assertFalse(createdDir.exists(), "Directory shouldn't exist after creation.");//eno etmasa7
    }

}