package edu.nyu.cs;

import java.util.Arrays;

/**
 * A virtual moped, roaming the streets of New York.
 * The signatures of a few methods are given and must be completed and used as indicated.
 * None of these methods should print anything.
 * The rest is up to you.
 */
public class Moped {

    // a set of constant-like values representing each of the cardinal directions
    public static enum Cardinality {
        NORTH,
        SOUTH,
        EAST,
        WEST
    }

    public static final int GAS_USED_PER_BLOCK = 5;

    // start off at 10th st and 5th ave
    private int street = 10;
    private int ave = 5;
    private Cardinality orientation = Cardinality.SOUTH;
    private int gas = 100;
    private int MAX_STREET = 200;
    private int MIN_STREET = 1;
    private int MAX_AVE = 10;
    private int MIN_AVE = 1;

    /**
     * Attaches the proper suffix to a number, such as 1st, 2nd, 3rd, 4th, etc.
     * @param num The number to which to attach the suffix, as an int.
     * @return The number with the appropriate suffix attached, as a String.
     */
    private String getNumberWithSuffix(int num) {
        String withSuffix;
        if ( ("" + num).endsWith("1")) withSuffix = num + "st";
        else if ( ("" + num).endsWith("2")) withSuffix = num + "nd";
        else if ( ("" + num).endsWith("3")) withSuffix = num + "rd";
        else withSuffix = num + "th";
        return withSuffix;
    }

    private String getAdvertisementFor(int street, int ave) {
        String ad = "";
        if (street==79 && ave==8) ad = "American Museum of Natural History";
        else if (street==74 && ave==1) ad = "Memorial Sloan Kettering";
        else if (street==56 && ave==3) ad = "Tina's Cuban Cuisine";
        else if (street==12 && ave==4) ad = "The Strand";
        return ad;
    }

    private boolean inBounds(int[] location) {
        int street = location[0];
        int ave = location[1];
        boolean streetInBounds = (street <= MAX_STREET && street >= MIN_STREET);
        boolean aveInBounds = (ave <= MAX_AVE && ave >= MIN_AVE);
        return streetInBounds && aveInBounds;
    }

    /**
     * Sets the orientation of the moped to a particular cardinal direction.
     * @param orientation A string representing which cardinal direction at which to set the orientation of the moped.  E.g. "north", "south", "east", or "west".
     */
    public void setOrientation(String orientation) {
        switch (orientation) {
            case "north":
                this.orientation = Cardinality.NORTH;
                break;
            case "south":
                this.orientation = Cardinality.SOUTH;
                break;
            case "east":
                this.orientation = Cardinality.EAST;
                break;
            case "west":
                this.orientation = Cardinality.WEST;
                break;
        }
    }
    /**
     * Returns the current orientation of the moped, as a lowercase String.
     * E.g. "north", "south", "east", or "west".
     * @return The current orientation of the moped, as a lowercase String.
     */
    public String getOrientation() {
        return this.orientation.toString().toLowerCase();
    }

    /**
     * Prints the current location, by default exactly following the format:
     *      Now at 12th St. and 5th Ave, facing South.
     *
     * If the current location is associated with location-based advertising, this method should print exactly following format:
     *      Now at 12th St. and 4th Ave, facing West.  Did you know The Strand has 18 Miles of new, used and rare books, and has been in business since 1927?
     */
    public void printLocation() {
        String street = getNumberWithSuffix(this.street);
        String ave = getNumberWithSuffix(this.ave);
        String orientation = getOrientation();
        String advertisement = getAdvertisementFor(this.street, this.ave);
        if ( ("" + this.street).endsWith("1")) street = this.street + "st";
        else if ( ("" + this.street).endsWith("2")) street = this.street + "nd";
        else if ( ("" + this.street).endsWith("2")) street = this.street + "nd";
        else if (this.street == 2) street = this.street + "nd";
        String msg = String.format("Now at %s St. and %s Ave, facing %s.%s", street, ave, orientation, advertisement);
        System.out.println(msg);
    }

