package algorithms;

public class Main {
    public static void main(String[] args) {
        IMazeGenerator mg = new SimpleMazeGenerator();
        Maze maze = mg.generate(1,3);
        System.out.println(maze);
        Position startPosition = maze.getStartPosition();
// print the start position
        System.out.println(String.format("Start Position: %s", startPosition)); // format "{row,column}"
// prints the maze exit position
        System.out.println(String.format("Goal Position: %s", maze.getGoalPosition()));



    }

}
