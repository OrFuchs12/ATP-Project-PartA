package algorithms.search;

import java.util.ArrayList;

public interface ISearchable {
    AState GetStartState();
    AState GetGoalState();
    ArrayList<AState> GetAllPossibleStates(AState state);
}
