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
    public Solution solve(ISearchable problem)
    {
        AState curr;
        BestOpenList.add(problem.GetStartState());
        while (!BestOpenList.isEmpty())
        {
            curr = BestOpenList.poll();
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
                    if (!BestOpenList.contains(pStates.get(i))) {
                        BestOpenList.add(pStates.get(i));
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
//    @Override
//    public Solution solve(ISearchable problem) {
//        AState curr= problem.GetStartState().getCopy();
//        getVisited().add(curr);
//
//        while (!curr.equals(problem.GetGoalState())) {
//            ArrayList<AState> PStates = problem.GetAllPossibleStates(curr);
//            addToOpenList(curr,PStates);
//            curr = PopOpenList();
//        }
//        Solution sol = new Solution();
//        while (!curr.equals(problem.GetStartState())){
//            sol.addToSolution(curr);
//            curr = curr.getCameFrom();
//        }
//        sol.addToSolution(problem.GetStartState());
//        return sol;
//    }
//
//    @Override
//    public AState PopOpenList() {
//        addVisitedNode();
//        AState next = BestOpenList.poll();
////        if (!isInVisited(next)) {
////            getVisited().add(next);
////        }
//        return next;
//    }
////todo write this
////    public void addToOpenList(AState curr, ArrayList<AState> pStates){
////        for (int i = 0; i < pStates.size(); i++) {
////            if (//!problem(pStates.get(i)) && !BestOpenList.contains((pStates.get(i)))) {
////                BestOpenList.add(pStates.get(i));
////                pStates.get(i).setCameFrom(curr);
////            }
////        }
////
////    }
//


}
