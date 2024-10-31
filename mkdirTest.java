package org.example;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;

class mkdirTest {
    @BeforeEach
    public void setUp() {
        String [] args = {"CTest", "D", "A", "M"};
        var RMDIRTester = new RMDIR();
        RMDIRTester.execute(args);
    }
    @Test
    public void TestCreatingOneDirectory() {
        String Dir = "CTest";
        String[] args = {Dir};
        mkdir mkdirTester = new mkdir();
        mkdirTester.execute(args); // Move to directory

        //the dir should appear
        File createdDir = new File(Dir);
        assertTrue(createdDir.exists(), "Directory should exist after creation.");//eno et3mal aslan
        assertTrue(createdDir.isDirectory(), "Created path should be a directory.");//eno fe al dir al sa7
    }

    @Test
    public void TestCreatingMultipleDirectories() {
        String[] args = {"D", "A", "M"};
        mkdir mkdirTester = new mkdir();
        mkdirTester.execute(args);

        //the dir should appear
        for (int i=0; i<args.length; i++) {
            File createdDir = new File(args[i]);
            assertTrue(createdDir.exists(), "Directory should exist after creation.");//eno et3mal aslan
            assertTrue(createdDir.isDirectory(), "Created path should be a directory.");//eno fe al dir al sa7
        }
    }
}