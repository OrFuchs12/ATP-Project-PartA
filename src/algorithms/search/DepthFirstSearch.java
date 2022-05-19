package algorithms.search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Stack;

public class DepthFirstSearch extends ASearchingAlgorithm{
    protected Stack<AState> DepthOpenList;

    public DepthFirstSearch()
    {
        DepthOpenList = new Stack<AState>();
    }

    @Override
    public Solution solve(ISearchable problem) {
        problem.cleanVisited();
        AState curr;
        DepthOpenList.add(problem.GetStartState());
        while (!DepthOpenList.isEmpty()) {
            curr = DepthOpenList.pop();
            if (!problem.isVisited(curr)) {
                problem.addVisited(curr);
                addVisitedNode();}
            if (curr.equals(problem.GetGoalState())) {
                Solution sol = problem.makeSol(curr);
                return sol;}
            else {
                ArrayList<AState> pStates = problem.GetAllPossibleStates(curr);
                Collections.reverse(pStates);
                for (int i = 0; i < pStates.size(); i++) {
                    if (!DepthOpenList.contains(pStates.get(i))) {
                        DepthOpenList.add((pStates.get(i)));
                        pStates.get(i).setCameFrom(curr);}
                    if (pStates.get(i).equals(problem.GetGoalState())) {
                        pStates.get(i).setCameFrom(curr);
                        Solution sol = problem.makeSol(pStates.get(i));
                        return sol;}}}}
        return null;}

    @Override
    public String getName() {
        return "DepthFirstSearch";
    }
}
