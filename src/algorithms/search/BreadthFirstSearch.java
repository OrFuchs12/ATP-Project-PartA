package algorithms.search;

import java.util.ArrayList;
import java.util.Comparator;

public class BreadthFirstSearch extends ASearchingAlgorithm {
    public BreadthFirstSearch() {
        super();
    }

    @Override
    public Solution solve(ISearchable problem)
    {
        problem.cleanVisited();
        AState curr;
        openList.add(problem.GetStartState());
        while (!openList.isEmpty())
        {
            curr = openList.poll();
            if (!problem.isVisited(curr))
            {
                problem.addVisited(curr);
                addVisitedNode();
            }
            if (curr.equals(problem.GetGoalState()))
            {
                Solution sol = problem.makeSol(curr);
                return sol;
            }
            else
            {
                ArrayList<AState> pStates = problem.GetAllPossibleStates(curr);
                for (int i = 0; i < pStates.size(); i++) {
                    if (!openList.contains(pStates.get(i))) {
                        openList.add(pStates.get(i));
                        pStates.get(i).setCameFrom(curr);
                    }
                    if (pStates.get(i).equals(problem.GetGoalState())) {
                        pStates.get(i).setCameFrom(curr);
                        Solution sol = problem.makeSol(pStates.get(i));
                        return sol;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public String getName() {
        return "BreadthFirstSearch";
    }
}
