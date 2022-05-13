package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;

public class SearchableMaze  implements ISearchable {
    /**
     * Constructor:
     * initializes 2D arraylist
     * adds relevent Positions to the frame arraylist
     * creates start and goal positions
     *
     * @param columns
     * @param rows
     */
    Maze maze;
    public SearchableMaze(Maze maze) {
        this.maze = maze;

    }

    public Maze getMaze() {
        return maze;
    }

    @Override
    public AState GetStartState() {
        Position startP = maze.getStartPosition();
        AState state = new MazeState(startP.getColumnIndex(), startP.getRowIndex());
        return state;
    }

    @Override
    public AState GetGoalState() {
        Position goalP = maze.getGoalPosition();
        AState state = new MazeState(goalP.getColumnIndex(), goalP.getRowIndex());
        return state;
    }

    @Override
    public ArrayList<AState> GetAllPossibleStates(AState state) {
        ArrayList<AState> pStates = new ArrayList<AState>();
//        int firstSpaceIndex=state.getState().indexOf(" ");
//        int seconedSpaceIndex = state.getState().indexOf(" ",firstSpaceIndex +1);
//        //int thirdSpaceIndex = state.getState().indexOf(" ", seconedSpaceIndex+1);
//        String RowsI = state.getState().substring(firstSpaceIndex+1,seconedSpaceIndex);
//        String ColI = state.getState().substring(seconedSpaceIndex+1);
        MazeState state_maze = (MazeState)state;
        int rowIndex = state_maze.getRow();
        int colIndex = state_maze.getCol();
        //Position curr = new Position(colIndex, rowIndex);
        //1
        if (rowIndex-1 >= 0 && maze.getValue(colIndex, rowIndex-1) == 0){
            AState twelve = new MazeState(colIndex, rowIndex-1);
            twelve.setCost(10);
            pStates.add(twelve);
        }
        ////2
        if (rowIndex-1 >=0 && colIndex+1 < maze.getColumns() && maze.getValue(colIndex +1 , rowIndex-1) == 0){
            AState oneAndHalf = new MazeState(colIndex+1, rowIndex-1);
            oneAndHalf.setCost(15);
            pStates.add(oneAndHalf);
        }
        //3
        if (colIndex+1 < maze.getColumns() && maze.getValue(colIndex +1 , rowIndex) == 0){
            AState three = new MazeState(colIndex+1, rowIndex);
            three.setCost(10);
            pStates.add(three);
        }
        //4
        if (colIndex+1 < maze.getColumns() && rowIndex+1 < maze.getRows() && maze.getValue(colIndex +1 , rowIndex+1) == 0){
            AState fourAndHalf = new MazeState(colIndex, rowIndex+1);
            fourAndHalf.setCost(15);
            pStates.add(fourAndHalf);
        }
        //5
        if (rowIndex+1 < maze.getRows() && maze.getValue(colIndex  , rowIndex +1 ) == 0){
            AState six = new MazeState(colIndex, rowIndex+1);
            six.setCost(10);
            pStates.add(six);
        }
       //6
        if (colIndex-1 >=0 && rowIndex+1 < maze.getRows() && maze.getValue(colIndex -1 , rowIndex+1) == 0){
            AState sevenAndHalf = new MazeState(colIndex-1, rowIndex+1);
            sevenAndHalf.setCost(15);
            pStates.add(sevenAndHalf);
        }

        //7
        if (colIndex -1 >=0 && maze.getValue(colIndex -1 , rowIndex) == 0){
            AState nine = new MazeState(colIndex-1, rowIndex);
            nine.setCost(10);
            pStates.add(nine);
        }

        ////8
        if (colIndex-1 >= 0 && rowIndex-1>=0 && maze.getValue(colIndex -1 , rowIndex-1) == 0){
            AState tenAndHalf = new MazeState(colIndex-1, rowIndex-1);
            tenAndHalf.setCost(15);
            pStates.add(tenAndHalf);
        }

        return pStates;
    }
    /*
     returns a string of the state we crate
     */
//    private String stateString(int col, int row){
//        return  " " + row + " " + col;
//    }
}
