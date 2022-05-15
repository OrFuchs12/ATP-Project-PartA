package algorithms.mazeGenerators;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


/**
 * generates a maze according to the Prim algorithm
 */
public class MyMazeGenerator extends AMazeGenerator {

    /**
     * while there are still possible neighbors- starts at the StartPostion, finds all neighbors
     * and chooses one randomly, then chooses a father of the neighbor. takes into consideration
     * the handling of a 2X2 maze
     * @param columns - number of columns in the maze
     * @param rows - number of rows in the maze
     * @return the generated maze
     */
    @Override
    public Maze generate(int columns, int rows) {
        ArrayList<Position> all_neighbors = new ArrayList<Position>();
        Maze MyMaze = new Maze(columns, rows);
        for (int i=0; i<rows; i++){
            for (int j =0; j<columns; j++){
                MyMaze.AddPosition(j,i,1);
            }
        }
        MyMaze.setPosition(MyMaze.getStartPosition(),0);
        if (rows == 2 && columns == 2)
        {
            if (!MyMaze.getStartPosition().equals(MyMaze.getGoalPosition()))
            {
                Random rand = new Random();
                int rand_point = rand.nextInt(2);
                MyMaze.setPosition(MyMaze.getFrame().get(rand_point +2), 0);

            }
            MyMaze.setPosition(MyMaze.getGoalPosition(), 0);

        }
        else {
            //Random rand = new Random();
//            int colRand = rand.nextInt(columns +1);
//            int rowRand = rand.nextInt(rows + 1);
//            Position curr = new Position(colRand,rowRand);
            Position curr = new Position(MyMaze.getStartPosition().getColumnIndex(),MyMaze.getStartPosition().getRowIndex());
            do {
                //all_neighbors.addAll(find_my_neighbors(curr, MyMaze));
                if (curr.getColumnIndex() + 2 < MyMaze.getColumns())
                {
                    if (MyMaze.getValue(curr.getColumnIndex()+2,curr.getRowIndex()) == 1) {
                        Position one = new Position(curr.getColumnIndex() + 2, curr.getRowIndex());
                        if (!all_neighbors.contains(one)) {
                            all_neighbors.add(one);
                            all_neighbors.get(all_neighbors.size() - 1).setFather(new Position(curr.getColumnIndex() + 1, curr.getRowIndex()));
                        }
                    }
                }
                if (curr.getColumnIndex() -2  >= 0) {
                    if (MyMaze.getValue(curr.getColumnIndex() - 2, curr.getRowIndex()) == 1) {
                        Position two = new Position(curr.getColumnIndex() - 2, curr.getRowIndex());
                        if (!all_neighbors.contains(two)) {
                            all_neighbors.add(two);
                            all_neighbors.get(all_neighbors.size() - 1).setFather(new Position(curr.getColumnIndex() - 1, curr.getRowIndex()));
                        }
                    }
                }
                if (curr.getRowIndex() + 2 < MyMaze.getRows())
                {
                    if (MyMaze.getValue(curr.getColumnIndex(),curr.getRowIndex()+2) == 1) {
                        Position three = new Position(curr.getColumnIndex(), curr.getRowIndex() + 2);
                        if (!all_neighbors.contains(three)) {
                            all_neighbors.add(three);
                            all_neighbors.get(all_neighbors.size() - 1).setFather(new Position(curr.getColumnIndex(), curr.getRowIndex() + 1));
                        }
                    }
                }
                if (curr.getRowIndex() - 2 >= 0) {
                    if (MyMaze.getValue(curr.getColumnIndex(), curr.getRowIndex() - 2) == 1) {
                        Position four = new Position(curr.getColumnIndex(), curr.getRowIndex() - 2);
                        if (!all_neighbors.contains(four)) {
                            all_neighbors.add(four);
                            all_neighbors.get(all_neighbors.size() - 1).setFather(new Position(curr.getColumnIndex(), curr.getRowIndex() - 1));
                        }
                    }
                }
                Collections.shuffle(all_neighbors);
                curr = all_neighbors.remove(0);
                MyMaze.setPosition(curr, 0);
                if (curr.getColumnIndex() +2 < columns && MyMaze.getValue(curr.getColumnIndex()+2, curr.getRowIndex()) == 0)
                {
                    Position father1 = new Position(curr.getColumnIndex()+1, curr.getRowIndex());
                    if (!curr.getFather().contains(father1)) {
                        curr.setFather(father1);}
                }
                if (curr.getColumnIndex() -2 >= 0 && MyMaze.getValue(curr.getColumnIndex()-2, curr.getRowIndex()) == 0)
                {
                    Position father2= new Position(curr.getColumnIndex()-1, curr.getRowIndex());
                    if (!curr.getFather().contains(father2))
                    {
                        curr.setFather(father2);
                    }

                }
                if (curr.getRowIndex() +2 < rows && MyMaze.getValue(curr.getColumnIndex(), curr.getRowIndex()+2) == 0)
                {
                    Position father3= new Position(curr.getColumnIndex(), curr.getRowIndex()+1);
                    if (!curr.getFather().contains(father3)) {
                        curr.setFather(father3);
                    }
                }
                if (curr.getRowIndex() -2 >=0 && MyMaze.getValue(curr.getColumnIndex(), curr.getRowIndex()-2) == 0)
                {
                    Position father4= new Position(curr.getColumnIndex(), curr.getRowIndex()-1);
                    if(!curr.getFather().contains(father4)){
                    curr.setFather(father4);}
                }
                curr.MixFather();
                MyMaze.setPosition(curr.getFather().get(0), 0);
            } while (!all_neighbors.isEmpty());
            if (MyMaze.getValue(MyMaze.getGoalPosition()) == 1)
            {
                ArrayList<Position> frame = MyMaze.getFrame();

                //todo: changed i to start from 0
                for (int i = 0; i < frame.size(); i++)
                {
                    if (MyMaze.getValue(frame.get(i)) == 0 && !frame.get(i).equals(MyMaze.getStartPosition()))
                    {
                        MyMaze.setGoalPosition(frame.get(i));
                        break;
                    }

                }
            }
//            if(MyMaze.getValue(MyMaze.getStartPosition()) == 1) {
//                ArrayList<Position> frame = MyMaze.getFrame();
//                Collections.shuffle(frame);
//                for(int i = 0; i<frame.size(); i++){
//                    Position temp = frame.get(i);
//                    if (temp.getRowIndex() == 0){
//                        temp.Go_Up();
//                        if (MyMaze.getValue(temp)== 0){
//                            temp.Go_Down();
//                            MyMaze.setStartPosition(temp);
//                        }
//
//                    }
//                }
//            }
        }
        return MyMaze;
    }


