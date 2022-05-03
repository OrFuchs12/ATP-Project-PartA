package algorithms;
import java.util.ArrayList;
import java.util.Collections;
public class EmptyMazeGenerator extends AMazeGenerator{

    @Override
    public Maze generate(int columns, int rows) {
        Maze Empty = new Maze(columns,rows);
        for (int i=0; i<rows; i++){
            for (int j =0; j<columns; j++){
                Empty.setPosition(new Position(i,j),0);
            }
        }

        ArrayList<Position> Frame = new ArrayList<>(2*rows + 2* columns - 4) ;
        for(int i=0; i<columns; i++){
            Frame.add(new Position(i,0));
            Frame.add(new Position(i,rows-1) );
        }
        for (int i = 0 ; i<rows; i++){
            Frame.add(new Position(0,i));
            Frame.add(new Position(columns-1, i));
        }
        Collections.shuffle(Frame);
        Empty.setStartPosition(Frame.get(0));
        Empty.setGoalPosition(Frame.get(1));

        return Empty;
    }

    public EmptyMazeGenerator() {
    }
}
