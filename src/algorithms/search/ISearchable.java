package algorithms.search;

import java.util.ArrayList;

public interface ISearchable {
    ArrayList<AState> visited = null;
    AState GetStartState();
    AState GetGoalState();
    ArrayList<AState> GetAllPossibleStates(AState state);
    boolean[][] getVisited();
    public Solution makeSol(AState stare);
    public boolean isVisited(AState state);
    public void addVisited(AState state);

}
