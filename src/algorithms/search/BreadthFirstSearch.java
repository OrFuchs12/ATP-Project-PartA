package algorithms.search;

import java.util.ArrayList;

public class BreadthFirstSearch extends ASearchingAlgorithm {
    public BreadthFirstSearch() {
        super();
    }

    @Override
    public Solution solve(ISearchable problem) {
        AState curr= problem.GetStartState().getCopy();

        while (!curr.compare_states(problem.GetGoalState())) {
            ArrayList<AState> PStates = problem.GetAllPossibleStates(curr);
            addToOpenList(curr,PStates);
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
    @Override
    public void addToOpenList(AState curr, ArrayList<AState> pStates){
        for (int i = 0; i < pStates.size(); i++) {
            if (!pStates.get(i).isVisited()) {
                openList.add(pStates.get(i));
                pStates.get(i).setCameFrom(curr);
            }
        }

    }

    @Override
    public String getName() {
        return "BreadthFirstSearch";
    }
}
