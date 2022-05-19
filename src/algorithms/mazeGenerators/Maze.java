package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;


/**
 * Class Maze: includes number of columns, rows, StartPosition and  GoalPosition of type Postion, n_Maze which is a 2D
 * arraylist of Integers while 0 is a path and 1 is a wall, and Frame is an arraylist of Positions that describes all
 * of the Positions that are considered on the frame of the maze.
 */
public class Maze {
    protected int columns;
    protected int rows;
    protected Position StartPosition;
    protected Position GoalPosition;
    protected ArrayList<Position> Frame;
    protected int[][] n_Maze;

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }

    /**
     *
     * @param p of type Position
     * @return the value of this Position in the maze
     */
    public int getValue(Position p)
    {
        return n_Maze[p.getRowIndex()][p.getColumnIndex()];
    }

    /**
     *
     * @param columns
     * @param rows
     * @return the value of these {columns,rows} in the maze
     */

    public int getValue(int columns, int rows)
    {
        return n_Maze[rows][columns];
    }

    public Position getStartPosition() {
        return StartPosition;
    }

    /**
     * updates the start position according to the param only if it is part of the frame
     * @param startPosition
     */
    public void setStartPosition(Position startPosition) {
        if (Frame.contains(startPosition)){
            StartPosition = startPosition;
            setPosition(startPosition, 0);}}

    public Position getGoalPosition() {
        return GoalPosition;
    }

    /**
     * updates the goal position according to the param only if it is part of the frame
     * @param goalPosition
     */
    public void setGoalPosition(Position goalPosition) {
        if(Frame.contains(goalPosition)){
        GoalPosition = goalPosition;
        setPosition(GoalPosition,0);}}

    /**
     * Constructor:
     * initializes 2D array
     * adds relevent Positions to the frame arraylist
     * creates start and goal positions
     * changes invalid rows/columns to size 2
     * @param columns
     * @param rows
     */
    public Maze(int columns, int rows) {
        if ((columns ==1 && rows == 1) || (columns ==0 && rows ==0)) {
            rows =2;
            columns=2;}
        this.n_Maze = new int[rows][columns];
        this.columns =columns;
        this.rows = rows;
        int frame_size;
        if (rows == 1 || columns == 1 ||rows == 2 || columns==2 ) {
            frame_size =rows*columns;}
        else {
            frame_size = 2*rows + 2* columns - 4;}
        Frame = new ArrayList<>(frame_size) ;
        if (frame_size == rows*columns) {
            for (int i=0; i<rows; i++) {
                for (int j = 0; j < columns; j++) {
                    Frame.add(new Position(j, i));}}}
        else {
            for(int i=0; i<columns; i++){
                Frame.add(new Position(i,0));
                Frame.add(new Position(i,rows-1) );}
            for (int i = 1 ; i<rows-1; i++){
                Frame.add(new Position(0,i));
                Frame.add(new Position(columns-1, i));}
        }
        Random rand = new Random();
        int frame_rand1 = rand.nextInt(frame_size);
        int frame_rand2 = rand.nextInt(frame_size);
        while (frame_rand2 == frame_rand1) {
          frame_rand2 =rand.nextInt(frame_size);}
        StartPosition = Frame.get(frame_rand1);
        GoalPosition = Frame.get(frame_rand2);
    }

    /**
     *
     * @return arraylist of poistions on the frame
     */
    public ArrayList<Position> getFrame() {
        return Frame;
    }

    /**
     * sets a value to the position in the maze
     * @param p
     * @param value
     */
    public void setPosition(Position p, int value){
        n_Maze[p.getRowIndex()][p.getColumnIndex()]= value;
    }

    /**
     * sets a value according to row and column
     * @param col
     * @param row
     * @param value
     */
    public void setPosition(int col, int row, int value){
        n_Maze[row][col]= value;}

    /**
     * prints the n_Maze while the StartPoint is labeled S and GoalPoint E
     */
    public void print() {
        for (int i=0; i<rows; i++)
        {
            if (StartPosition.getRowIndex() ==i && GoalPosition.getRowIndex() ==i) {
                boolean firststart=false;
                int first=0;
                int second=0;
                if (StartPosition.getColumnIndex() < GoalPosition.getColumnIndex()){
                     first = StartPosition.getColumnIndex();
                     second = GoalPosition.getColumnIndex();
                     firststart=true; }
                else{
                     first = GoalPosition.getColumnIndex();
                     second = StartPosition.getColumnIndex();
                     firststart = false;}
                System.out.print("[");
                for (int j=0; j<first; j++){
                    System.out.print(getValue(j,i)+ ", ");}
                if (firststart==true) {
                    System.out.print("S");}
                else{
                    System.out.print("E");}
                for (int j=first+1; j<second; j++) {
                    System.out.print(", ");
                    System.out.print(getValue(j,i)+"");}
                if (firststart==true) {
                    System.out.print(", E");}
                else{
                    System.out.print(", S");}
                for (int j=second+1; j<columns;j++) {
                    System.out.print(", ");
                    System.out.print(getValue(j,i)+"");}
                System.out.println("]");}
            else if (StartPosition.getRowIndex() ==i) {
                System.out.print("[");
                for (int j=0; j<StartPosition.getColumnIndex(); j++) {
                    System.out.print(getValue(j,i)+ ", ");}
                System.out.print("S");
                for (int j=StartPosition.getColumnIndex()+1; j<columns;j++) {
                    System.out.print(", ");
                    System.out.print(getValue(j,i)+"");}
                System.out.println("]");}
            else if (GoalPosition.getRowIndex() ==i) {
                System.out.print("[");
                for (int j=0; j<GoalPosition.getColumnIndex(); j++) {
                    System.out.print(getValue(j,i)+ ", ");}
                System.out.print("E");
                for (int j=GoalPosition.getColumnIndex()+1; j<columns;j++) {
                    System.out.print(", ");
                    System.out.print(getValue(j,i)+"");}
                System.out.println("]");}
            else{
            System.out.println(Arrays.toString(n_Maze[i]));}
        }
    }
}
