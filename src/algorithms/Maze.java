package algorithms;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class Maze {
    protected int columns;
    protected int rows;
    protected Position StartPosition;
    protected Position GoalPosition;
    protected ArrayList<Position> Frame;
    private ArrayList<ArrayList<Integer>> n_Maze;

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

    public int getValue(Position p)
    {
        return n_Maze.get(p.getRowIndex()).get(p.getColumnIndex());
    }

    public int getValue(int columns, int rows)
    {
        return n_Maze.get(rows).get(columns);
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
        this.n_Maze = new ArrayList<ArrayList<Integer>>(rows);
        for (int i=0 ; i<rows; i++){
            n_Maze.add(new ArrayList<Integer>(columns));


        }

        this.columns =columns;
        this.rows = rows;
                /*
        Randomize start position and goal position
         */
        int frame_size;
        if (rows == 1 || columns == 1 ||rows == 2 || columns==2 )
        {
            frame_size =rows*columns;
        }
        else {
            frame_size = 2*rows + 2* columns - 4;
        }

        Frame = new ArrayList<>(frame_size) ;
        if (frame_size == rows*columns)
        {
            for (int i=0; i<rows; i++) {
                for (int j = 0; j < columns; j++) {
                    Frame.add(new Position(j, i));
                }
            }
        }
        else {
            for(int i=0; i<columns; i++){
                Frame.add(new Position(i,0));
                Frame.add(new Position(i,rows-1) );
            }
            for (int i = 1 ; i<rows-1; i++){
                Frame.add(new Position(0,i));
                Frame.add(new Position(columns-1, i));
            }
        }

        Collections.shuffle(Frame);
        StartPosition = Frame.get(0);
        GoalPosition = Frame.get(1);


    }


    public ArrayList<Position> getFrame() {
        return Frame;
    }
    public void setPosition(Position p, int value){

        n_Maze.get(p.getRowIndex()).set(p.getColumnIndex(), value);

    }
    public void AddPosition(int columns , int rows, int value){
        n_Maze.get(rows).add(columns,value);
    }

    @Override
    public String toString() {
        String Maze = "";
        for(int i =0 ; i<rows; i++){
            Maze += (n_Maze.get(i) + "\n");
        }
        return Maze;
    }
}
