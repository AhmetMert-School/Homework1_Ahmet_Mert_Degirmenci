//------------------------------------------------------
// Homework 1
// Written by: (your name and student ID)
// ECE 373 Object-Oriented Software Design, Section 001 - Fall 2026
// The University of Arizona
//------------------------------------------------------
package edu.arizona.ece373.hw1;

/**
 * A Conway's Game of Life field.
 *
 * <p>Task 4 of the homework. The field <em>is a</em> {@link Matrix} in which {@code 1} means the
 * cell is alive and {@code 0} means it is dead. Cells outside the field always count as dead, so
 * the field does not wrap around at the edges.</p>
 *
 * <p>The rules for one generation are:</p>
 * <ul>
 *   <li>a live cell with fewer than two live neighbours dies;</li>
 *   <li>a live cell with two or three live neighbours survives;</li>
 *   <li>a live cell with more than three live neighbours dies;</li>
 *   <li>a dead cell with exactly three live neighbours becomes alive.</li>
 * </ul>
 */
public class GameOfLife extends Matrix {

    /** Value of a cell that is alive. */
    public static final int ALIVE = 1;

    /** Value of a cell that is dead. */
    public static final int DEAD = 0;

    /**
     * Creates an empty game field of the given size; every cell starts dead.
     *
     * @param rows    the height of the field, must be at least 1
     * @param columns the width of the field, must be at least 1
     * @throws IllegalArgumentException if {@code rows} or {@code columns} is less than 1
     */
    public GameOfLife(int rows, int columns) {
        super(rows, columns);
    }

    /**
     * Stamps a shape onto the field with its top-left cell at the given coordinate.
     *
     * @param shape  the shape to place, must not be {@code null}
     * @param row    the field row that receives row 0 of the shape
     * @param column the field column that receives column 0 of the shape
     * @throws IllegalArgumentException if {@code shape} is {@code null}, if the coordinate is
     *                                  negative, or if the shape does not fit entirely on the field
     */
    public void addShape(Shape shape, int row, int column) {
        // Check for negative coordinates.
        // Then use length of shape plus coordinates to check if the shape is out of the bounds
        // Coordinates that are greater than bounds will also be checked at the same algorithm that checks shape out of bounds
        if(row < 0 || column < 0 || shape.getColumns() + column > this.getColumns() || shape.getRows() + row > this.getRows()){
            throw new IllegalArgumentException("Input cannot be negative or the shape is out of bounds");
        }
        //shapeRow and shapeColumn stores index of the shape's cell that will be copied to the field
        //Actual index in the field to be copied will be starting index + index of shape's cell
        for(int shapeRow = 0; shapeRow < shape.getRows(); shapeRow++){
            for(int shapeColumn = 0; shapeColumn < shape.getColumns(); shapeColumn++){
                this.set(shapeRow + row, shapeColumn + column, shape.get(shapeRow,shapeColumn));
            }
        }
    }

    /**
     * Advances the field by one generation.
     *
     * <p>Compute the whole next generation into a local two-dimensional array first, and only
     * then install it with the inherited {@link Matrix#setData(int[][])}.</p>
     */
    public void step() {
        // Array next stores copy of the field at the beginning then after for loops finishes
        // it will store next step of the game.
        int[][] next = getData();
        // Iterate through each cell and check their alive neighbours to determine their status ALIVE or DEAD.
        for(int row = 0; row < getRows(); row++){
            for(int column = 0; column < getColumns(); column++){
                //Find alive neighbours
                int numberOfAliveNeighbours = countLiveNeighbours(row,column);
                // Check for ranges:
                // < 2 will be dead cell
                // 2 or 3 Alive cell stays alive Dead cell becomes alive if neighbour number is 3
                // > 3 will be dead cell
                if(numberOfAliveNeighbours < 2 || numberOfAliveNeighbours > 3){
                    next[row][column] = DEAD;
                }
                // Check for dead Cell becoming alive case.
                else if(next[row][column] == DEAD && numberOfAliveNeighbours == 3){
                    next[row][column] = ALIVE;
                }
            }
        }
        setData(next);
    }

    /**
     * Counts how many of the eight cells around a coordinate are alive.
     *
     * @param row    the row of the cell whose neighbours are counted
     * @param column the column of the cell whose neighbours are counted
     * @return a number between 0 and 8
     */
    public int countLiveNeighbours(int row, int column) {
        // TODO: look at the eight surrounding coordinates and count the live ones,
        // TODO: skipping coordinates that fall outside the field.
        //Checks the beginning index of the neighbours if it is out of bounds assigns the closest value


        int numberOfAliveNeighbours = 0;
        //for loop continues either row + 1 is reached or rightmost, bottom bound is reached
        // to not include the outside bound indexes
        for(int rowToCheck = (row-1 < 0) ? 0: row-1; rowToCheck <= row + 1 && rowToCheck < getRows(); rowToCheck++){
            for(int columnToCheck = (column-1 < 0) ? 0: column-1; columnToCheck <= column + 1 && columnToCheck < getColumns(); columnToCheck++){
                //ignore the cell itself
                if(columnToCheck != column || rowToCheck != row){
                    //check if the neighbour is zero or one if one, increment alive neighbour count
                    if(get(rowToCheck,columnToCheck) == ALIVE){
                        numberOfAliveNeighbours++;
                    }
                }
            }
        }
        return numberOfAliveNeighbours;
    }

    /**
     * Showcase for Task 4: builds a field holding a blinker and a glider and steps it a fixed
     * number of generations.
     *
     * @param args optional; args[0] is the number of generations to run, default 20
     * @throws InterruptedException if the animation is interrupted while sleeping between frames
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO: create a game field, add at least a Blinker and a Glider, then run a fixed
        // TODO: number of generations - read it from args[0] if present, else use 20:
        // TODO:   print the field with a BoolMatrixPrinter, call step(), and Thread.sleep a little.
    }
}
