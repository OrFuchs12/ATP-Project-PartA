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
    private Maze maze;
    private AState startState;
    private AState goalState;
    private boolean[][] visited;

    public SearchableMaze(Maze maze) {
        this.maze = maze;
        visited = new boolean[maze.getRows()][maze.getColumns()];
        for (int i =0; i<maze.getRows(); i++) {
            for (int j=0; j<maze.getColumns(); j++) {
                visited[i][j]= false;}}
        Position startP = maze.getStartPosition();
        startState = new MazeState(startP.getColumnIndex(), startP.getRowIndex());
        Position goalP = maze.getGoalPosition();
        goalState = new MazeState(goalP.getColumnIndex(), goalP.getRowIndex());
        int rows= getData(startState).get(0);
        int col = getData(startState).get(1);
        visited[rows][col] = true;}

    private ArrayList<Integer> getData(AState state) {
        ArrayList<Integer> data = new ArrayList<Integer>(2);
        int firstSpaceIndex=  state.getState().indexOf(" ");
        int secondSpaceIndex = state.getState().indexOf(" ",firstSpaceIndex +1);
        int thirdSpaceIndex = state.getState().indexOf(" ", secondSpaceIndex+1);
        String RowsI = state.getState().substring(firstSpaceIndex+1,secondSpaceIndex);
        String ColI = state.getState().substring(thirdSpaceIndex+1);
        data.add(Integer.parseInt(RowsI));
        data.add(Integer.parseInt(ColI));
        return data;}

    public Maze getMaze() {
        return maze;
    }

    @Override
    public AState GetStartState() {return startState;}

    @Override
    public AState GetGoalState() {return goalState;}

    public boolean[][] getVisited() {
        return visited;
    }

    @Override
    public ArrayList<AState> GetAllPossibleStates(AState state) {
        ArrayList<AState> pStates = new ArrayList<AState>();
        MazeState state_maze = (MazeState)state;
        int rowIndex = state_maze.getRow();
        int colIndex = state_maze.getCol();
        //1
        if (rowIndex-1 >= 0 && maze.getValue(colIndex, rowIndex-1) == 0){
            AState twelve = new MazeState(colIndex, rowIndex-1);
            if (!visited[rowIndex-1][colIndex]){
                twelve.setCost(10);
                pStates.add(twelve);}}
        ////2
        if (rowIndex-1 >=0 && colIndex+1 < maze.getColumns() && maze.getValue(colIndex +1 , rowIndex-1) == 0 &&
                (maze.getValue(colIndex + 1, rowIndex) == 0 || maze.getValue(colIndex, rowIndex - 1) == 0)){
            AState oneAndHalf = new MazeState(colIndex+1, rowIndex-1);
            if (!visited[rowIndex-1][colIndex+1]) {{
                    oneAndHalf.setCost(15);
                    pStates.add(oneAndHalf);}}}
        //3
        if (colIndex+1 < maze.getColumns() && maze.getValue(colIndex +1 , rowIndex) == 0){
            AState three = new MazeState(colIndex+1, rowIndex);
            if (!visited[rowIndex][colIndex+1]){
                three.setCost(10);
                pStates.add(three);}}
        //4
        if (colIndex+1 < maze.getColumns() && rowIndex+1 < maze.getRows() && maze.getValue(colIndex +1 , rowIndex+1) == 0 &&
                (maze.getValue(colIndex + 1, rowIndex) == 0 || maze.getValue(colIndex, rowIndex + 1) == 0)){
            AState fourAndHalf = new MazeState(colIndex+1, rowIndex+1);
            if(!visited[rowIndex+1][colIndex+1]) {{
                    fourAndHalf.setCost(15);
                    pStates.add(fourAndHalf);}}}
        //5
        if (rowIndex+1 < maze.getRows() && maze.getValue(colIndex  , rowIndex +1 ) == 0){
            AState six = new MazeState(colIndex, rowIndex+1);
            if(!visited[rowIndex+1][colIndex]){
            six.setCost(10);
            pStates.add(six);}}
       //6
        if (colIndex-1 >=0 && rowIndex+1 < maze.getRows() && maze.getValue(colIndex -1 , rowIndex+1) == 0 &&
                (maze.getValue(colIndex , rowIndex+1) == 0 || maze.getValue(colIndex-1, rowIndex ) == 0)){
            AState sevenAndHalf = new MazeState(colIndex-1, rowIndex+1);
            if(!visited[rowIndex+1][colIndex-1]) {{
                    sevenAndHalf.setCost(15);
                    pStates.add(sevenAndHalf);}}}
        //7
        if (colIndex -1 >=0 && maze.getValue(colIndex -1 , rowIndex) == 0){
            AState nine = new MazeState(colIndex-1, rowIndex);
            if (!visited[rowIndex][colIndex-1]) {
            nine.setCost(10);
            pStates.add(nine);}}
        ////8
        if (colIndex-1 >= 0 && rowIndex-1>=0 && maze.getValue(colIndex -1 , rowIndex-1) == 0 &&
                (maze.getValue(colIndex - 1, rowIndex) == 0 || maze.getValue(colIndex, rowIndex - 1) == 0) ){
            AState tenAndHalf = new MazeState(colIndex-1, rowIndex-1);
            if(!visited[rowIndex-1][colIndex-1]) {{
                    tenAndHalf.setCost(15);
                    pStates.add(tenAndHalf);}}}
        return pStates;}

    public Solution makeSol(AState start) {
        Solution sol = new Solution();
        while (!start.equals(this.GetStartState())){
            sol.addToSolution(start);
            start = start.getCameFrom();}
        sol.addToSolution(this.GetStartState());
        return sol;}

    public boolean isVisited(AState state) {
        int rows = getData(state).get(0);
        int col = getData(state).get(1);
        return visited[rows][col];}

    public void addVisited(AState state) {
        int rows = getData(state).get(0);
        int col = getData(state).get(1);
        visited[rows][col] = true;}

    public void cleanVisited(){
        for (int i=0; i< maze.getRows(); i++) {
            for (int j=0; j< maze.getColumns(); j++) {
                visited[i][j]=false;}}}
}
