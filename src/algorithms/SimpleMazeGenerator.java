package algorithms;

import algorithms.AMazeGenerator;

import java.util.ArrayList;
import java.util.Collections;
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
                Simple.n_Maze.get(i).add(j,rand_int);
            }
        }
        /*
        Randomize start position and goal position
         */
        ArrayList<Position> Frame = new ArrayList<>(2*rows + 2* columns - 4) ;
        for(int i=0; i<columns; i++){
            Frame.add(new Position(i,0));
            Frame.add(new Position(i,rows-1) );
        }
        for (int i = 1 ; i<rows-1; i++){
            Frame.add(new Position(0,i));
            Frame.add(new Position(columns-1, i));
        }

        Collections.shuffle(Frame);
        Simple.setStartPosition(Frame.get(0));
        Simple.setGoalPosition(Frame.get(1));
        /*
        set the start and goal position to 0 so we can start there
         */
//        Simple.setPosition(Simple.getStartPosition(),0);
//        Simple.setPosition(Simple.getGoalPosition(),0);
        /*
        breaking the walls to pave the way for a solution
         */
        Position curr = new Position(Simple.getStartPosition().getRowIndex(), Simple.getStartPosition().getColumnIndex());
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
