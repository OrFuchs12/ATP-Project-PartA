package algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MyMazeGenerator extends AMazeGenerator{
    @Override
    public Maze generate(int columns, int rows) {
        ArrayList<Position> all_neighbors = new ArrayList<Position>();
        Maze MyMaze = new Maze(columns, rows);
        for (int i=0; i<rows; i++){
            for (int j =0; j<columns; j++){
                MyMaze.n_Maze.get(i).add(j,1);
            }
        }
        if (rows == 2 && columns == 2)
        {
            if (MyMaze.getStartPosition().getColumnIndex() != MyMaze.getGoalPosition().getColumnIndex() &&
            MyMaze.getStartPosition().getRowIndex() != MyMaze.getGoalPosition().getRowIndex())
            {
                Random rand = new Random();
                int rand_point = rand.nextInt(2);
                MyMaze.setPosition(MyMaze.getFrame().get(rand_point +3), 0);

            }
        }
        else {
            Position curr = new Position(MyMaze.getStartPosition().getColumnIndex(), MyMaze.getStartPosition().getRowIndex());
            do {
                all_neighbors.addAll(find_my_neighbors(curr, MyMaze));
                Collections.shuffle(all_neighbors);
                curr = all_neighbors.remove(0);
                MyMaze.setPosition(curr, 0);
                MyMaze.setPosition(curr.getFather(), 0);
            }
            while (!all_neighbors.isEmpty());
            if (MyMaze.getValue(MyMaze.getGoalPosition()) == 1) {
                ArrayList<Position> frame = MyMaze.getFrame();
                for (int i = 2; i < frame.size(); i++) {
                    if (MyMaze.getValue(frame.get(i)) == 0) {
                        MyMaze.setGoalPosition(frame.get(i));
                        break;
                    }

                }
            }
        }
        return MyMaze;
    }

    private ArrayList<Position> find_my_neighbors(Position p, Maze maze) {
        ArrayList<Position> neighbors = new ArrayList<>();
        if (p.getColumnIndex() + 2 <= maze.getColumns())
        {
            if (maze.getValue(p.getColumnIndex()+2,p.getRowIndex()) == 1) {
            neighbors.add(new Position(p.getColumnIndex()+2, p.getRowIndex()));
            neighbors.get(-1).setFather(new Position(p.getColumnIndex()+1, p.getRowIndex()));
            }
        }
        if (p.getColumnIndex() -2  >= 0)
        {
            if (maze.getValue(p.getColumnIndex()-2,p.getRowIndex()) == 1) {
            neighbors.add(new Position(p.getColumnIndex()-2, p.getRowIndex()));
            neighbors.get(-1).setFather(new Position(p.getColumnIndex()-1, p.getRowIndex()));}
        }
        if (p.getRowIndex() + 2 <= maze.getRows())
        {
            if (maze.getValue(p.getColumnIndex(),p.getRowIndex()+2) == 1) {
            neighbors.add(new Position(p.getColumnIndex(), p.getRowIndex() +2));
            neighbors.get(-1).setFather(new Position(p.getColumnIndex(), p.getRowIndex()+1));}
        }
        if (p.getRowIndex() - 2 >= 0)
        {
            if (maze.getValue(p.getColumnIndex(),p.getRowIndex()-2) == 1) {
            neighbors.add(new Position(p.getColumnIndex(), p.getRowIndex() -2));
            neighbors.get(-1).setFather(new Position(p.getColumnIndex(), p.getRowIndex()-1));}
        }
        return neighbors;
    }

    public MyMazeGenerator() {

    }
}
