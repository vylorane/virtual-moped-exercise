package edu.nyu.cs;

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

    // start off at 10th st and 5th ave
    private int street = 10;
    private int ave = 5;
    private Cardinality orientation = Cardinality.SOUTH;    
    private int gas = 100;

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
        else if (street==74 && ave==1) ad = "Memorial Sloan Kettering";
        else if (street==56 && ave==3) ad = "Tina's Cuban Cuisine";
        else if (street==12 && ave==4) ad = "The Strand";
        return ad;
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
    public void printCurrentLocation() {
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
     * Consumes gas with each block, according to the instructions.
     * This method must not print anything.
     */
    public void goLeft() {
        switch (this.orientation) {
            case NORTH:
                this.orientation = Cardinality.WEST;
                this.ave++;
                break;
            case SOUTH:
                this.orientation = Cardinality.EAST;
                this.ave--;
                break;
            case EAST:
                this.orientation = Cardinality.NORTH;
                this.street++;
                break;
            case WEST:
                this.orientation = Cardinality.SOUTH;
                this.street--;
                break;
        }
    }

    /**
     * Handles the command, `go right`.
     * Moves the moped one block to the right, and causes the moped to face the appropriate new cardinal direction.
     * Consumes gas with each block, according to the instructions.
     * This method must not print anything.
     */
    public void goRight() {
        switch (this.orientation) {
            case NORTH:
                this.orientation = Cardinality.EAST;
                this.ave--;
                break;
            case SOUTH:
                this.orientation = Cardinality.WEST;
                this.ave++;
                break;
            case EAST:
                this.orientation = Cardinality.NORTH;
                this.street--;
                break;
            case WEST:
                this.orientation = Cardinality.SOUTH;
                this.street++;
                break;
        }
    }

    /**
     * Handles the command,`straight on`.
     * Moves the moped one block straight ahead.
     * Consumes gas with each block, according to the instructions.
     * This method must not print anything.
     */
    public void goStraight() {
        switch (this.orientation) {
            case NORTH:
                this.street++;
                break;
            case SOUTH:
                this.street--;
                break;
            case EAST:
                this.ave--;
                break;
            case WEST:
                this.ave++;
                break;
        }
    }

    /**
     * Handles the command,`back up`.
     * Moves the moped one block backwards, but does not change the cardinal direction the moped is facing.
     * Consumes gas with each block, according to the instructions.
     * This method must not print anything.
     */
    public void goBackwards() {
        switch (this.orientation) {
            case NORTH:
                this.street--;
                break;
            case SOUTH:
                this.street++;
                break;
            case EAST:
                this.ave++;
                break;
            case WEST:
                this.ave--;
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
     * Handles the command, `fill 'er up`.
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
     * Consumes gas with each block, according to the instructions.
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
