package edu.nyu.cs;

import java.util.Arrays;

import javax.lang.model.util.ElementScanner6;

/**
 * A virtual moped, roaming the streets of New York.
 * The signatures of a few methods are given and must be completed and used as indicated.
 * Create as many additional properties or methods as you want, as long as the given methods behave as indicated in the instructions.
 * Follow good object-oriented design, especially the principles of abstraction (i.e. the black box metaphor) and encapsulation (i.e. methods and properties belonging to specific objects), as we have learned them.
 * The rest is up to you.
 */
public class Moped {
    private String facing;  // orientation
    private int[] location; 
    private int gasLevel;

    public Moped(){
        this.facing = "south";
        this.location = new int[] {10,5};
        this.gasLevel = 100;
        // Moped moped1 = new Moped("north", "red");
        
    }
    /**
     * Sets the orientation of the moped to a particular cardinal direction.
     * @param orientation A string representing which cardinal direction at which to set the orientation of the moped.  E.g. "north", "south", "east", or "west".
     */
    public void setOrientation(String orientation) {
        String mopedOri = orientation.toLowerCase();

        if (mopedOri.equals("north")){
            this.facing = "north";
        }else if(mopedOri.equals("south")){
            this.facing = "south";
        }else if(mopedOri.equals("east")){
            this.facing = "east";
        }else if(mopedOri.equals("west")){
            this.facing = "west";
        }



    }

    /**
     * @param streetAndAvenue
     * @return suffix
     */
    public String getSuffix(int streetAndAvenue){
        String suffix = " ";
        if (streetAndAvenue % 10 == 1 && streetAndAvenue != 11){
            suffix = "st";
        }else if (streetAndAvenue % 10 == 2 && streetAndAvenue != 12){
            suffix = "nd";
        }else if (streetAndAvenue % 10 == 3 && streetAndAvenue != 13){
            suffix = "rd";
        }else{
            suffix = "th";
        }

        return suffix;
    }
    /**
     * Returns the current orientation of the moped, as a lowercase String.
     * E.g. "north", "south", "east", or "west".
     * @return The current orientation of the moped, as a lowercase String.
     */
    public String getOrientation() {
        return this.facing; // placeholder only... delete this!    
    }

    /**
     * Prints the current location, by default exactly following the format:
     *      Now at 12th St. and 5th Ave, facing South.
     *
     * If the current location is associated with location-based advertising, this method should print exactly following format:
     *      Now at 12th St. and 4th Ave, facing West.  Did you know The Strand has 18 Miles of new, used and rare books, and has been in business since 1927?
     * 
     * Note that the suffixes for the numbers must be correct: i.e. the "st" in "1st", "nd" in "2nd", "rd" in "3rd", "th" in "4th", etc, must be correct.
     */
    public void printLocation() {
        String suffixStreet = getSuffix(this.location[0]);
        String suffixAvenue = getSuffix(this.location[1]);

        String currentLocation = "Now at " + this.location[0] + suffixStreet + " St. and " + this.location[1] + suffixAvenue + " Ave, facing " + this.facing + ".";

        if (this.location[0] == 79 && this.location[1] == 8 ){
            System.out.println("American Museum of Natural History: Unlimited Tickets, Early Access, Store Discounts and Much More!");
        }else if(this.location[0] == 74 && this.location[1] == 1){
            System.out.println("Memorial Sloan Kettering: Leading The Fight Against Cancer. Always focused on you.");
        }else if(this.location[0] == 56 && this.location[1] == 3){
            System.out.println("Tina's Cuban Cuisine: 10% off your first online order! ");
        }else if(this.location[0] == 12 && this.location[1] == 4){
            System.out.println("The Strand: Where it all began: Bound by a Family");
        }

    }

    /**
     * Handles the command, `go left`.
     * Moves the moped one block to the left, and causes the moped to face the appropriate new cardinal direction.
     * Consumes gas with each block moved, and doesn't move or turn unless there is sufficient gas, as according to the instructions.
     * If attempting to drive off the map, the moped will turn but not move a block.  Turns-only consume no gas.
     * This method must not print anything.
     */
    public void goLeft() {
        if(this.gasLevel >= 5 && this.location[0] <= 200 && this.location[1]<= 10){
            this.gasLevel-=5;
            if (this.facing.equals("east")){
                this.facing = "north";
                this.location[0]++;
            }else if(this.facing.equals("west")){
                this.facing = "south";
                this.location[0]--;
            }else if (this.facing.equals("south")){
                this.facing = "east";
                this.location[1]--;
            }else if(this.facing.equals("north")){
                this.facing = "west";
                this.location[1]++;
            }
        }else{
            System.out.println("Oops! Not enough gas is left. Refill.");
        } 

    }

