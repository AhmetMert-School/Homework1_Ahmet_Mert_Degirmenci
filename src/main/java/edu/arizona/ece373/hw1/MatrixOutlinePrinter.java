//------------------------------------------------------
// Homework 1
// Written by: Ahmet Mert Degirmenci ID: 23829642
// Date 10/13/2026
// ECE 373 Object-Oriented Software Design, Section 001 - Fall 2026
// The University of Arizona
//------------------------------------------------------
package edu.arizona.ece373.hw1;

/**
 * Prints the outline (the frame) of a matrix and leaves the inside blank.
 *
 * <p>A 2x4 matrix must render as</p>
 *
 * <pre>
 * +----+
 * |    |
 * |    |
 * +----+
 * </pre>
 *
 * <p>with a {@code '\n'} after every line, including the last one.</p>
 */
public class MatrixOutlinePrinter implements MatrixPrinter {

    /** The character drawn at the four corners of the frame. */
    public static final char CORNER = '+';

    /** The character drawn along the top and bottom edges of the frame. */
    public static final char HORIZONTAL = '-';

    /** The character drawn along the left and right edges of the frame. */
    public static final char VERTICAL = '|';

    /**
     * Renders {@code matrix} as a framed block of text.
     *
     * @param matrix the matrix to render, must not be {@code null}
     * @return the framed rendering of {@code matrix}
     * @throws IllegalArgumentException if {@code matrix} is {@code null}, or if
     *                                  {@link #printRow(Matrix, int)} rejects a row
     */
    @Override
    public String print(Matrix matrix) {
        // TODO: reject a null matrix, then build the frame with a StringBuilder:
        // TODO:   a border line, one line per row, a border line, each ending in '\n'.
        // TODO: the inside of each line must come from printRow(matrix, row) so that
        // TODO: BoolMatrixPrinter can change it by overriding that one method.
        //Reject null matrix
        if(matrix == null){
            throw new IllegalArgumentException("Matrix cannot be null");
        }
        //firstAndLastLine stores '+-----+\n' line for beginning and end of the print statement
        //middle stores middle section of the matrix to print
        //result is the completed form
        StringBuilder firstAndLastLine = new StringBuilder();
        StringBuilder middle = new StringBuilder();
        StringBuilder result = new StringBuilder();

        //Generates first and last line to print
        firstAndLastLine.append(CORNER);
        firstAndLastLine.append(CORNER);
        firstAndLastLine.append("\n");
        // Add horizontal lines at the number of columns
        for(int i = 0; i < matrix.getColumns(); i++){
            firstAndLastLine.insert(1,HORIZONTAL);
        }
        // Generates middle section of the printed matrix
        // for example 2x4 matrix will generate this outline:
        // |    |
        // |    |
        for(int i = 0; i < matrix.getRows(); i++){
            middle.append(VERTICAL);
            middle.append(printRow(matrix, i));
            middle.append(VERTICAL);
            middle.append("\n");
        }
        // combine first and last line with the middle section
        result.append(firstAndLastLine);
        result.append(middle);
        result.append(firstAndLastLine);
        return result.toString();
    }

    /**
     * Renders the inside of one row, without the surrounding {@link #VERTICAL} characters.
     *
     * <p>The returned string must be exactly {@code matrix.getColumns()} characters long,
     * otherwise the frame will not line up. Subclasses override this method.</p>
     *
     * @param matrix the matrix being printed, never {@code null}
     * @param row    the index of the row to render
     * @return the characters that belong between the two border characters of this line
     */
    protected String printRow(Matrix matrix, int row) {
        StringBuilder outputRow = new StringBuilder();
        for(int i = 0; i < matrix.getColumns(); i++){
            outputRow.append(" ");
        }
        return outputRow.toString();
    }
}
