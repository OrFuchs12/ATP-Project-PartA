package algorithms.mazeGenerators;
import java.util.ArrayList;
import java.util.Collections;


/**
 * Position describes a location with its column and row index, has an arraylist of Positions which includes
 * its fathers
 */
public class Position {
    private int ColumnIndex;
    private int RowIndex;
    private ArrayList<Position> father;

    /**
     *
     * @return column index
     */
    public int getColumnIndex() {
        return ColumnIndex;
    }

    /**
     *
     * @param o
     * @return true of the this position equals the object o, false otherwise
     */
    @Override
    public boolean equals(Object o)
    {
        if (o.getClass() == this.getClass())
        {
            Position other = (Position)o;
            if(other.getColumnIndex() == ColumnIndex && other.getRowIndex() == RowIndex){
                return true;}
            return false;
        }
        return false;
    }
    /**
     *
     * @return row index
     */
    public int getRowIndex() {
        return RowIndex;
    }

    /**
     * changes the position one place to the left
     */
    public void Go_Left(){
        ColumnIndex -= 1;
    }

    /**
     * changes the position one place to the right
     */
    public void Go_Right(){
        ColumnIndex += 1;
    }

    /**
     * changes the position one place to the top
     */
    public void Go_Up(){
        RowIndex += 1;
    }

    /**
     * changes the position one place to the bottom
     */
    public void Go_Down(){
        RowIndex -= 1;
    }

    /**
     * Constructor
     * @param columnIndex
     * @param rowIndex
     */
    public Position(int columnIndex, int rowIndex) {
        ColumnIndex = columnIndex;
        RowIndex = rowIndex;
        //visited= false;
        father= new ArrayList<Position>();
    }

    /**
     * adds a father p to the father list
     * @param p
     */
    public void setFather(Position p)
    {
        father.add(p);
    }

    /**
     *
     * @return the father list
     */
    public ArrayList<Position> getFather()
    {
        return father;
    }

    /**
     * mixes the list of fathers
     */

    /**
     *
     * @return the printed position - {row, column}
     */
    @Override
    public String toString() {
        return "{" +
                 + RowIndex +
                "," + ColumnIndex +
                '}';
    }
}
