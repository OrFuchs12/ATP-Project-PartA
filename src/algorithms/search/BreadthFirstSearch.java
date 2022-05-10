package algorithms.search;

import java.util.ArrayList;

public class BreadthFirstSearch extends ASearchingAlgorithm {
    @Override
    public Solution Solve(ISearchable problem) {
        AState curr= problem.GetStartState().getCopy();

        while (!curr.equals(problem.GetGoalState())) {
            ArrayList<AState> PStates = problem.GetAllPossibleStates(curr);
            for (int i = 0; i < PStates.size(); i++) {
                if (!PStates.get(i).isVisited()) {
                    openList.add(PStates.get(i));
                    PStates.get(i).setCameFrom(curr);
                }
            }
            curr = PopOpenList();
        }
        Solution sol = new Solution();
        while (curr!= problem.GetStartState()){
            sol.addToSolution(curr);
            curr = curr.getCameFrom();
        }
        sol.addToSolution(problem.GetStartState());
        return sol;
    }



}
