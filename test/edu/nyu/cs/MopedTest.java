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
    int[][] mockLocations = {
        {10, 5},
        {125, 10},
        {2, 1},
        {200, 2}
    };

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

    /**
     * Test any kind of movement
     * @param mockOrientation The current moped orientation (i.e. "north", "south", "east", "west")
     * @param movements A String array indicating a series of movements to perform (i.e. "straight", "backwards", "left", "right")
     * @param mockLocations A two-dimensional array of coordinates from which to start.
     * @param expectedLocations A two-dimensional array of coordinates where we expect the moped to end up.
     */
    public void testMovement(String mockOrientation, String[] mockMovementSequence, int[][] mockLocations, int[][] expectedLocations, String[] expectedOrientations) {
        for (int i=0; i<mockLocations.length; i++) {
            int[] mockLocation = mockLocations[i]; // set initial location
            int[] expectedLocation = expectedLocations[i]; // where we expect moped to end up
            String expectedOrientation = expectedOrientations[i]; // expected orientation at end
            try {
                m.setOrientation(mockOrientation); // set initial orientation
                m.setLocation(mockLocation); // move moped to starting location
            }
            catch (Exception e) {
                assertEquals(
                    "Expected program not to crash when setting moped's location and orientation.", 
                    String.format("Program crashed when setLocation or setOrientation were called location to put moped at %s facing %s: %s", Arrays.toString(mockLocation), mockOrientation, e)
                );
            }
            // implement the movements
            for (String mockMovementType : mockMovementSequence) {
                try {
                    switch (mockMovementType) {
                        case "left":
                            m.goLeft();
                            break;
                        case "right":
                            m.goRight();
                            break;
                        case "straight":
                            m.goStraight();
                            break;
                        case "backwards":
                            m.goBackwards();
                            break;
                    }
                }
                catch (Exception e) {
                    assertEquals(
                        String.format("Expected program not to crash when trying to move %s.", mockMovementType),
                        String.format("Program crashed when moving %s to %s: %s", mockMovementType, Arrays.toString(mockLocation), e)
                    );
                }
            }

            // test that the updated location matches expectations
            try {
                int[] actualLocation = m.getLocation();
                String actualOrientation = m.getOrientation();
                boolean isSameLocation = Arrays.equals(expectedLocation, actualLocation);
                boolean isSameOrientation = expectedOrientation.equals(actualOrientation);
                if (!isSameLocation && isSameOrientation) assertEquals(
                    String.format("Expected a moped facing %s going %s from %s to end up at %s facing %s.", mockOrientation, Arrays.toString(mockMovementSequence), Arrays.toString(mockLocation), Arrays.toString(expectedLocation), expectedOrientation),
                    String.format("In fact, the moped ended up at %s facing %s.", Arrays.toString(actualLocation), actualOrientation)
                );
                else {
                    assert(true); // all good
                }
            }
            catch (Exception e) {
                assertEquals(
                    "Expected program not to crash when getting location and orientation.",
                    String.format("Program crashed when moving calling getLocation and/or getOrientation: ", e)
                );
            }
        }
    }

    @Test
    public void testGoingStraight() {
        testGoStraightFacingNorthward();
        testGoStraightFacingSouthward();
        testGoStraightFacingEastward();
        testGoStraightFacingWestward();
    }
    
    public void testGoStraightFacingNorthward() {
        String mockOrientation = "north";
        int[][] expectedLocations = {
            {11, 5},
            {126, 10},
            {3, 1},
            {200, 2}
        };
        String[] expectedOrientations = {
            "north",
            "north",
            "north",
            "north"
        };
        String[] mockMovements = {"straight"};
        testMovement(mockOrientation, mockMovements, mockLocations, expectedLocations, expectedOrientations);
    }

    public void testGoStraightFacingSouthward() {
        String mockOrientation = "south";
        String[] mockMovements = {"straight"};
        int[][] expectedLocations = {
            {9, 5},
            {124, 10},
            {1, 1},
            {199, 2}
        };
        String[] expectedOrientations = {
            "south",
            "south",
            "south",
            "south"
        };
        testMovement(mockOrientation, mockMovements, mockLocations, expectedLocations, expectedOrientations);
    }

    public void testGoStraightFacingEastward() {
        String mockOrientation = "east";
        String[] mockMovements = {"straight"};
        int[][] expectedLocations = {
            {10, 4},
            {125, 9},
            {2, 1},
            {200, 1}
        };
        String[] expectedOrientations = {
            "east",
            "east",
            "east",
            "east"
        };
        testMovement(mockOrientation, mockMovements, mockLocations, expectedLocations, expectedOrientations);
    }
        
    public void testGoStraightFacingWestward() {
        String mockOrientation = "west";
        String[] mockMovements = {"straight"};
        int[][] expectedLocations = {
            {10, 6},
            {125, 10},
            {2, 2},
            {200, 3}
        };
        String[] expectedOrientations = {
            "west",
            "west",
            "west",
            "west"
        };
        testMovement(mockOrientation, mockMovements, mockLocations, expectedLocations, expectedOrientations);
    }
        
    @Test
    public void testGoingBackwards() {
        testGoBackwardsFacingNorthward();
        testGoBackwardsFacingSouthward();
        testGoBackwardsFacingEastward();
        testGoBackwardsFacingWestward();
    }
    
    public void testGoBackwardsFacingNorthward() {
        String mockOrientation = "north";
        String[] mockMovements = {"backwards"};
        int[][] expectedLocations = {
            {9, 5},
            {124, 10},
            {1, 1},
            {199, 2}
        };
        String[] expectedOrientations = {
            "north",
            "north",
            "north",
            "north"
        };
        testMovement(mockOrientation, mockMovements, mockLocations, expectedLocations, expectedOrientations);
    }

    public void testGoBackwardsFacingSouthward() {
        String mockOrientation = "south";
        String[] mockMovements = {"backwards"};
        int[][] expectedLocations = {
            {11, 5},
            {126, 10},
            {3, 1},
            {200, 2}
        };
        String[] expectedOrientations = {
            "south",
            "south",
            "south",
            "south"
        };
        testMovement(mockOrientation, mockMovements, mockLocations, expectedLocations, expectedOrientations);
    }

    public void testGoBackwardsFacingEastward() {
        String mockOrientation = "east";
        String[] mockMovements = {"backwards"};
        int[][] expectedLocations = {
            {10, 6},
            {125, 10},
            {2, 2},
            {200, 3}
        };
        String[] expectedOrientations = {
            "east",
            "east",
            "east",
            "east"
        };
        testMovement(mockOrientation, mockMovements, mockLocations, expectedLocations, expectedOrientations);
    }
        
    public void testGoBackwardsFacingWestward() {
        String mockOrientation = "west";
        String[] mockMovements = {"backwards"};
        int[][] expectedLocations = {
            {10, 4},
            {125, 9},
            {2, 1},
            {200, 1}
        };
        String[] expectedOrientations = {
            "west",
            "west",
            "west",
            "west"
        };
        testMovement(mockOrientation, mockMovements, mockLocations, expectedLocations, expectedOrientations);
    }

    @Test
    public void testTurningLeft() {
        testGoLeftFacingNorthward();
        testGoLeftFacingSouthward();
        testGoLeftFacingEastward();
        testGoLeftFacingWestward();
    }
    
    public void testGoLeftFacingNorthward() {
        String mockOrientation = "north";
        String[] mockMovements = {"left"};
        int[][] expectedLocations = {
            {10, 6},
            {125, 10},
            {2, 2},
            {200, 3}
        };
        String[] expectedOrientations = {
            "west",
            "west",
            "west",
            "west"
        };
        testMovement(mockOrientation, mockMovements, mockLocations, expectedLocations, expectedOrientations);
    }

    public void testGoLeftFacingSouthward() {
        String mockOrientation = "south";
        String[] mockMovements = {"left"};
        int[][] expectedLocations = {
            {10, 4},
            {125, 9},
            {2, 1},
            {200, 1}
        };
        String[] expectedOrientations = {
            "east",
            "east",
            "east",
            "east"
        };
        testMovement(mockOrientation, mockMovements, mockLocations, expectedLocations, expectedOrientations);
    }

    public void testGoLeftFacingEastward() {
        String mockOrientation = "east";
        String[] mockMovements = {"left"};
        int[][] expectedLocations = {
            {11, 5},
            {126, 10},
            {3, 1},
            {200, 2}
        };
        String[] expectedOrientations = {
            "north",
            "north",
            "north",
            "north"
        };
        testMovement(mockOrientation, mockMovements, mockLocations, expectedLocations, expectedOrientations);
    }
        
    public void testGoLeftFacingWestward() {
        String mockOrientation = "west";
        String[] mockMovements = {"left"};
        int[][] expectedLocations = {
            {9, 5},
            {124, 10},
            {1, 1},
            {199, 2}
        };
        String[] expectedOrientations = {
            "south",
            "south",
            "south",
            "south"
        };
        testMovement(mockOrientation, mockMovements, mockLocations, expectedLocations, expectedOrientations);
    }

    @Test
    public void testTurningRight() {
        testGoRightFacingNorthward();
        testGoRightFacingSouthward();
        testGoRightFacingEastward();
        testGoRightFacingWestward();
    }

    public void testGoRightFacingNorthward() {
        String mockOrientation = "north";
        String[] mockMovements = {"right"};
        int[][] expectedLocations = {
            {10, 4},
            {125, 9},
            {2, 1},
            {200, 1}
        };
        String[] expectedOrientations = {
            "west",
            "west",
            "west",
            "west"
        };
        testMovement(mockOrientation, mockMovements, mockLocations, expectedLocations, expectedOrientations);
    }

    public void testGoRightFacingSouthward() {
        String mockOrientation = "south";
        String[] mockMovements = {"right"};
        int[][] expectedLocations = {
            {10, 6},
            {125, 10},
            {2, 2},
            {200, 3}
        };
        String[] expectedOrientations = {
            "east",
            "east",
            "east",
            "east"
        };
        testMovement(mockOrientation, mockMovements, mockLocations, expectedLocations, expectedOrientations);
    }

    public void testGoRightFacingEastward() {
        String mockOrientation = "east";
        String[] mockMovements = {"right"};
        int[][] expectedLocations = {
            {9, 5},
            {124, 10},
            {1, 1},
            {199, 2}
        };
        String[] expectedOrientations = {
            "north",
            "north",
            "north",
            "north"
        };
        testMovement(mockOrientation, mockMovements, mockLocations, expectedLocations, expectedOrientations);
    }
        
    public void testGoRightFacingWestward() {
        String mockOrientation = "west";
        String[] mockMovements = {"right"};
        int[][] expectedLocations = {
            {11, 5},
            {126, 10},
            {3, 1},
            {200, 2}
        };
        String[] expectedOrientations = {
            "south",
            "south",
            "south",
            "south"
        };
        testMovement(mockOrientation, mockMovements, mockLocations, expectedLocations, expectedOrientations);
    }

    @Test
    public void testMultiStepMovements() {
        testMultiStepMovementsNorthwards();
        testMultiStepMovementsSouthwards();
        testMultiStepMovementsEastwards();
        testMultiStepMovementsWestwards();
    }

    public void testMultiStepMovementsNorthwards() {
        String mockOrientation = "north";
        String[] mockMovements = {"right", "left", "left", "right", "left"};
        int[][] expectedLocations = {
            {12, 6},    // {10, 5},
            {127, 10},  // {125, 10},
            {4, 3},     // {2, 1},
            {200, 3}    // {200, 2}
        };
        String[] expectedOrientations = {
            "west",
            "west",
            "west",
            "west"
        };
        testMovement(mockOrientation, mockMovements, mockLocations, expectedLocations, expectedOrientations);
    }

    public void testMultiStepMovementsSouthwards() {
        String mockOrientation = "south";
        String[] mockMovements = {"right", "left", "left", "right", "left"};
        int[][] expectedLocations = {
            {8, 4},    // {10, 5},     
            {123, 8},  // {125, 10},   
            {1, 1},     // {2, 1},      
            {198, 1}    // {200, 2}     
        };
        String[] expectedOrientations = {
            "east",
            "east",
            "east",
            "east"
        };
        testMovement(mockOrientation, mockMovements, mockLocations, expectedLocations, expectedOrientations);
    }

    public void testMultiStepMovementsEastwards() {
        String mockOrientation = "east";
        String[] mockMovements = {"right", "left", "left", "right", "left"};
        int[][] expectedLocations = {
            {11, 3},    // {10, 5},     
            {126, 8},  // {125, 10},   
            {3, 1},     // {2, 1},      
            {200, 1}    // {200, 2}     
        };
        String[] expectedOrientations = {
            "north",
            "north",
            "north",
            "north"
        };
        testMovement(mockOrientation, mockMovements, mockLocations, expectedLocations, expectedOrientations);
    }

    public void testMultiStepMovementsWestwards() {
        String mockOrientation = "west";
        String[] mockMovements = {"right", "left", "left", "right", "left"};
        int[][] expectedLocations = {
            {9, 7},    // {10, 5},     
            {124, 10},  // {125, 10},   
            {1, 3},     // {2, 1},      
            {198, 4}    // {200, 2}     
        };
        String[] expectedOrientations = {
            "south",
            "south",
            "south",
            "south"
        };
        testMovement(mockOrientation, mockMovements, mockLocations, expectedLocations, expectedOrientations);
    }

}
