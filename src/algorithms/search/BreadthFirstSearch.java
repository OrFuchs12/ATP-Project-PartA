package algorithms.search;

import java.util.ArrayList;
import java.util.Comparator;

public class BreadthFirstSearch extends ASearchingAlgorithm {
    public BreadthFirstSearch() {
        super();
    }


    @Override
    public Solution solve(ISearchable problem) {
        AState curr= problem.GetStartState();//.getCopy();
        getVisited().add(curr);

        while (!curr.compare_state(problem.GetGoalState())) {
            ArrayList<AState> PStates = problem.GetAllPossibleStates(curr);
            addToOpenList(curr,PStates);
            curr = PopOpenList();
        }
        Solution sol = new Solution();
        while (!curr.compare_state(problem.GetStartState())){
            sol.addToSolution(curr);
            curr = curr.getCameFrom();
        }
        sol.addToSolution(problem.GetStartState());
        return sol;
    }
    @Override
    public void addToOpenList(AState curr, ArrayList<AState> pStates){
        for (int i = 0; i < pStates.size(); i++) {
            if (!isInVisited(pStates.get(i))) {
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
