//------------------------------------------------------
// Homework 1
// Written by: Ahmet Mert Degirmenci ID: 23829642
// Date 10/13/2026
// ECE 373 Object-Oriented Software Design, Section 001 - Fall 2026
// The University of Arizona
//------------------------------------------------------
package edu.arizona.ece373.hw1;

/**
 * A named pattern of live and dead cells that can be stamped onto a {@link GameOfLife} field.
 *
 * <p>Task 3 of the homework. A shape <em>is a</em> {@link Matrix} whose cells are only 0 or 1,
 * plus a name such as {@code "Beehive"}.</p>
 */
public class Shape extends Matrix {

    /**
     * Stores the name of the shape
     */
    private String name = "";

    /**
     * Creates a named shape from a pattern of zeroes and ones.
     *
     * @param name the name of the shape, must not be {@code null} or blank
     * @param data the pattern, must be a legal matrix body containing only 0 and 1
     * @throws IllegalArgumentException if the name is missing, if {@code data} is not a legal
     *                                  matrix, or if any cell is not 0 or 1
     */
    public Shape(String name, int[][] data) {
        super(data);
        //Check if the name is empty or null if not continue checking the matrix
        if(name != null && !name.isEmpty()){
            //check matrix's each cell if not 0 or 1 throw exception
            for(int row = 0; row < getRows(); row++){
                for(int column = 0; column < getColumns(); column++){
                    if(data[row][column] != 1 && data[row][column] != 0){
                        throw new IllegalArgumentException("data must be made of 0s and 1s");
                    }
                }
            }
            this.name = name;
        }
        //If string is empty or null throw exception
        else{
            throw new IllegalArgumentException("Name cannot be empty string");
        }
    }

    /**
     * Returns the name of this shape.
     *
     * @return the shape name
     */
    public String getName() {
        //returns the name.
        return this.name;
    }

    /**
     * Renames this shape.
     *
     * @param name the new name, must not be {@code null} or blank
     * @throws IllegalArgumentException if the name is {@code null} or blank
     */
    public void setName(String name) {
        //Checks for empty and null string
        if(name == null || name.isEmpty()){
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
        this.name = name;
    }

    /**
     * Returns the classic still-life "beehive", a 3x4 pattern that never changes.
     *
     * @return a new beehive shape
     */
    public static Shape beehive() {
        //creates beehive shape
        // 0110
        // 1001
        // 0110
        return new Shape("beehive",new int[][] {{0,1,1,0},{1,0,0,1},{0,1,1,0}});
    }

    /**
     * Returns the classic still-life "boat", a 3x3 pattern that never changes.
     *
     * @return a new boat shape
     */
    public static Shape boat() {
        //creates boat shape:
        // 110
        // 101
        // 010
        return new Shape("boat",new int[][] {{1,1,0},{1,0,1},{0,1,0}});
    }

    /**
     * Returns the horizontal phase of the oscillator "blinker", a 1x3 row of live cells.
     *
     * @return a new blinker shape
     */
    public static Shape blinker() {
        //Returns blinker shape:
        // 111
        return new Shape("blinker",new int[][] {{1,1,1}});
    }

    /**
     * Returns the classic spaceship "glider", a 3x3 pattern that travels one cell down and one
     * cell to the right every four generations.
     *
     * @return a new glider shape
     */
    public static Shape glider() {
        //Returns glider
        // 010
        // 001
        // 111
        return new Shape("glider",new int[][] {{0,1,0},{0,0,1},{1,1,1}});
    }

    /**
     * Returns the still-life "block", a 2x2 square of live cells.
     *
     * @return a new block shape
     */
    public static Shape block() {
        // Returns block:
        // 11
        // 11
        return new Shape("block",new int[][] {{1,1},{1,1}});
    }

    /**
     * Returns one phase of the period-two oscillator "toad", a 2x4 pattern.
     *
     * @return a new toad shape
     */
    public static Shape toad() {
        // Returns block:
        // 0111
        // 1110
        return new Shape("toad",new int[][] {{0,1,1,1},{1,1,1,0}});
    }

    /**
     * Showcase for Task 3: builds a beehive and a boat and prints both with a
     * {@link BoolMatrixPrinter}.
     *
     * @param args ignored
     */
    public static void main(String[] args) {
        System.out.println("***Welcome to the ECE 373 Homework 1 Shape driver.***");
        System.out.println("*****************************************************");
        // Create printer obj to print shapes
        BoolMatrixPrinter printer = new BoolMatrixPrinter();
        //create boat shape
        Shape boat = Shape.boat();
        // create beehive shape
        Shape beehive = Shape.beehive();
        //Print them to the console
        System.out.println(printer.print(boat));
        System.out.println(printer.print(beehive));

        System.out.println();
        System.out.println("************************************************************");
        System.out.println("************************ All Done!! ************************");
    }
}
