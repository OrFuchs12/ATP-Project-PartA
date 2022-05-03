package algorithms;
import java.util.ArrayList;
import java.util.List;
public class Maze {
    protected int columns;
    protected int rows;
    protected Position StartPosition;
    protected Position GoalPosition;
    protected ArrayList<ArrayList<Integer>> n_Maze;

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public Position getStartPosition() {
        return StartPosition;
    }

    public void setStartPosition(Position startPosition) {
        StartPosition = startPosition;
    }

    public Position getGoalPosition() {
        return GoalPosition;
    }

    public void setGoalPosition(Position goalPosition) {
        GoalPosition = goalPosition;
    }

    public Maze(int columns, int rows) {
        this.n_Maze = new ArrayList<>(rows);
        for (int i=0 ; i<rows; i++){
            n_Maze.add(i,new ArrayList<>(columns));

        }

        this.columns =columns;
        this.rows = rows;

    }
    public void setPosition(Position p, int value){

        n_Maze.get(p.getRowIndex()).set(p.getColumnIndex(), value);

    }

    @Override
    public String toString() {
        return " " + n_Maze + "start is in " + StartPosition +" Goal is in " + GoalPosition;
    }
}
