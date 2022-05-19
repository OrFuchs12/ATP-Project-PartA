package algorithms.mazeGenerators;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;
import java.util.Random;


/**
 * Simple Maze Generator extends AMazeGenerator class and its generate function makes a Maze the includes
 * random picks for each position
 */
public class SimpleMazeGenerator extends AMazeGenerator {
    public SimpleMazeGenerator() {}

    /**
     * first each value is chosen randomly. Second, we "walk" from the start position to the end position
     * in a straight line and if we see a wall we "break" to ensure a solution
     * @param columns - number of columns in the maze
     * @param rows - number of rows in the maze
     * @return a Simple Maze
     */
    @Override
    public Maze generate(int columns, int rows) {
        Maze Simple = new Maze(columns, rows);
        if ((columns ==1 && rows == 1) || columns ==0 || rows ==0) {
            rows =2;
            columns=2;}
        Random rand = new Random();
        for(int i =0; i<rows; i++){
            for(int j = 0; j< columns; j++){
                int rand_int = rand.nextInt(2);
                Simple.setPosition(j,i,rand_int);
            }
        }
        Position curr = new Position(Simple.getStartPosition().getColumnIndex(), Simple.getStartPosition().getRowIndex());
        while (curr.getColumnIndex() != Simple.getGoalPosition().getColumnIndex()){
            Simple.setPosition(curr,0);
            if(curr.getColumnIndex() > Simple.getGoalPosition().getColumnIndex()){
                curr.Go_Left();
            }
            if(curr.getColumnIndex() < Simple.getGoalPosition().getColumnIndex()){
                curr.Go_Right();
            }
        }
        while (curr.getRowIndex() != Simple.getGoalPosition().getRowIndex()){
            Simple.setPosition(curr,0);
            if(curr.getRowIndex() > Simple.getGoalPosition().getRowIndex()){
                curr.Go_Down();
            }
            if(curr.getRowIndex() < Simple.getGoalPosition().getRowIndex()){
                curr.Go_Up();
            }
        }
        Simple.setPosition(Simple.getGoalPosition(),0);
        return Simple;
    }
}
