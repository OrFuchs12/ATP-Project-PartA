package algorithms.search;
import java.util.ArrayList;


/**
 * describes a state of a MAZE with rows and columns, string, cost, and another father state
 */
public class MazeState extends AState{
    private int row;
    private int col;

    /**
     * constructor for a mazeState
     * @param col
     * @param row
     */
    public MazeState(int col, int row) {
        this.State = "Rows: " + row+ " Col: " + col;
        this.row= row;
        this.col =col;}

    /**
     *
     * @return the row index of the MazeState
     */
    public int getRow() {
        return row;
    }

    /**
     *
     * @return the column index of the MazeState
     */
    public int getCol() {
        return col;
    }

    /**
     * comparing maze states by row and column only!
     * @param o
     * @return boolean if the states have the same location
     */
    @Override
    public boolean equals(Object o) {
        if (this.getClass() == o.getClass()) {
            MazeState other = (MazeState)o;
            if (other.getRow() == this.row && other.getCol() == this.col) {
                return true;}
            return false;}
        return false;}


    /**
     *
     * @return the state in the form of {row,column}
     */
    @Override
    public String toString() {
        return "{" +
                 row +
                "," + col +
                '}';
    }
}
