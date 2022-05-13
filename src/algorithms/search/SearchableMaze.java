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

    @Override
    public AState GetStartState() {
        Position startP = maze.getStartPosition();
        String State = stateString(startP.getRowIndex(),startP.getColumnIndex());
        AState state = new MazeState(State);
        return state;
    }

    @Override
    public AState GetGoalState() {
        Position goalP = maze.getGoalPosition();
        String State = stateString(goalP.getRowIndex(),goalP.getColumnIndex());
        AState state = new MazeState(State);
        return state;
    }

    @Override
    public ArrayList<AState> GetAllPossibleStates(AState state) {
        ArrayList<AState> pStates = new ArrayList<AState>();
        int firstSpaceIndex=state.getState().indexOf(" ");
        int seconedSpaceIndex = state.getState().indexOf(" ",firstSpaceIndex +1);
        int thirdSpaceIndex = state.getState().indexOf(" ", seconedSpaceIndex+1);
        String RowsI = state.getState().substring(firstSpaceIndex+1,seconedSpaceIndex);
        String ColI = state.getState().substring(thirdSpaceIndex+1);
        int rowIndex = Integer.parseInt(RowsI);
        int colIndex = Integer.parseInt(ColI);
        Position curr = new Position(colIndex, rowIndex);
        if (rowIndex-1 >= 0 && maze.getValue(colIndex, rowIndex-1) == 0){
            String s1 = stateString(colIndex,rowIndex -1);
            AState twelve = new MazeState(s1);
            twelve.setCost(10);
            pStates.add(twelve);
        }
        if (rowIndex-1 >=0 && colIndex+1 < maze.getColumns() && maze.getValue(colIndex +1 , rowIndex-1) == 0){
            String s1 = stateString(colIndex +1,rowIndex -1);
            AState oneAndHalf = new MazeState(s1);
            oneAndHalf.setCost(15);
            pStates.add(oneAndHalf);
        }
        if (colIndex+1 < maze.getColumns() && maze.getValue(colIndex +1 , rowIndex) == 0){
            String s1 = stateString(colIndex +1,rowIndex );
            AState three = new MazeState(s1);
            three.setCost(10);
            pStates.add(three);
        }
        if (colIndex+1 < maze.getColumns() && rowIndex+1 < maze.getRows() && maze.getValue(colIndex +1 , rowIndex+1) == 0){
            String s1 = stateString(colIndex +1,rowIndex +1);
            AState fourAndHalf = new MazeState(s1);
            fourAndHalf.setCost(15);
            pStates.add(fourAndHalf);
        }
        if (rowIndex+1 < maze.getRows() && maze.getValue(colIndex  , rowIndex +1 ) == 0){
            String s1 = stateString(colIndex ,rowIndex+1 );
            AState six = new MazeState(s1);
            six.setCost(10);
            pStates.add(six);
        }
        if (colIndex-1 >=0 && rowIndex+1 < maze.getRows() && maze.getValue(colIndex -1 , rowIndex+1) == 0){
            String s1 = stateString(colIndex -1,rowIndex +1);
            AState sevenAndHalf = new MazeState(s1);
            sevenAndHalf.setCost(15);
            pStates.add(sevenAndHalf);
        }
        if (colIndex -1 >=0 && maze.getValue(colIndex -1 , rowIndex) == 0){
            String s1 = stateString(colIndex-1 ,rowIndex );
            AState nine = new MazeState(s1);
            nine.setCost(10);
            pStates.add(nine);
        }
        if (colIndex-1 >= 0 && rowIndex-1>=0 && maze.getValue(colIndex -1 , rowIndex-1) == 0){
            String s1 = stateString(colIndex -1,rowIndex -1);
            AState tenAndHalf = new MazeState(s1);
            tenAndHalf.setCost(15);
            pStates.add(tenAndHalf);
        }

        return pStates;
    }
    /*
     returns a string of the state we crate
     */
    private String stateString(int row, int col){
        return  "RowIndex: " + row + " ColIndex: " + col;
    }
}