    /**
     * Handles the command, `go left`.
     * Moves the moped one block to the left, and causes the moped to face the appropriate new cardinal direction.
     * Consumes gas with each block moved, and doesn't move or turn unless there is sufficient gas, as according to the instructions.
     * If attempting to drive off the map, the moped will turn but not move a block.  Turns-only consume no gas.
     * This method must not print anything.
     */
    public void goLeft() {
        // System.out.println(String.format("Going left with %d gas from %s:%s facing %s", this.getGasLevel(), this.street, this.ave, this.getOrientation()));
        if (this.getGasLevel() < 10) return; // don't move without gas
        // System.out.println(String.format("starting left turn from %sward %d:%d", this.orientation, this.street, this.ave));
        switch (this.orientation) {
            case NORTH:
                int[] loc1 = {this.street, this.ave+1};
                // System.out.println("trying to go left from " + this.street + ":" + this.ave + " facing " + this.getOrientation() + " to " + Arrays.toString(loc1));
                this.orientation = Cardinality.WEST;
                if (inBounds(loc1)) {
                    this.ave++;
                    this.gas -= GAS_USED_PER_BLOCK;
                }
                break;
            case SOUTH:
                int[] loc2 = {this.street, this.ave-1};
                // System.out.println("trying to go left from " + this.street + ":" + this.ave + " facing " + this.getOrientation() + " to " + Arrays.toString(loc2));
                this.orientation = Cardinality.EAST;
                if (inBounds(loc2)) {
                    this.ave--;
                    this.gas -= GAS_USED_PER_BLOCK;
                }
                break;
            case EAST:
                int[] loc3 = {this.street+1, this.ave};
                // System.out.println("trying to go left from " + this.street + ":" + this.ave + " facing " + this.getOrientation() + " to " + Arrays.toString(loc3));
                this.orientation = Cardinality.NORTH;
                if (inBounds(loc3)) {
                    this.street++;
                    this.gas -= GAS_USED_PER_BLOCK;
                }
                break;
            case WEST:
                int[] loc4 = {this.street-1, this.ave};
                // System.out.println("trying to go left from " + this.street + ":" + this.ave + " facing " + this.getOrientation() + " to " + Arrays.toString(loc4));
                this.orientation = Cardinality.SOUTH;
                if (inBounds(loc4)) {
                    this.street--;
                    this.gas -= GAS_USED_PER_BLOCK;
                }
                break;
        }
        // System.out.println(String.format("ending left turn at %sward %d:%d", this.orientation, this.street, this.ave));
    }

    /**
     * Handles the command, `go right`.
     * Moves the moped one block to the right, and causes the moped to face the appropriate new cardinal direction.
     * Consumes gas with each block moved, and doesn't move or turn unless there is sufficient gas, as according to the instructions.
     * If attempting to drive off the map, the moped will turn but not move a block.  Turns-only consume no gas.
     * This method must not print anything.
     */
    public void goRight() {
        // System.out.println(String.format("Going right with %d gas from %s:%s facing %s", this.getGasLevel(), this.street, this.ave, this.getOrientation()));
        if (this.getGasLevel() < 10) return; // don't move without gas
        switch (this.orientation) {
            case NORTH:
                int[] loc1 = {this.street, this.ave-1};
                // System.out.println("trying to go right from " + this.street + ":" + this.ave + " facing " + this.getOrientation() + " to " + Arrays.toString(loc1));
                this.orientation = Cardinality.EAST;
                if (inBounds(loc1)) {
                    this.ave--;
                    this.gas -= GAS_USED_PER_BLOCK;
                }
                break;
            case SOUTH:
                int[] loc2 = {this.street, this.ave+1};
                // System.out.println("trying to go right from " + this.street + ":" + this.ave + " facing " + this.getOrientation() + " to " + Arrays.toString(loc2));
                this.orientation = Cardinality.WEST;
                if (inBounds(loc2)) {
                    this.ave++;
                    this.gas -= GAS_USED_PER_BLOCK;
                }
                break;
            case EAST:
                int[] loc3 = {this.street-1, this.ave};
                // System.out.println("trying to go right from " + this.street + ":" + this.ave + " facing " + this.getOrientation() + " to " + Arrays.toString(loc3));
                this.orientation = Cardinality.SOUTH;
                if (inBounds(loc3)) {
                    this.street--;
                    this.gas -= GAS_USED_PER_BLOCK;
                }
                break;
            case WEST:
                int[] loc4 = {this.street+1, this.ave};
                // System.out.println("trying to go right from " + this.street + ":" + this.ave + " facing " + this.getOrientation() + " to " + Arrays.toString(loc4));
                this.orientation = Cardinality.NORTH;
                if (inBounds(loc4)) {
                    this.street++;
                    this.gas -= GAS_USED_PER_BLOCK;
                }
                break;
        }
    }

    /**
     * Handles the command,`straight on`.
     * Moves the moped one block straight ahead.
     * Consumes gas with each block moved, and doesn't move unless there is sufficient gas, as according to the instructions.
     * This method must not print anything.
     */
    public void goStraight() {
        // System.out.println(String.format("Going straight with %d gas from %s:%s facing %s", this.getGasLevel(), this.street, this.ave, this.getOrientation()));
        if (this.getGasLevel() < 10) return; // don't move without gas
        switch (this.orientation) {
            case NORTH:
                int[] loc1 = {this.street+1, this.ave};
                if (inBounds(loc1)) {
                    this.street++;
                    this.gas -= GAS_USED_PER_BLOCK;
                }
                break;
            case SOUTH:
                int[] loc2 = {this.street-1, this.ave};
                if (inBounds(loc2)) {
                    this.street--;
                    this.gas -= GAS_USED_PER_BLOCK;                  
                }
                break;
            case EAST:
                int[] loc3 = {this.street, this.ave-1};
                if (inBounds(loc3)) {
                    this.ave--;
                    this.gas -= GAS_USED_PER_BLOCK;
                }
                break;
            case WEST:
                int[] loc4 = {this.street, this.ave+1};
                if (inBounds(loc4)) {
                    this.ave++;
                    this.gas -= GAS_USED_PER_BLOCK;
                }
                break;
        }
    }

