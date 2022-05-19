package algorithms.search;
import java.util.*;

/**
 * an abstract class for searching algorithm. Adds a queue and a field of visited nodes.
 */

public abstract class ASearchingAlgorithm  implements ISearchingAlgorithm {
    protected Queue<AState> openList;
    private int visitedNodes;

    public abstract Solution solve(ISearchable problem);

    public ASearchingAlgorithm() {
        openList = new LinkedList<AState>();
        visitedNodes = 0;}

    @Override
    public abstract String getName();

    /**
     *
     * @return the number of cells that were visited before the solution was ready
     */
    @Override
    public int getNumberOfNodesEvaluated() {
        return visitedNodes;
    }

    public void addVisitedNode() {
        visitedNodes++;
    }



}