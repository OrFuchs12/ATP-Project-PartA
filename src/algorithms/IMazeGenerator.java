package algorithms;

public interface IMazeGenerator {
     /**
      *
      * @param columns - number of columns in the maze
      * @param rows - number of rows in the maze
      * @return a Maze
      */
     public Maze generate(int columns, int rows);

     /**
      *
      * @param columns - number of columns in the maze
      * @param rows - number of rows in the maze
      * @return the time it takes to generate the maze by substracting the finel time from the starting time
      */
     public long measureAlgorithmTimeMillis(int columns, int rows);
}
