package algorithms;

import java.util.ArrayList;
import java.util.Random;

public class MyMazeGenerator extends AMazeGenerator{
    @Override
    public Maze generate(int columns, int rows) {
        Maze MyMaze = new Maze(columns, rows);
        return null;

    }

    private ArrayList<Position> find_my_neighbors(Position p, Maze maze) {
        ArrayList<Position> neighbors = new ArrayList<>();
        if (p.getColumnIndex() + 2 <= maze.getColumns())
        {
            neighbors.add(new Position(p.getColumnIndex()+2, p.getRowIndex()));
        }
        if (p.getColumnIndex() -2  >= 0)
        {
            neighbors.add(new Position(p.getColumnIndex()-2, p.getRowIndex()));
        }
        if (p.getRowIndex() + 2 <= maze.getRows())
        {
            neighbors.add(new Position(p.getColumnIndex(), p.getRowIndex() +2));
        }
        if (p.getRowIndex() - 2 >= 0)
        {
            neighbors.add(new Position(p.getColumnIndex(), p.getRowIndex() -2));
        }
        return neighbors;
    }

    public MyMazeGenerator() {

    }
}
