package algorithms.mazeGenerators;

import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.Random;

public class SimpleMazeGenerator extends AMazeGenerator {
    public SimpleMazeGenerator() {
    }

    @Override
    public Maze generate(int columns, int rows) {
        /*
        Creating the Maze
         */
        Maze Simple = new Maze(columns, rows);
        Random rand = new Random();
        for(int i =0; i<rows; i++){
            for(int j = 0; j< columns; j++){
                int rand_int = rand.nextInt(2);
                Simple.AddPosition(j,i,rand_int);
            }
        }

        /*
        set the start and goal position to 0 so we can start there
         */
//        Simple.setPosition(Simple.getStartPosition(),0);
//        Simple.setPosition(Simple.getGoalPosition(),0);
        /*
        breaking the walls to pave the way for a solution
         */
        Position curr = new Position(Simple.getStartPosition().getColumnIndex(), Simple.getStartPosition().getRowIndex());
        while (curr.getColumnIndex() != Simple.getGoalPosition().getColumnIndex()){
            Simple.setPosition(curr,0);
            if(curr.getColumnIndex() > Simple.getGoalPosition().getColumnIndex()){
                curr.Go_Left();
            }
            if(curr.getColumnIndex() < Simple.getGoalPosition().getColumnIndex()){
                curr.Go_Right();
            }

        }
        while (curr.getRowIndex() != Simple.getGoalPosition().getRowIndex()){
            Simple.setPosition(curr,0);
            if(curr.getRowIndex() > Simple.getGoalPosition().getRowIndex()){
                curr.Go_Down();
            }
            if(curr.getRowIndex() < Simple.getGoalPosition().getRowIndex()){
                curr.Go_Up();
            }


        }
        Simple.setPosition(Simple.getGoalPosition(),0);
        return Simple;
    }
}
