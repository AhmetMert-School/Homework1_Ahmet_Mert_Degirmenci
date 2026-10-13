//------------------------------------------------------
// Homework 1
// Written by: Ahmet Mert Degirmenci ID: 23829642
// Date 10/13/2026
// ECE 373 Object-Oriented Software Design, Section 001 - Fall 2026
// The University of Arizona
//------------------------------------------------------
package edu.arizona.ece373.hw1;

/**
 * Prints a matrix whose cells are only {@code 0} or {@code 1}, values and all.
 *
 * <p>A 2x4 matrix whose two rows both hold {@code 0, 0, 1, 0} must render as</p>
 *
 * <pre>
 * +----+
 * |0010|
 * |0010|
 * +----+
 * </pre>
 *
 * <p>Only the row hook is overridden; the frame is inherited from
 * {@link MatrixOutlinePrinter}.</p>
 */
public class BoolMatrixPrinter extends MatrixOutlinePrinter {

    /** The character printed for a cell that holds {@code 0}. */
    public static final char DEAD = '0';

    /** The character printed for a cell that holds {@code 1}. */
    public static final char ALIVE = '1';

    /**
     * Renders one row as its cell values.
     *
     * @param matrix the matrix being printed, never {@code null}
     * @param row    the index of the row to render
     * @return one character per column, {@code '0'} or {@code '1'}
     * @throws IllegalArgumentException if any cell of the row holds a value other than 0 or 1
     */
    @Override
    protected String printRow(final Matrix matrix, final int row) {
        StringBuilder result = new StringBuilder();
        //Iterates through each column
        for (int column = 0; column < matrix.getColumns(); column++) {
            //value stores value from given row column
            int value = matrix.get(row, column);
            // checks for cases 0(dead), 1(alive), if neither throws illegalArgumentException
            switch (value) {
                case 1:
                    result.append(ALIVE);
                    break;
                case 0:
                    result.append(DEAD);
                    break;
                default:
                    throw new IllegalArgumentException("Values stored must be 0 or 1");
            }
        }
        return result.toString();
    }
}
