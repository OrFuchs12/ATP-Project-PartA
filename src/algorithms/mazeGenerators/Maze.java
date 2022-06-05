package algorithms.mazeGenerators;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.*;



/**
 * Class Maze: includes number of columns, rows, StartPosition and  GoalPosition of type Postion, n_Maze which is a 2D
 * arraylist of Integers while 0 is a path and 1 is a wall, and Frame is an arraylist of Positions that describes all
 * of the Positions that are considered on the frame of the maze.
 */
public class Maze implements Serializable {
    protected int columns;
    protected int rows;
    protected Position StartPosition;
    protected Position GoalPosition;
    protected ArrayList<Position> Frame;
    protected int[][] n_Maze;


    /**
     *
     * @return the number of columns int the maze
     */
    public int getColumns() {
        return columns;
    }

    /**
     *
     * @return the number of rows int the maze
     */
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

    /**
     *
     * @return the start position of the maze
     */
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
     * adds relevant Positions to the frame arraylist
     * creates start and goal positions
     * changes invalid rows/columns to size 2
     * @param columns
     * @param rows
     */
    public Maze(int columns, int rows) {
        if ((columns ==1 && rows == 1) || columns ==0 || rows ==0) {
            rows =2;
            columns=2;}
        this.n_Maze = new int[rows][columns];
        this.columns =columns;
        this.rows = rows;
        this.Frame = makeFrame(rows, columns);
        int frame_size = this.Frame.size();
        Random rand = new Random();
        int frame_rand1 = rand.nextInt(frame_size);
        int frame_rand2 = rand.nextInt(frame_size);
        while (frame_rand2 == frame_rand1) {
          frame_rand2 =rand.nextInt(frame_size);}
        StartPosition = Frame.get(frame_rand1);
        GoalPosition = Frame.get(frame_rand2);}


    public  Maze(byte[] byte_array) {
          rows = new BigInteger(Arrays.copyOfRange(byte_array, 0, 2)).intValue();
          columns = new BigInteger(Arrays.copyOfRange(byte_array, 2,4)).intValue();
          if ((columns ==1 && rows == 1) || columns ==0 || rows ==0) {
            rows =2;
            columns=2;}
          n_Maze = new int[rows][columns];
          int start_row = new BigInteger(Arrays.copyOfRange(byte_array, 4,6)).intValue();
          int start_col = new BigInteger(Arrays.copyOfRange(byte_array, 6,8)).intValue();
          StartPosition = new Position(start_col, start_row);
          int goal_row = new BigInteger(Arrays.copyOfRange(byte_array, 8,10)).intValue();
          int goal_col = new BigInteger(Arrays.copyOfRange(byte_array, 10,12)).intValue();
          GoalPosition = new Position(goal_col, goal_row);
          int counter =0;
          for (int i=0; i< rows; i++) {
              for (int j=0; j< columns; j++) {
                  n_Maze[i][j]= byte_array[12 + rows *counter + j];}
              counter++;
          }
        this.Frame = makeFrame(rows, columns);
        int frame_size = this.Frame.size();
        Random rand = new Random();
        int frame_rand1 = rand.nextInt(frame_size);
        int frame_rand2 = rand.nextInt(frame_size);
        while (frame_rand2 == frame_rand1) {
            frame_rand2 =rand.nextInt(frame_size);}
    }
    /**
     *
     * @param rows
     * @param columns
     * @return an arraylist of positions that include the frame of the maze
     */
    private ArrayList<Position> makeFrame(int rows, int columns)
    {
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
        return Frame;
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
        for (int i=0; i<rows; i++) {
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
    public byte[] toByteArray(){
        ByteArrayOutputStream bb= new ByteArrayOutputStream();
        BigInteger curr_rows = BigInteger.valueOf(rows);
        BigInteger curr_col = BigInteger.valueOf(columns);
        BigInteger startrow= BigInteger.valueOf(StartPosition.getRowIndex());
        BigInteger startcol = BigInteger.valueOf(StartPosition.getColumnIndex());
        BigInteger goalrow= BigInteger.valueOf(GoalPosition.getRowIndex());
        BigInteger goalcol = BigInteger.valueOf(GoalPosition.getColumnIndex());
        for (int i=0; i<rows; i++) {
            for (int j = 0; j < columns; j++) {
                    bb.write(n_Maze[i][j]);}}
        ByteBuffer buffer = ByteBuffer.allocate(bb.size() + 12);
        if (curr_rows.toByteArray().length ==1) {buffer.put((byte)0);}
        buffer.put(curr_rows.toByteArray());
        if (curr_col.toByteArray().length ==1) {buffer.put((byte)0);}
        buffer.put(curr_col.toByteArray());
        if (startrow.toByteArray().length ==1) {buffer.put((byte)0);}
        buffer.put(startrow.toByteArray());
        if (startcol.toByteArray().length ==1) {buffer.put((byte)0);}
        buffer.put(startcol.toByteArray());
        if (goalrow.toByteArray().length ==1) {buffer.put((byte)0);}
        buffer.put(goalrow.toByteArray());
        if (goalcol.toByteArray().length ==1) {buffer.put((byte)0);}
        buffer.put(goalcol.toByteArray());
        buffer.put(bb.toByteArray());
        return buffer.array();
    }

}
