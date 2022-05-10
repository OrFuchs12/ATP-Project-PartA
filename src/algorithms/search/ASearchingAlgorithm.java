package algorithms.search;

import java.util.PriorityQueue;

public abstract class ASearchingAlgorithm  implements ISearchingAlgorithm{
    protected PriorityQueue<AState> openList;
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
}