    /**
     * Handles the command,`back up`.
     * Moves the moped one block backwards, but does not change the cardinal direction the moped is facing.
     * Consumes gas with each block moved, and doesn't move unless there is sufficient gas, as according to the instructions.
     * This method must not print anything.
     */
    public void goBackwards() {
        // System.out.println(String.format("Going backwards with %d gas from %s:%s facing %s", this.getGasLevel(), this.street, this.ave, this.getOrientation()));
        if (this.getGasLevel() < 10) return; // don't move without gas
        switch (this.orientation) {
            case NORTH:
                int[] loc4 = {this.street-1, this.ave};
                // System.out.println("trying to go backwards from " + this.street + ":" + this.ave + " facing " + this.getOrientation() + " to " + Arrays.toString(loc4));
                if (inBounds(loc4)) {
                    this.street--;
                    this.gas -= GAS_USED_PER_BLOCK;
                }
                break;
            case SOUTH:
                int[] loc3 = {this.street+1, this.ave};
                // System.out.println("trying to go backwards from " + this.street + ":" + this.ave + " facing " + this.getOrientation() + " to " + Arrays.toString(loc3));
                if (inBounds(loc3)) {
                    this.street++;
                    this.gas -= GAS_USED_PER_BLOCK;
                }
                break;
            case EAST:
                int[] loc1 = {this.street, this.ave+1};
                // System.out.println("trying to go backwards from " + this.street + ":" + this.ave + " facing " + this.getOrientation() + " to " + Arrays.toString(loc1));
                if (inBounds(loc1)) {
                    this.ave++;
                    this.gas -= GAS_USED_PER_BLOCK;
                }
                break;
            case WEST:
                int[] loc2 = {this.street, this.ave-1};
                // System.out.println("trying to go backwards from " + this.street + ":" + this.ave + " facing " + this.getOrientation() + " to " + Arrays.toString(loc2));
                if (inBounds(loc2)) {
                    this.ave--;
                    this.gas -= GAS_USED_PER_BLOCK;
                }
                break;
        }
    }

    /**
     * Handles the command,`how we doin'?`.
     * This method must not print anything.
     * @return The current gas level, as an integer from 0 to 100.
     */
    public int getGasLevel() {
        // return 100; // replace this with your own int, according to the instructions
        return this.gas;
    }

    /**
     * Prints the current gas level, by default exactly following the format:
     *      The gas tank is currently 85% full.
     *
     * If the moped is out of gas, this method should print exactly following format:
     *      We have run out of gas.  Bye bye!
     */
    public void printGasLevel() {
        String msg = "The gas tank is currently " + this.gas + "% full.";
        if (this.gas <= 0) msg = "We have run out of gas.  Bye bye!";
        System.out.println(msg);
    }

    /**
     * Handles the command, `fill it up`.
     * This method must not print anything.
     * Fills the gas level to the maximum.
     */
    public void fillGas() {
        this.gas = 100;
    }

    /**
     * Handles the command, `park`.
     * This causes the program to quit.
     */
    public void park() {
        System.out.println("We have parked.");
        // quit the program
        System.exit(0);
    }

    /**
     * Handles the command, `go to Xi'an Famous Foods`
     * Causes the moped to self-drive, block-by-block, to 8th Ave. and 15th St.
     * Consumes gas with each block, and doesn't move unless there is sufficient gas, as according to the instructions.
     */
    public void goToXianFamousFoods() {

    }

    /**
     * Generates a string, containing a list of all the user commands that the program understands.
     * @return String containing commands that the user can type to control the moped.
     */
    public String getHelp() {
        return "help!"; // replace this with your own String, according to the instructions
    }

    /**
     * Sets the current location of the moped.
     * @param location an int array containing the new location at which to place the moped, in the order {street, avenue}.
     */
    public void setLocation(int[] location) {
        this.street = location[0];
        this.ave = location[1];
    }
    /**
     * Gets the current location of the moped.
     * @return The current location of the moped, as an int array in the order {street, avenue}.
     */
    public int[] getLocation() {
        // int[] location = {10, 5}; // an example array at 10th st and 5th Ave.
        // return location;
        int[] location = {this.street, this.ave};
        return location;
    }

}
