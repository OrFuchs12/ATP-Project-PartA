package algorithms.search;
import java.util.ArrayList;

public class MazeState extends AState{
    private int row;
    private int col;

    public MazeState(int col, int row) {
        this.State = "Rows: " + row+ " Col: " + col;
        this.row= row;
        this.col =col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public boolean equals(Object o) {
        if (this.getClass() == o.getClass()) {
            MazeState other = (MazeState)o;
            if (other.getRow() == this.row && other.getCol() == this.col) {
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public String toString() {
        return "{" +
                 row +
                "," + col +
                '}';
    }
}
