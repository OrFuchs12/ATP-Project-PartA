package algorithms.search;

import java.util.ArrayList;

public interface ISearchable {
    ArrayList<AState> visited = null;
    AState GetStartState();
    AState GetGoalState();
    ArrayList<AState> GetAllPossibleStates(AState state);
    ArrayList<AState> getVisited();

}
