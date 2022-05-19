package algorithms.search;
import java.util.*;

/**
 * searches by using the BestFirstSearch algorithm using a priority queue
 */
public class BestFirstSearch extends BreadthFirstSearch {
    protected PriorityQueue<AState> BestOpenList;

    /**
     * comparator for the priority queue, adds the smallest cost to the beginning
     */
    class theComparator implements Comparator<AState>{
        @Override
        public int compare(AState state1, AState state2) {
            AState st1 = state1;
            AState st2 = state2;
            if (st1.getCost() >= st2.getCost()){
                return 1;
            }
            else return -1;}}

    @Override
    public String getName() {return "BestFirstSearch";}


    public BestFirstSearch() {BestOpenList = new PriorityQueue<AState>(1, new theComparator());}

    /**
     * while the BestOpenList is not empty, find the neighbors of the current state
     * check if one of the neighbors is our goal state and if so make the solution with it
     * add the neighbors to the priority queue by their cost
     * if one of the neighbors is already in the queue swap it only if its cost it smaller
     * any state that was checked is mark as visited as to not check it again
     * @param problem
     * @return the Solution with smallest cost possible
     */
    @Override
    public Solution solve(ISearchable problem) {
        problem.cleanVisited();
        AState curr;
        BestOpenList.add(problem.GetStartState());
        while (!BestOpenList.isEmpty()) {
            curr = BestOpenList.poll();
            if (!problem.isVisited(curr)) {
                problem.addVisited(curr);
                addVisitedNode();}
            if (curr.equals(problem.GetGoalState())) {
                Solution sol = problem.makeSol(curr);
                return sol;}
            else {
                int mycost;
                int othercost;
                ArrayList<AState> pStates = problem.GetAllPossibleStates(curr);
                for (int i = 0; i < pStates.size(); i++) {
                    mycost= pStates.get(i).getCost();
                    pStates.get(i).setCost(curr.getCost()+mycost);
                    if (!BestOpenList.contains(pStates.get(i))) {
                        BestOpenList.add(pStates.get(i));
                        pStates.get(i).setCameFrom(curr);}
                    else {
                        boolean swap = false;
                        List<AState> keys = new ArrayList<>();
                        while(!BestOpenList.isEmpty() && !BestOpenList.peek().equals(pStates.get(i))) {
                            keys.add(BestOpenList.poll());}
                        othercost =BestOpenList.peek().getCost();
                        if( pStates.get(i).getCost() < othercost) {
                            BestOpenList.poll();
                            pStates.get(i).setCameFrom(curr);
                            swap = true;}
                        while (!keys.isEmpty()) {
                            BestOpenList.add(keys.remove(0));}
                        if (swap == true) {
                            BestOpenList.add(pStates.get(i));}}
                    if (pStates.get(i).equals(problem.GetGoalState())) {
                        pStates.get(i).setCameFrom(curr);
                        Solution sol = problem.makeSol(pStates.get(i));
                        return sol;}}}}
        return null;
    }
}
