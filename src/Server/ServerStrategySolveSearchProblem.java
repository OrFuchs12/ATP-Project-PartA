package Server;
import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.Maze;
import algorithms.search.*;

import java.io.*;
import java.util.Arrays;


public class ServerStrategySolveSearchProblem implements IServerStrategy{
    String tempDirectoryPath;

    public ServerStrategySolveSearchProblem()
    {
        tempDirectoryPath = System.getProperty("java.io.tmpdir");
    }

    /**
     * receives a maze from the client and solves it according to the search algorithm in the configurations
     * saves the maze and the solution with its hashcode in a file in the computer and checks for already solved mazes
     * @param inFromClient
     * @param outToClient
     */
    @Override
    public void ServerStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            Maze maze = (Maze) fromClient.readObject();
            int sol_name = Arrays.hashCode(maze.toByteArray());

            if (new File(tempDirectoryPath, "maze"+sol_name).exists())
            {
                ObjectInputStream in = new ObjectInputStream(new FileInputStream(tempDirectoryPath+"\\solution" +sol_name));
                Solution sol =(Solution)in.readObject();
                toClient.writeObject(sol);
                toClient.flush();
                System.out.println("solution already exists");

            }
            else {
                SearchableMaze searchableMaze = new SearchableMaze(maze);
                Configurations co = Configurations.getInstance();
                String alg = co.getProp("mazeSearchingAlgorithm");
                ISearchingAlgorithm search = getAlg(alg);
                Solution sol = search.solve(searchableMaze);
                handleSol(sol, sol_name);
                handleMaze(sol_name, maze);
                toClient.writeObject(sol);
                toClient.flush();
            }
            toClient.close();
            fromClient.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * saves a solution to a temp file
     * @param sol Solution
     * @param sol_name name of the file
     */

    private synchronized void handleSol(Solution sol, int sol_name)
    {
        try {
            FileOutputStream sol_file = new FileOutputStream(tempDirectoryPath +"\\solution"+ sol_name);
            ObjectOutputStream out = new ObjectOutputStream(sol_file);
            out.writeObject(sol);
            out.flush();
            out.close();
            sol_file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * saves the maze to a file
     * @param sol_name Solution
     * @param maze
     */
    private synchronized void  handleMaze(int sol_name, Maze maze){
        try {
            FileOutputStream file = new FileOutputStream(tempDirectoryPath + "\\maze" + sol_name);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(maze);
            out.flush();
            out.close();
            file.close();
        }
        catch(Exception e){
            e.printStackTrace();

        }

    }

    /**
     * according to configurations
     * @param s
     * @return algorithm to solve
     */
    private ISearchingAlgorithm getAlg(String s)
    {
        if (s.equals("BreadthFirstSearch"))
        {
            return new BreadthFirstSearch();
        }
        if (s.equals("DepthFirstSearch"))
        {
            return new DepthFirstSearch();
        }
        if (s.equals("BestFirstSearch"))
        {
            return new BestFirstSearch();
        }
        return null;
    }
    
    
}
