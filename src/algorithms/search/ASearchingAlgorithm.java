package algorithms.search;

import java.util.*;

public abstract class ASearchingAlgorithm  implements ISearchingAlgorithm{
    protected Queue<AState> openList;
    private int visitedNodes;
    private ArrayList<AState> visited;
    public abstract Solution solve(ISearchable problem);
    public ASearchingAlgorithm(){
        openList = new LinkedList<AState>();
        visitedNodes = 0;
        visited = new ArrayList<>();
    }

//    class theComparator implements Comparator<AState> {
//        public int compare(AState state1, AState state2)
//        {
//            return 1;
//        }
//    }

    @Override
    public abstract String getName();

    @Override
    public int getNumberOfNodesEvaluated() {
        return visited.size();
    }

    public ArrayList<AState> getVisited() {
        return visited;
    }
    public boolean isInVisited(AState other) {
        for (int i = 0; i < visited.size(); i++)
        {
            if (visited.get(i).equals(other))
            {
                return true;
            }
         }
    return false;
    }


    public AState PopOpenList(){
        ///visitedNodes++;
        AState next = openList.poll();
        //next.setVisited();
        if (!isInVisited(next)){
            visited.add(next);}
        return next;
    }

//    public void setVisited(AState visited) {
//        this.visited.add(visited);
//    }

    public void addVisitedNode()
    {
        visitedNodes++;
    }
    public abstract void addToOpenList(AState curr, ArrayList<AState> pStates);
}
