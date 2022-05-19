package algorithms.search;

import java.util.ArrayList;

public interface ISearchable {
    AState GetStartState();
    AState GetGoalState();
    ArrayList<AState> GetAllPossibleStates(AState state);
    Solution makeSol(AState stare);
    boolean isVisited(AState state);
    void addVisited(AState state);
    void cleanVisited();

}
