package algorithms.search;

import java.util.ArrayList;

public class Solution {
    private ArrayList<AState> solution;
    int total_cost;

    public Solution(){
        solution = new ArrayList<AState>();
        total_cost =0;}

    public void addToSolution(AState state){
        solution.add(0,state);
        total_cost += state.getCost();}

    public ArrayList<AState> getSolutionPath()
    {
        return solution;
    }

    public int getTotal_cost() {return total_cost;}
}
