package algorithms.search;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public abstract class ASearchingAlgorithm  implements ISearchingAlgorithm{
    protected Queue<AState> openList;
    private int visitedNodes;
    public abstract Solution Solve(ISearchable problem);
    public ASearchingAlgorithm(){
        openList = new PriorityQueue<AState>();
        visitedNodes = 0;
    }
    public AState PopOpenList(){
        visitedNodes++;
        AState next = openList.poll();
        next.setVisited();
        return next;
    }
    public abstract void addToOpenList(AState curr, ArrayList<AState> pStates);
}
