package algorithms.search;

public class MazeState extends AState{
    private int row;
    private int col;

    public MazeState(int col, int row) {
        this.State = null;
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
    public AState getCopy() {
        AState copy = new MazeState(this.getCol(), this.getRow());
        return copy;
    }

    @Override
    public boolean equals(AState other) {
        if (this.getClass() == other.getClass()) {
            MazeState othermaze = (MazeState) other;
            if (othermaze.getRow() == this.row && othermaze.getCol() == this.col) {
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
