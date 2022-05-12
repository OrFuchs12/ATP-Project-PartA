package algorithms.search;

import java.util.ArrayList;

public class Solution {
    private ArrayList<AState> solution;
    public Solution(){
        solution = new ArrayList<AState>();
    }
    public void addToSolution(AState state){
        solution.add(0,state);
    }

    public ArrayList<AState> getSolution() {
        return solution;
    }

    public ArrayList<AState> getSolutionPath()
    {
        return solution;
    }
}
