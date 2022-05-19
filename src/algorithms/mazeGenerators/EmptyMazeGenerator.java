package algorithms.mazeGenerators;
import java.util.ArrayList;
import java.util.Collections;

/**
 * EmptyMazeGenerator extends the AMazeGenerator class. Creates mazes that include
 * all zeros.
 */
public class EmptyMazeGenerator extends AMazeGenerator {
    /**
     *
     * @param columns - number of columns in the maze
     * @param rows - number of rows in the maze
     * @return maze with all zeros
     */
    @Override
    public Maze generate(int columns, int rows) {
        Maze Empty = new Maze(columns,rows);
        for (int i=0; i<rows; i++){
            for (int j =0; j<columns; j++){
                Empty.setPosition(j,i,0);
            }
        }
        return Empty;
    }

    public EmptyMazeGenerator() {}
}
