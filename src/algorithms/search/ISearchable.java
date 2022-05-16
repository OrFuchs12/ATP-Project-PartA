package algorithms.search;

import java.util.ArrayList;

public interface ISearchable {
    //ArrayList<AState> visited = null;

    AState GetStartState();
    AState GetGoalState();
    ArrayList<AState> GetAllPossibleStates(AState state);
    //boolean[][] getVisited();
    Solution makeSol(AState stare);
    boolean isVisited(AState state);
    void addVisited(AState state);
    void cleanVisited();

}
