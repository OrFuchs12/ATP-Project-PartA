package algorithms.search;
import java.util.ArrayList;


/**
 * Interface of a searchable object. Includes getters of s
 */
public interface ISearchable {
    /**
     *
     * @return the start state of the problem
     * */
    AState GetStartState();

    /**
     *
     * @return the goal state of this problem
     */
    AState GetGoalState();

    /**
     * function which gets a specific state and return an arrayList of all the state that the solver can move to
     * @param state
     * @return all the state which the solver can move to from 'state'
     */
    ArrayList<AState> getAllPossibleStates(AState state);

    /**
     *
     * @param stare the goal state
     * @return the solution for the problem
     */
    Solution makeSol(AState stare);

    /**
     *
     * @param state
     * @return true if the state marks as visited, false otherwise
     */
    boolean isVisited(AState state);

    /**
     * changes status of a state to visited
     * @param state
     */
    void addVisited(AState state);

    /**
     * cleans the visited array
     */
    void cleanVisited();
}
