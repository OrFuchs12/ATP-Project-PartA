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
        String State = stateString(startP.getColumnIndex(),startP.getRowIndex());
        AState state = new MazeState(State);
        return state;
    }

    @Override
    public AState GetGoalState() {
        Position goalP = maze.getGoalPosition();
        String State = stateString(goalP.getColumnIndex(),goalP.getRowIndex());
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
        //1
        if (rowIndex-1 >= 0 && maze.getValue(colIndex, rowIndex-1) == 0){
            String s1 = stateString(colIndex,rowIndex -1);
            AState twelve = new MazeState(s1);
            twelve.setCost(10);
            pStates.add(twelve);
        }
        ////2
        if (rowIndex-1 >=0 && colIndex+1 < maze.getColumns() && maze.getValue(colIndex +1 , rowIndex-1) == 0){
            String s1 = stateString(colIndex +1,rowIndex -1);
            AState oneAndHalf = new MazeState(s1);
            oneAndHalf.setCost(15);
            pStates.add(oneAndHalf);
        }
        //3
        if (colIndex+1 < maze.getColumns() && maze.getValue(colIndex +1 , rowIndex) == 0){
            String s1 = stateString(colIndex +1,rowIndex );
            AState three = new MazeState(s1);
            three.setCost(10);
            pStates.add(three);
        }
        //4
        if (colIndex+1 < maze.getColumns() && rowIndex+1 < maze.getRows() && maze.getValue(colIndex +1 , rowIndex+1) == 0){
            String s1 = stateString(colIndex +1,rowIndex +1);
            AState fourAndHalf = new MazeState(s1);
            fourAndHalf.setCost(15);
            pStates.add(fourAndHalf);
        }
        //5
        if (rowIndex+1 < maze.getRows() && maze.getValue(colIndex  , rowIndex +1 ) == 0){
            String s1 = stateString(colIndex ,rowIndex+1 );
            AState six = new MazeState(s1);
            six.setCost(10);
            pStates.add(six);
        }
       //6
        if (colIndex-1 >=0 && rowIndex+1 < maze.getRows() && maze.getValue(colIndex -1 , rowIndex+1) == 0){
            String s1 = stateString(colIndex -1,rowIndex +1);
            AState sevenAndHalf = new MazeState(s1);
            sevenAndHalf.setCost(15);
            pStates.add(sevenAndHalf);
        }

        //7
        if (colIndex -1 >=0 && maze.getValue(colIndex -1 , rowIndex) == 0){
            String s1 = stateString(colIndex-1 ,rowIndex );
            AState nine = new MazeState(s1);
            nine.setCost(10);
            pStates.add(nine);
        }

        ////8
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
    private String stateString(int col, int row){
        return  "RowIndex: " + row + " ColIndex: " + col;
    }
}
