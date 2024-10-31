package org.example;
import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;

class mkdirTest {
    @Test
    public void TestCreatingOneDirectory() {
        String Dir = "Amrr";
        String[] args = {Dir};
        mkdir.main(args); // Move to directory

        //the dir should appear
        File createdDir = new File(Dir);
        assertTrue(createdDir.exists(), "Directory should exist after creation.");//eno et3mal aslan
        assertTrue(createdDir.isDirectory(), "Created path should be a directory.");//eno fe al dir al sa7
    }

    @Test
    public void TestCreatingMultipleDirectories() {
        String[] args = {"M", "F", "N"};
        mkdir.main(args); // Move to directory

        //the dir should appear
        for (int i=0; i<args.length; i++) {
            File createdDir = new File(args[i]);
            assertTrue(createdDir.exists(), "Directory should exist after creation.");//eno et3mal aslan
            assertTrue(createdDir.isDirectory(), "Created path should be a directory.");//eno fe al dir al sa7
        }
    }
}