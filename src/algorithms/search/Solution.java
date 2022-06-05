package algorithms.search;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * hold the final list of states the make up the solution to the problem
 * also calculates total cost
 */

public class Solution implements Serializable {
    private ArrayList<AState> solution;
    int total_cost;

    public Solution(){
        solution = new ArrayList<AState>();
        total_cost =0;}

    /**
     * adds a state to the beginning of the solution and adds the cost
     * @param state
     */
    public void addToSolution(AState state){
        solution.add(0,state);
        total_cost += state.getCost();}

    /**
     *
     * @return an arrayList contains all the states in the solution
     */
    public ArrayList<AState> getSolutionPath()
    {
        return solution;
    }

    /**
     *
     * @return the total cost of the states in the solution
     */
    public int getTotal_cost() {return total_cost;}
}
