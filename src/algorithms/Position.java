package algorithms;

public class Position {
    private int ColumnIndex;
    private int RowIndex;
    private boolean visited;
    private Position father;





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
        father= null;
    }

    public void setFather(Position p)
    {
        father = p;
    }

    public Position getFather()
    {
        return father;
    }


    @Override
    public String toString() {
        return "{" +
                 + ColumnIndex +
                "," + RowIndex +
                '}';
    }
}
