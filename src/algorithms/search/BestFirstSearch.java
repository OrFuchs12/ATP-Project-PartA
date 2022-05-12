package algorithms.search;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class BestFirstSearch extends BreadthFirstSearch {

    class theComperator implements Comparator<AState>{
        public int compare(AState state1, AState state2)
        {
            AState st1 = state1;
            AState st2 = state2;
            if (st1.getCost() >= st2.getCost()){
                return 1;
            }
            else return -1;

        }
    }

    @Override
    public String getName() {
        return "BestFirstSearch";
    }

    protected PriorityQueue<AState> BestOpenList;
    public BestFirstSearch(){
        BestOpenList = new PriorityQueue<AState>(new theComperator());
    }
    @Override
    public Solution solve(ISearchable problem) {
        AState curr= problem.GetStartState().getCopy();

        while (!curr.equals(problem.GetGoalState())) {
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
    public void addToOpenList(AState curr, ArrayList<AState> pStates){
        for (int i = 0; i < pStates.size(); i++) {
            if (!pStates.get(i).isVisited()) {
                BestOpenList.add(pStates.get(i));
                pStates.get(i).setCameFrom(curr);
            }
        }

    }



}
