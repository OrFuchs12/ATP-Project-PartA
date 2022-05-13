package algorithms.search;

import java.util.*;

public abstract class ASearchingAlgorithm  implements ISearchingAlgorithm{
    protected Queue<AState> openList;
    private int visitedNodes;
    public abstract Solution solve(ISearchable problem);
    public ASearchingAlgorithm(){
        openList = new LinkedList<AState>();
        visitedNodes = 0;
    }

    @Override
    public abstract String getName();

    @Override
    public int getNumberOfNodesEvaluated() {
        return 0;
    }

    public AState PopOpenList(){
        visitedNodes++;
        AState next = openList.poll();
        next.setVisited();
        return next;
    }
    public abstract void addToOpenList(AState curr, ArrayList<AState> pStates);
}
