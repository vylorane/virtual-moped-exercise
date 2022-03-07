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

import java.util.Arrays;

import org.junit.After;

public class MopedTest {
    Moped m;

    @ClassRule
    public static final TextFromStandardInputStream systemInMock = emptyStandardInputStream();

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule();

    @Before
    public void init() {
        // any pre-test setup here
        try {
            m = new Moped();
        }
        catch (Exception e) {
            assertEquals("Expected to be able to instantiate a new Moped object using a no-args constructor", "In fact, we are unable to instantiate a Moped object using a no-args constructor.");
        }
    }

    @After
    public void cleanup() {
        // any post-test knockdown here
        m = null;
    }
    
    @Test
    public void testInitialLocation() {
        int[] expectedLocation = {10, 5}; // 10th st and 5th ave
        int[] actualLocation = m.getLocation();
        if (!Arrays.equals(actualLocation, expectedLocation)) {
            assertEquals(
                String.format("Expected the moped's initial location to be %s.", Arrays.toString(expectedLocation)), 
                String.format("In fact, the initial location of your moped is %s", Arrays.toString(actualLocation))
            );
        }
    }

    @Test
    public void testInitialOrientation() {
        String expectedOrientation = "south"; // lowercase
        String actualOrientation = m.getOrientation().toLowerCase();
        if (!expectedOrientation.equals(actualOrientation)) {
            assertEquals(
                String.format("Expected the moped's initial orientation to be %s.", expectedOrientation), 
                String.format("In fact, the initial orientation of your moped is %s", actualOrientation)
            );
        }
    }

    @Test
    public void testTurnLeft() {
        m.goLeft();        
    }

    @Test
    public void testTurnRight() {
        m.goRight();        
    }

    @Test
    public void testGoStraightNorthward() {
        String mockOrientation = "north";
        int[][] mockLocations = {
            {10, 5},
            {125, 10},
            {2, 1},
            {200, 2}
        };
        int[][] expectedLocations = {
            {11, 5},
            {126, 10},
            {3, 1},
            {200, 2}
        };
        m.setOrientation(mockOrientation);
        for (int i=0; i<mockLocations.length; i++) {
            int[] mockLocation = mockLocations[i];
            int[] expectedLocation = expectedLocations[i];
            m.setLocation(mockLocation);
            m.goStraight();
            int[] actualLocation = m.getLocation();
            boolean isSame = Arrays.equals(expectedLocation, actualLocation);
            if (!isSame) assertEquals(
                String.format("Expected moped to end up at %s when going straight facing %s from %s.", Arrays.toString(expectedLocation), mockOrientation, Arrays.toString(mockLocation)),
                String.format("In fact, the moped ended up at %s.", Arrays.toString(actualLocation))
            );
            else {
                assert(true);
            }
        }
    }

    @Test
    public void testGoStraightSouthward() {
        String mockOrientation = "south";
        int[][] mockLocations = {
            {10, 5},
            {125, 10},
            {2, 1},
            {1, 2}
        };
        int[][] expectedLocations = {
            {9, 5},
            {124, 10},
            {1, 1},
            {1, 2}
        };
        m.setOrientation(mockOrientation);
        for (int i=0; i<mockLocations.length; i++) {
            int[] mockLocation = mockLocations[i];
            int[] expectedLocation = expectedLocations[i];
            m.setLocation(mockLocation);
            m.goStraight();
            int[] actualLocation = m.getLocation();
            boolean isSame = Arrays.equals(expectedLocation, actualLocation);
            if (!isSame) assertEquals(
                String.format("Expected moped to end up at %s when going straight facing %s from %s.", Arrays.toString(expectedLocation), mockOrientation, Arrays.toString(mockLocation)),
                String.format("In fact, the moped ended up at %s.", Arrays.toString(actualLocation))
            );
            else {
                assert(true);
            }
        }
    }

    @Test
    public void testBackUp() {
        m.goBackwards();        
    }
}
