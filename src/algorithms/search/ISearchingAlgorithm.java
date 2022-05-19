package algorithms.search;


/**
 * interface for searching algorithms. includes: solve, getName, getNumberofNodesEvaluated.
 */
public interface ISearchingAlgorithm {
     Solution solve(ISearchable problem);
     String getName();
     int getNumberOfNodesEvaluated();
}
