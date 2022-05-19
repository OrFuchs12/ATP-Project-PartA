package algorithms.search;
import java.util.ArrayList;


/**
 * Interface of a searchable object. Includes getters of s
 */
public interface ISearchable {
    AState GetStartState();
    AState GetGoalState();
    ArrayList<AState> GetAllPossibleStates(AState state);
    Solution makeSol(AState stare);
    boolean isVisited(AState state);
    void addVisited(AState state);
    void cleanVisited();
}
