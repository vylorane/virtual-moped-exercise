// DO NOT TOUCH THIS FILE!
package edu.nyu.cs;

// import junit 4 testing framework
import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.contrib.java.lang.system.SystemOutRule; // system rules lib - useful for capturing system output
// import static org.mockito.Mockito.*; // for mocking, stubbing, and spying
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.*;

import org.junit.After;

public class TestDriveTest {

    @ClassRule
    public static final TextFromStandardInputStream systemInMock = emptyStandardInputStream();

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule();

    @Before
    public void init() {
        // any pre-test setup here
        systemOutRule.enableLog(); // start capturing System.out
    }

    @Test
    public void testMain() {
        String[] args = {};
        try {
            TestDrive.main(args);
            String actual = systemOutRule.getLogWithNormalizedLineSeparator();
            String expected = "Hello world!\n";
            assertEquals(expected, actual);
        }
        catch (Exception e) {
            assertEquals("Expected to be able to run the main method without crashing.", "In fact, the program crashed: " + e);
        }
    }

    
}