    /**
     *
     * @param p
     * @param maze
     * @return a list of neighbors in a distance of 2 in the maze
     */
    private ArrayList<Position> find_my_neighbors(Position p, Maze maze) {
        ArrayList<Position> neighbors = new ArrayList<>();
        if (p.getColumnIndex() + 2 < maze.getColumns())
        {
            if (maze.getValue(p.getColumnIndex()+2,p.getRowIndex()) == 1) {
            neighbors.add(new Position(p.getColumnIndex()+2, p.getRowIndex()));
            neighbors.get(neighbors.size()-1).setFather(new Position(p.getColumnIndex()+1, p.getRowIndex()));
            }
        }
        if (p.getColumnIndex() -2  >= 0)
        {
            if (maze.getValue(p.getColumnIndex()-2,p.getRowIndex()) == 1) {
            neighbors.add(new Position(p.getColumnIndex()-2, p.getRowIndex()));
            neighbors.get(neighbors.size()-1).setFather(new Position(p.getColumnIndex()-1, p.getRowIndex()));}
        }
        if (p.getRowIndex() + 2 < maze.getRows())
        {
            if (maze.getValue(p.getColumnIndex(),p.getRowIndex()+2) == 1) {
            neighbors.add(new Position(p.getColumnIndex(), p.getRowIndex() +2));
            neighbors.get(neighbors.size()-1).setFather(new Position(p.getColumnIndex(), p.getRowIndex()+1));}
        }
        if (p.getRowIndex() - 2 >= 0)
        {
            if (maze.getValue(p.getColumnIndex(),p.getRowIndex()-2) == 1) {
            neighbors.add(new Position(p.getColumnIndex(), p.getRowIndex() -2));
            neighbors.get(neighbors.size()-1).setFather(new Position(p.getColumnIndex(), p.getRowIndex()-1));}
        }
        return neighbors;
    }

    public MyMazeGenerator() {}
}
