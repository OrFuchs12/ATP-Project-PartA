package algorithms.search;

import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.SimpleMazeGenerator;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class BestFirstSearchTest {
    ASearchingAlgorithm Best = new BestFirstSearch();
    Random rand = new Random();
    IMazeGenerator mg = new MyMazeGenerator();


    @Test
    void getName() {
        assertEquals("BestFirstSearch", Best.getName());
    }

    @Test
    void solve10() {
        Maze maze10 = mg.generate(10, 10);
        ISearchable s_maze = new SearchableMaze(maze10);
        Solution sol =Best.solve(s_maze);
        assertTrue(sol.getSolutionPath().get(0).equals(s_maze.GetStartState()));
        assertTrue(sol.getSolutionPath().get(sol.getSolutionPath().size()-1).equals(s_maze.GetGoalState()));
        assertTrue(Best.getNumberOfNodesEvaluated() <= 100);
        if (sol.getSolutionPath().size() >2) {
        int num = rand.nextInt( sol.getSolutionPath().size()-2);
        AState state1 = sol.getSolutionPath().get(num+1);
        AState state2 = sol.getSolutionPath().get(num+2);
        MazeState m_state1 = (MazeState)state1;
        MazeState m_state2 = (MazeState)state2;
        assertTrue(state1.getCameFrom() != null);
        assertFalse(state1.equals(state2));
        assertTrue(Math.abs(m_state1.getCol() - m_state2.getCol()) <= 1);
        assertTrue(Math.abs(m_state1.getRow() - m_state2.getRow()) <= 1);}
        else {
            MazeState ms = (MazeState) s_maze.GetStartState();
            MazeState es = (MazeState) s_maze.GetGoalState();
            assertTrue(Math.abs(ms.getCol() -es.getCol()) <= 1);
            assertTrue(Math.abs(ms.getRow() -es.getRow()) <= 1);
        }
        assertFalse(sol.getSolutionPath().size() < 2);
    }

    @Test
    void solve100() {
        Maze maze100 = mg.generate(100, 100);
        ISearchable s_maze100 = new SearchableMaze(maze100);
        Solution sol100 =Best.solve(s_maze100);
        assertTrue(sol100.getSolutionPath().get(0).equals(s_maze100.GetStartState()));
        assertTrue(sol100.getSolutionPath().get(sol100.getSolutionPath().size()-1).equals(s_maze100.GetGoalState()));
        assertTrue(Best.getNumberOfNodesEvaluated() <= 10000);
        if (sol100.getSolutionPath().size() >2 ){
        int num100 = rand.nextInt( sol100.getSolutionPath().size()-2);
        AState state100 = sol100.getSolutionPath().get(num100+1);
        AState state200 = sol100.getSolutionPath().get(num100+2);
        MazeState m_state100 = (MazeState)state100;
        MazeState m_state200 = (MazeState)state200;
        assertTrue(state100.getCameFrom() != null);
        assertFalse(state100.equals(state200));
        assertTrue(Math.abs(m_state100.getCol() - m_state200.getCol()) <= 1);
        assertTrue(Math.abs(m_state100.getRow() - m_state200.getRow()) <= 1);}
        else {
            MazeState ms = (MazeState) s_maze100.GetStartState();
            MazeState es = (MazeState) s_maze100.GetGoalState();
            assertTrue(Math.abs(ms.getCol() -es.getCol()) <= 1);
            assertTrue(Math.abs(ms.getRow() -es.getRow()) <= 1);
        }
        assertFalse(sol100.getSolutionPath().size() < 2);


    }

    @Test
    void solve1000()
    {
        Maze maze = mg.generate(1000, 1000);
        ISearchable s_maze = new SearchableMaze(maze);
        Solution sol =Best.solve(s_maze);
        assertTrue(sol.getSolutionPath().get(0).equals(s_maze.GetStartState()));
        assertTrue(sol.getSolutionPath().get(sol.getSolutionPath().size()-1).equals(s_maze.GetGoalState()));
        assertTrue(Best.getNumberOfNodesEvaluated() <= 1000000);
        if (sol.getSolutionPath().size() >2)
        {
            int num = rand.nextInt( sol.getSolutionPath().size()-2);
            AState state1 = sol.getSolutionPath().get(num+1);
            AState state2 = sol.getSolutionPath().get(num+2);
            MazeState m_state1 = (MazeState)state1;
            MazeState m_state2 = (MazeState)state2;
            assertTrue(state1.getCameFrom() != null);
            assertFalse(state1.equals(state2));
            assertTrue(Math.abs(m_state1.getCol() - m_state2.getCol()) <= 1);
            assertTrue(Math.abs(m_state1.getRow() - m_state2.getRow()) <= 1);
        }
        else {
            MazeState ms = (MazeState) s_maze.GetStartState();
            MazeState es = (MazeState) s_maze.GetGoalState();
            assertTrue(Math.abs(ms.getCol() -es.getCol()) <= 1);
            assertTrue(Math.abs(ms.getRow() -es.getRow()) <= 1);
        }
        assertFalse(sol.getSolutionPath().size() < 2);

    }



}