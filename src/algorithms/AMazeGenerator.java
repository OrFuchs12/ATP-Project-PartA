package algorithms;

public abstract class AMazeGenerator implements IMazeGenerator {
    /**
     *
     * @param columns - number of columns in the maze
     * @param rows - number of rows in the maze
     * @return the time it takes to generate the maze by substracting the finel time from the starting time
     */
    @Override
    public long measureAlgorithmTimeMillis(int columns, int rows) {
        long time1 =  System.currentTimeMillis();
        generate(columns,rows);
        long time2 = System.currentTimeMillis();
        return time2 - time1;
    }

}
