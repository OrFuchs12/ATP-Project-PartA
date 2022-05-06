package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.Collections;

public class Position {
    private int ColumnIndex;
    private int RowIndex;
    private boolean visited;
    private ArrayList<Position> father;





    public int getColumnIndex() {
        return ColumnIndex;
    }
    public int getRowIndex() {
        return RowIndex;
    }
    public void Go_Left(){
            ColumnIndex -= 1;

    }
    public void Go_Right(){
        ColumnIndex += 1;
    }
    public void Go_Up(){
        RowIndex += 1;
    }
    public void Go_Down(){
        RowIndex -= 1;
    }

    public Position(int columnIndex, int rowIndex) {
        ColumnIndex = columnIndex;
        RowIndex = rowIndex;
        visited= false;
        father= new ArrayList<Position>();
    }

    public void setFather(Position p)
    {
        father.add(p);
    }

    public ArrayList<Position> getFather()
    {
        return father;
    }

    public void MixFather() {
        Collections.shuffle(father);
    }

    @Override
    public String toString() {
        return "{" +
                 + RowIndex +
                "," + ColumnIndex +
                '}';
    }
}
