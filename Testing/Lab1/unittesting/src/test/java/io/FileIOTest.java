package io;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FileIOTest {

    private FileIO fileIO = new FileIO();

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void readFile() {
        int[] expected = {3, 9, 0, 2, 10, 9, 3, 8, 0, 3};
        int[] actual = fileIO.readFile("E:\\Semester06\\testing\\unittesting\\src\\test\\resources\\grades_valid.txt");
        assertArrayEquals(expected, actual);
    }

    @Test (expected = IllegalArgumentException.class)
    public void fileDoesNotExist() {
        fileIO.readFile("E:\\Semester06\\testing\\unittesting\\src\\test\\resources\\grades.txt");
    }

    @Test (expected = IllegalArgumentException.class)
    public void fileIsEmpty() {
        fileIO.readFile("E:\\Semester06\\testing\\unittesting\\src\\test\\resources\\empty_file.txt");
    }

    @Test (expected = NumberFormatException.class)
    public void invalidEntry() {
        fileIO.readFile("E:\\Semester06\\testing\\unittesting\\src\\test\\resources\\grades_invalid.txt");
    }
}