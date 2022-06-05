package algorithms.mazeGenerators;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


/**
 * generates a maze according to the Prim algorithm
 */
public class MyMazeGenerator extends AMazeGenerator implements Serializable {
    public MyMazeGenerator() {
    }

    /**
     * while there are still possible neighbors- starts at the StartPostion, finds all neighbors
     * and chooses one randomly, then chooses a father of the neighbor. takes into consideration
     * the handling of a 2X2 maze
     *
     * @param columns - number of columns in the maze
     * @param rows    - number of rows in the maze
     * @return the generated maze
     */
    @Override
    public Maze generate(int columns, int rows) {
        ArrayList<Position> all_neighbors = new ArrayList<Position>();
        Maze MyMaze = new Maze(columns, rows);
        if ((columns == 1 && rows == 1) || columns == 0 || rows == 0) {
            rows = 2;
            columns = 2;}
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                MyMaze.setPosition(j, i, 1);}}
        MyMaze.setPosition(MyMaze.getStartPosition(), 0);
        if (rows == 2 && columns == 2) {
            if (MyMaze.getStartPosition().getRowIndex() != MyMaze.getGoalPosition().getRowIndex() && MyMaze.getStartPosition().getColumnIndex() != MyMaze.getGoalPosition().getColumnIndex()) {
                Random rand = new Random();
                int coltozero = rand.nextInt(2);
                Position zero = new Position(0, 0);
                if (MyMaze.getGoalPosition().equals(zero) || MyMaze.getStartPosition().equals(zero)) {
                    if (coltozero == 0) {
                        MyMaze.setPosition(coltozero, 1, 0);}
                    else {
                        MyMaze.setPosition(coltozero, 0, 0);}}
                else {
                    MyMaze.setPosition(coltozero, coltozero, 0);}}
            MyMaze.setPosition(MyMaze.getGoalPosition(), 0);}
        else {
            Position curr = new Position(MyMaze.getStartPosition().getColumnIndex(), MyMaze.getStartPosition().getRowIndex());
            do {
                find_neighbors(curr, MyMaze, all_neighbors);
                Random rand = new Random();
                int rand_neighbor = rand.nextInt(all_neighbors.size());
                curr = all_neighbors.remove(rand_neighbor);
                MyMaze.setPosition(curr, 0);
                int rand_father = rand.nextInt(curr.getFather().size());
                MyMaze.setPosition(curr.getFather().get(rand_father), 0);
            } while (!all_neighbors.isEmpty());
            if (MyMaze.getValue(MyMaze.getGoalPosition()) == 1) {
                ArrayList<Position> frame = MyMaze.getFrame();
                for (int i = 0; i < frame.size(); i++) {
                    if (MyMaze.getValue(frame.get(i)) == 0 && !frame.get(i).equals(MyMaze.getStartPosition())) {
                        MyMaze.setGoalPosition(frame.get(i));
                        break;}}}}
        return MyMaze;}


    /**
     * finds all neighbors two places from a current postion that have a value of 1 and are in the maze
     * @param curr
     * @param MyMaze
     * @param all_neighbors
     */

    private void find_neighbors(Position curr, Maze MyMaze, ArrayList<Position> all_neighbors ) {
        //ArrayList<Position> all_neighbors = new ArrayList<Position>();
        if (curr.getColumnIndex() + 2 < MyMaze.getColumns()) {
            if (MyMaze.getValue(curr.getColumnIndex() + 2, curr.getRowIndex()) == 1) {
                Position one = new Position(curr.getColumnIndex() + 2, curr.getRowIndex());
                if (!all_neighbors.contains(one)) {
                    all_neighbors.add(one);
                    all_neighbors.get(all_neighbors.size() - 1).setFather(new Position(curr.getColumnIndex() + 1, curr.getRowIndex()));}
                else {
                    if (!curr.getFather().contains(one)) {
                        curr.setFather(one);}}}}
        if (curr.getColumnIndex() - 2 >= 0) {
            if (MyMaze.getValue(curr.getColumnIndex() - 2, curr.getRowIndex()) == 1) {
                Position two = new Position(curr.getColumnIndex() - 2, curr.getRowIndex());
                if (!all_neighbors.contains(two)) {
                    all_neighbors.add(two);
                    all_neighbors.get(all_neighbors.size() - 1).setFather(new Position(curr.getColumnIndex() - 1, curr.getRowIndex()));
                } else {
                    if (!curr.getFather().contains(two)) {
                        curr.setFather(two);}}}}
        if (curr.getRowIndex() + 2 < MyMaze.getRows()) {
            if (MyMaze.getValue(curr.getColumnIndex(), curr.getRowIndex() + 2) == 1) {
                Position three = new Position(curr.getColumnIndex(), curr.getRowIndex() + 2);
                if (!all_neighbors.contains(three)) {
                    all_neighbors.add(three);
                    all_neighbors.get(all_neighbors.size() - 1).setFather(new Position(curr.getColumnIndex(), curr.getRowIndex() + 1));}
                else {
                    if (!curr.getFather().contains(three)) {
                        curr.setFather(three);}}}}
        if (curr.getRowIndex() - 2 >= 0) {
            if (MyMaze.getValue(curr.getColumnIndex(), curr.getRowIndex() - 2) == 1) {
                Position four = new Position(curr.getColumnIndex(), curr.getRowIndex() - 2);
                if (!all_neighbors.contains(four)) {
                    all_neighbors.add(four);
                    all_neighbors.get(all_neighbors.size() - 1).setFather(new Position(curr.getColumnIndex(), curr.getRowIndex() - 1));}
                else {
                    if (!curr.getFather().contains(four)) {
                        curr.setFather(four);}}}}
        //return all_neighbors;
        }

}



