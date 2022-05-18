package algorithms.search;

import java.util.*;

public class BestFirstSearch extends BreadthFirstSearch {


    class theComparator implements Comparator<AState>{
        @Override
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
    public String getName()
    {
        return "BestFirstSearch";
    }

    protected PriorityQueue<AState> BestOpenList;

    public BestFirstSearch()
    {
        BestOpenList = new PriorityQueue<AState>(1, new theComparator());
    }
    @Override
    public Solution solve(ISearchable problem)
    {
        problem.cleanVisited();
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
                int mycost;
                int othercost;
                ArrayList<AState> pStates = problem.GetAllPossibleStates(curr);
                for (int i = 0; i < pStates.size(); i++)
                {
                    if (!BestOpenList.contains(pStates.get(i)))
                    {
                        mycost= pStates.get(i).getCost();
                        pStates.get(i).setCost(curr.getCost()+mycost);
                        BestOpenList.add(pStates.get(i));
                        pStates.get(i).setCameFrom(curr);
                    }
                    else
                    {
                        boolean swap = false;
                        mycost= pStates.get(i).getCost();
                        pStates.get(i).setCost(curr.getCost()+mycost);
                        List<AState> keys = new ArrayList<>();
                        while(!BestOpenList.isEmpty() && !BestOpenList.peek().equals(pStates.get(i))) {
                            keys.add(BestOpenList.poll());
                        }
                        othercost =BestOpenList.peek().getCost();
                        if( mycost < othercost)
                        {
                            BestOpenList.poll();
                            pStates.get(i).setCameFrom(curr);
                            swap = true;
                        }
                        while (!keys.isEmpty())
                        {
                            BestOpenList.add(keys.remove(0));
                        }
                        if (swap == true)
                        {
                            BestOpenList.add(pStates.get(i));
                        }
                    }
                    if (pStates.get(i).equals(problem.GetGoalState()))
                    {
                        pStates.get(i).setCameFrom(curr);
                        Solution sol = problem.makeSol(pStates.get(i));
                        return sol;
                    }
                }
            }
        }
        return null;
    }
}
