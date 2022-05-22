package algorithms.search;


/**
 * interface for searching algorithms. includes: solve, getName, getNumberofNodesEvaluated.
 */
public interface ISearchingAlgorithm {
     /**
      * function that gets as an argument the problem that the user wants to solve, and return a solution for the problem
      * @param problem
      * @return a solution for the problem
      */
     Solution solve(ISearchable problem);

     /**
      *
      * @return the name of the searching algorithm
      */
     String getName();

     /**
      *
      * @return the number of nodes the searching algorithm evaluated so far
      */
     int getNumberOfNodesEvaluated();
}