    /**
     * Handles the command, `go right`.
     * Moves the moped one block to the right, and causes the moped to face the appropriate new cardinal direction.
     * Consumes gas with each block moved, and doesn't move or turn unless there is sufficient gas, as according to the instructions.
     * If attempting to drive off the map, the moped will turn but not move a block.  Turns-only consume no gas.
     * This method must not print anything.
     */
    public void goRight() {
        if(this.gasLevel >= 5 && this.location[0] <= 200 && this.location[1]<= 10){
            this.gasLevel-=5;
            if (this.facing.equals("east")){
                this.facing = "south";
                this.location[0]--;
            }else if(this.facing.equals("west")){
                this.facing = "north";
                this.location[0]++;
            }else if (this.facing.equals("south")){
                this.facing = "west";
                this.location[1]++;
            }else if(this.facing.equals("north")){
                this.facing = "east";
                this.location[1]--;
            }
        }else{
            System.out.println("Oops! Not enough gas is left. Refill.");
        } 

    }

    /**
     * Handles the command,`straight on`.
     * Moves the moped one block straight ahead.
     * Consumes gas with each block moved, and doesn't move unless there is sufficient gas, as according to the instructions.
     * This method must not print anything.
     */
    public void goStraight() {
        if(this.gasLevel >= 5 && this.location[0] <= 200 && this.location[1]<= 10){
            this.gasLevel-=5;
            if (this.facing.equals("east")){
                this.location[1]--;
            }else if(this.facing.equals("west")){
                this.location[1]++;
            }else if (this.facing.equals("south")){
                this.location[0]--;
            }else if(this.facing.equals("north")){
                this.location[0]++;
            }
        }else{
            System.out.println("Oops! Not enough gas is left. Refill.");
        } 

    }

    /**
     * Handles the command,`back up`.
     * Moves the moped one block backwards, but does not change the cardinal direction the moped is facing.
     * Consumes gas with each block moved, and doesn't move unless there is sufficient gas, as according to the instructions.
     * This method must not print anything.
     */
    public void goBackwards() {
        if(this.gasLevel >= 5 && this.location[0] <= 200 && this.location[1] <= 10){
            this.gasLevel-=5;
            if (this.facing.equals("east")){
                this.location[1]++;
            }else if(this.facing.equals("west")){
                this.location[1]--;
            }else if (this.facing.equals("south")){
                this.location[0]++;
            }else if(this.facing.equals("north")){
                this.location[0]--;
            }
        }else{
            System.out.println("Oops! Not enough gas is left. Refill.");
        } 

    }

    /**
     * Handles the command,`how we doin'?`.
     * This method must not print anything.
     * @return The current gas level, as an integer from 0 to 100.
     */
    public int getGasLevel() {
        return this.gasLevel; // placeholder only... delete this!
    }

    /**
     * Prints the current gas level, by default exactly following the format:
     *      The gas tank is currently 85% full.
     *
     * If the moped is out of gas, this method should print exactly following format:
     *      We have run out of gas.  Bye bye!
     */
    public void printGasLevel() {
        int gasLevel = this.getGasLevel();
        int percentGasLevel = (gasLevel / 100) * 100;

        if (this.gasLevel >= 5){
            System.out.println("The gas tank is " + percentGasLevel + "% full");
        }else{
            System.out.print("We have run out of gas. Bye bye!");
        }


    }

    /**
     * Handles the command, `fill it up`.
     * This method must not print anything.
     * Fills the gas level to the maximum.
     */
    public void fillGas() {
        this.gasLevel = 100;

    }

    /**
     * Handles the command, `park`.
     * This causes the program to quit.  
     * You can use System.exit(0); to cause a program to quit with status code 0, which indicates a normal graceful exit. 
     * (In case you were wondering, status code 1 represents quitting as a result of an error of some kind).
     */
    public void park() {
        System.exit(0);
    }

    /**
     * Handles the command, `go to Xi'an Famous Foods`
     * Causes the moped to self-drive, block-by-block, to 8th Ave. and 15th St.
     * Consumes gas with each block, and doesn't move unless there is sufficient gas, as according to the instructions.
     */
    public void goToXianFamousFoods() {
        int currentStreet = this.location[0];
        int currentAve = this.location[1];

    }

    

    /**
     * Generates a string, containing a list of all the user commands that the program understands.
     * @return String containing commands that the user can type to control the moped.
     */
    public String getHelp() {
        return ""; // placeholder only... delete this!        
    }

    /**
     * Sets the current location of the moped.
     * @param location an int array containing the new location at which to place the moped, in the order {street, avenue}.
     */
    public void setLocation(int[] location) {
        //this.location[0] = location[0];
        //this.location[1] = location[1];

        int newStr = location[0];
        int newAve = location[1];

        this.location[0] = newStr;
        this.location[1] = newAve;


    }

    /**
     * Gets the current location of the moped.
     * @return The current location of the moped, as an int array in the order {street, avenue}.
     */
    public int[] getLocation() {
        // the following two lines are placeholder... delete them and return this moped's correct coordinates.
        int[] location = {this.location[0], this.location[1]}; // an example array at 3rd st and 4th Ave.... placeholder only... delete this!
        return this.location;
    }


}

